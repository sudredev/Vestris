package br.com.vestris.pharmacology.application;

import br.com.vestris.pharmacology.domain.model.DoseReferencia;
import br.com.vestris.pharmacology.domain.model.enums.ViaAdministracao;
import br.com.vestris.pharmacology.domain.repository.RepositorioDoseReferencia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ServiceDoseReferencia {
    private final RepositorioDoseReferencia repositorio;

    public DoseReferencia buscarMelhorReferencia(UUID medicamentoId, UUID especieId, UUID doencaId, String viaString, UUID clinicaId) {

        // 1. Resolve o Enum da Via de forma segura
        ViaAdministracao viaAlvo = null;
        if (viaString != null && !viaString.isBlank()) {
            try {
                // Tenta mapear o input (ex: "Oral", "ORAL") para o Enum
                viaAlvo = ViaAdministracao.valueOf(viaString.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                // Se não mapear, assumimos que a via é desconhecida, o que dificultará o match de vias específicas
            }
        }

        // 2. Busca TUDO do banco para este remédio
        List<DoseReferencia> referencias = repositorio.findByMedicamentoId(medicamentoId);

        if (referencias.isEmpty()) return null;

        // 3. Filtra e Pontua em Memória
        ViaAdministracao finalViaAlvo = viaAlvo;

        return referencias.stream()
                .filter(ref -> {
                    // Clínica: Referência deve ser global (null) ou da minha clínica
                    boolean clinicaMatch = ref.getClinicaId() == null || (clinicaId != null && ref.getClinicaId().equals(clinicaId));
                    if (!clinicaMatch) return false;

                    // Espécie: Deve ser genérica (null) ou exata. Não aceitamos referência de Gato para Calopsita.
                    boolean especieMatch = ref.getEspecieId() == null || (especieId != null && ref.getEspecieId().equals(especieId));
                    if (!especieMatch) return false;

                    // Doença: Deve ser genérica (null) ou exata.
                    boolean doencaMatch = ref.getDoencaId() == null || (doencaId != null && ref.getDoencaId().equals(doencaId));
                    if (!doencaMatch) return false;

                    // Via:
                    // Se a referência tem via definida (ex: IV), e eu pedi outra (ex: Oral), descarta.
                    // Se a referência é genérica (null), aceita qualquer input.
                    // Se eu não pedi via (null), aceita referências genéricas ou específicas (mas pontua menos).
                    boolean viaMatch = ref.getVia() == null || (finalViaAlvo != null && ref.getVia() == finalViaAlvo);

                    return viaMatch;
                })
                .max(Comparator.comparingInt(ref -> calcularScore(ref, especieId, doencaId, finalViaAlvo, clinicaId)))
                .orElse(null);
    }

    private int calcularScore(DoseReferencia ref, UUID especieId, UUID doencaId, ViaAdministracao viaAlvo, UUID clinicaId) {
        int score = 0;

        // Doença bateu exato? (Peso Máximo - Regra mais específica possível)
        if (ref.getDoencaId() != null && ref.getDoencaId().equals(doencaId)) score += 1000;

        // Espécie bateu exato? (Peso Alto)
        if (ref.getEspecieId() != null && ref.getEspecieId().equals(especieId)) score += 100;

        // Via bateu exata? (Peso Médio)
        if (ref.getVia() != null && ref.getVia() == viaAlvo) score += 50;

        // É Institucional? (Preferência sobre Oficial se o resto for igual)
        if (ref.getClinicaId() != null && ref.getClinicaId().equals(clinicaId)) score += 10;

        // Referência mais recente/segura (Desempate) -> Poderíamos usar data, mas por enquanto origem serve
        if ("OFICIAL".equals(ref.getOrigem())) score += 1;

        return score;
    }

    public List<String> listarViasPorMedicamentoEEspecie(UUID medicamentoId, UUID especieId) {
        List<ViaAdministracao> vias = repositorio.listarViasDisponiveis(medicamentoId, especieId);

        // Converte Enum para String para facilitar o transporte
        return vias.stream()
                .filter(v -> v != null)
                .map(Enum::name)
                .toList();
    }
}
