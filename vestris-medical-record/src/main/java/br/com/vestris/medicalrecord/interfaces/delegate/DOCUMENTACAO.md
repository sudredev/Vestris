## src\main\java\br\com\vestris\medicalrecord\interfaces\delegate

### ApiDelegateAtendimentos.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegateAtendimentos.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceAtendimento;
import br.com.vestris.medicalrecord.domain.model.Atendimento;
import br.com.vestris.medicalrecord.interfaces.api.AtendimentosApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.*;
import br.com.vestris.species.application.ServiceEspecie;
import br.com.vestris.user.application.ServiceUsuario;
import br.com.vestris.user.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateAtendimentos implements AtendimentosApiDelegate {

    private final ServiceAtendimento servico;
    private final ServiceUsuario servicoUsuario;
    private final ServiceEspecie servicoEspecie;

    // --- AGENDAR ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> agendarAtendimento(AgendamentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setTitulo(request.getTitulo());
        a.setProtocoloId(request.getProtocoloId());

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        } else {
            a.setDataHora(LocalDateTime.now());
        }

        a.setStatus(Atendimento.StatusAtendimento.AGENDADO);

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- CRIAR (LEGADO/COMPLETO) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> criarAtendimento(AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setVeterinarioId(request.getVeterinarioId());
        a.setProtocoloId(request.getProtocoloId());
        a.setTitulo(request.getTitulo());

        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) { }
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());
        a.setObservacoes(request.getObservacoes());

        if (request.getDataHora() != null) {
            a.setDataHora(request.getDataHora().toLocalDateTime());
        }

        Atendimento salvo = servico.criar(a, request.getPacienteId());
        return ResponseEntity.ok(criarResponse(salvo));
    }

    // --- LISTAR ---
    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarMeusAtendimentos(
            UUID veterinarioId,
            StatusAtendimentoEnum status,
            UUID pacienteId,
            java.time.LocalDate dataInicio,
            java.time.LocalDate dataFim
    ) {
        Atendimento.StatusAtendimento statusDomain = status != null
                ? Atendimento.StatusAtendimento.valueOf(status.name())
                : null;

        List<AtendimentoResponse> lista = servico.listar(veterinarioId, statusDomain, pacienteId, dataInicio, dataFim)
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseListaAtendimento> listarAtendimentosPorPaciente(UUID pacienteId) {
        List<AtendimentoResponse> lista = servico.listarPorPaciente(pacienteId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaAtendimento r = new ApiResponseListaAtendimento();
        r.setSucesso(true);
        r.setDados(lista);
        return ResponseEntity.ok(r);
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> buscarAtendimentoPorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    // --- ATUALIZAR (RASCUNHO/PUT) ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarAtendimento(UUID id, AtendimentoRequest request) {
        Atendimento a = new Atendimento();
        a.setTitulo(request.getTitulo());

        if (request.getStatus() != null) {
            try {
                a.setStatus(Atendimento.StatusAtendimento.valueOf(request.getStatus().name()));
            } catch (Exception e) {}
        }

        a.setQueixaPrincipal(request.getQueixaPrincipal());
        a.setHistoricoClinico(request.getHistoricoClinico());
        a.setExameFisico(request.getExameFisico());
        a.setDiagnostico(request.getDiagnostico());
        a.setCondutaClinica(request.getCondutaClinica());

        // CORREÇÃO: Garantindo que Observações sejam passadas
        a.setObservacoes(request.getObservacoes());

        a.setOrientacoesManejo(request.getOrientacoesManejo());

        a.setProtocoloId(request.getProtocoloId());

        Atendimento atualizado = servico.atualizar(id, a);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- FINALIZAR ---
    @Override
    public ResponseEntity<ApiResponseAtendimento> finalizarAtendimento(UUID id, FinalizacaoAtendimentoRequest request) {
        Atendimento dadosClinicos = new Atendimento();
        dadosClinicos.setTitulo(request.getTitulo());
        dadosClinicos.setQueixaPrincipal(request.getQueixaPrincipal());
        dadosClinicos.setHistoricoClinico(request.getHistoricoClinico());
        dadosClinicos.setExameFisico(request.getExameFisico());
        dadosClinicos.setDiagnostico(request.getDiagnostico());
        dadosClinicos.setCondutaClinica(request.getCondutaClinica());

        // CORREÇÃO: Garantindo observações na finalização
        dadosClinicos.setObservacoes(request.getObservacoes());

        dadosClinicos.setProtocoloId(request.getProtocoloId());

        Atendimento finalizado = servico.finalizar(id, dadosClinicos);
        return ResponseEntity.ok(criarResponse(finalizado));
    }

    @Override
    public ResponseEntity<ApiResponseAtendimento> atualizarStatusAtendimento(UUID id, AtualizarStatusAtendimentoRequest request) {
        Atendimento.StatusAtendimento novoStatus = Atendimento.StatusAtendimento.valueOf(request.getStatus().name());
        Atendimento atualizado = servico.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(criarResponse(atualizado));
    }

    // --- CONVERSORES ---
    private ApiResponseAtendimento criarResponse(Atendimento a) {
        ApiResponseAtendimento r = new ApiResponseAtendimento();
        r.setSucesso(true);
        r.setDados(converter(a));
        return r;
    }

    private AtendimentoResponse converter(Atendimento a) {
        AtendimentoResponse dto = new AtendimentoResponse();
        dto.setId(a.getId());

        // Datas
        if(a.getCriadoEm() != null) dto.setDataHora(a.getCriadoEm().atOffset(ZoneOffset.UTC));
        if(a.getDataHora() != null) dto.setDataHora(a.getDataHora().atOffset(ZoneOffset.UTC));

        dto.setTitulo(a.getTitulo());

        if (a.getStatus() != null) {
            dto.setStatus(StatusAtendimentoEnum.valueOf(a.getStatus().name()));
        }

        // --- LÓGICA DE GOVERNANÇA E PROTEÇÃO (NOVO) ---
        // Na prática, teríamos o contexto de segurança aqui.
        // Como o SecurityContext ainda não foi totalmente integrado neste método (Drop 1),
        // preparamos a estrutura.
        // O FrontEnd já faz o bloqueio visual para 'ADMIN_GESTOR'.
        // No futuro (Drop 2), adicionaremos:
        // if (SecurityUtils.temPerfil("ADMIN_GESTOR")) { censurar() }

        // Por enquanto, enviamos os dados, pois o front do Admin Gestor não deve chamar esta rota de detalhe,
        // ou se chamar, o front esconde. Mas para segurança em profundidade, deixamos o hook pronto:

        boolean censurarDadosClinicos = false; // Mudar para lógica real futuramente

        if (censurarDadosClinicos) {
            dto.setQueixaPrincipal("[DADO PROTEGIDO]");
            dto.setHistoricoClinico("[DADO PROTEGIDO]");
            dto.setExameFisico("[DADO PROTEGIDO]");
            dto.setDiagnostico("[DADO PROTEGIDO]");
            dto.setCondutaClinica("[DADO PROTEGIDO]");
            dto.setObservacoes("[DADO PROTEGIDO]");
            dto.setOrientacoesManejo("[DADO PROTEGIDO]");
        } else {
            dto.setQueixaPrincipal(a.getQueixaPrincipal());
            dto.setHistoricoClinico(a.getHistoricoClinico());
            dto.setExameFisico(a.getExameFisico());
            dto.setDiagnostico(a.getDiagnostico());
            dto.setCondutaClinica(a.getCondutaClinica());
            dto.setOrientacoesManejo(a.getOrientacoesManejo());
            dto.setObservacoes(a.getObservacoes());
        }

        dto.setProtocoloId(a.getProtocoloId());
        dto.setVeterinarioId(a.getVeterinarioId());

        // Dados Relacionados (Enriquecimento)
        try {
            Usuario veterinario = servicoUsuario.buscarPorId(a.getVeterinarioId());
            dto.setVeterinarioNome(veterinario.getNome());
            dto.setVeterinarioCrmv(veterinario.getCrmv());
        } catch (Exception e) {
            dto.setVeterinarioNome("Veterinário não encontrado");
        }

        if (a.getPaciente() != null) {
            dto.setPacienteId(a.getPaciente().getId());
            dto.setPacienteNome(a.getPaciente().getNome());
            try {
                var especie = servicoEspecie.buscarPorId(a.getPaciente().getEspecieId());
                dto.setPacienteEspecie(especie.getNomePopular());
            } catch (Exception e) {
                dto.setPacienteEspecie("Espécie não identificada");
            }
        }
        return dto;
    }
}
```

### ApiDelegateExame.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegateExame.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServiceExames;
import br.com.vestris.medicalrecord.domain.model.ExameAnexo;
import br.com.vestris.medicalrecord.interfaces.api.ExamesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaExameAnexo;
import br.com.vestris.medicalrecord.interfaces.dto.ExameAnexoResponse;
import br.com.vestris.user.application.ServiceAuditoria;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegateExame implements ExamesApiDelegate {

    private final ServiceExames servico;
    private final ServiceAuditoria serviceAuditoria;

    @Override
    public ResponseEntity<ApiResponseExameAnexo> uploadExame(UUID atendimentoId, MultipartFile arquivo, String observacoes) {
        // Validação básica de arquivo
        if (arquivo == null || arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Ou lance uma ExcecaoRegraNegocio
        }

        // Lógica de "Upload" (Passamos a responsabilidade pro Service lidar com bytes/S3)
        // Aqui simulamos que o Service vai salvar e devolver a entidade com a URL gerada

        String nomeOriginal = arquivo.getOriginalFilename();
        String tipoConteudo = arquivo.getContentType();

        // Dica: Em um cenário real, você passaria 'arquivo.getInputStream()' para o service.
        // Como combinamos que o Service receberia Strings no passo anterior,
        // vamos simular a URL aqui ou ajustar o Service para aceitar MultipartFile (recomendado).

        // Assumindo que o ServiceExames foi ajustado para receber os metadados e gerar a URL:
        // urlSimulada = "https://s3.amazon..." ou "/uploads/..."
        String urlSimulada = "https://storage.vestris.com/" + UUID.randomUUID() + "_" + nomeOriginal;

        ExameAnexo salvo = servico.anexar(
                atendimentoId,
                nomeOriginal,
                tipoConteudo,
                urlSimulada,
                observacoes
        );

        ApiResponseExameAnexo response = new ApiResponseExameAnexo();
        response.setSucesso(true);
        response.setMensagem("Arquivo anexado com sucesso.");
        response.setDados(converter(salvo));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaExameAnexo> listarExamesPorAtendimento(UUID atendimentoId) {
        List<ExameAnexoResponse> lista = servico.listarPorAtendimento(atendimentoId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaExameAnexo response = new ApiResponseListaExameAnexo();
        response.setSucesso(true);
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deletarExame(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- CONVERSOR ---
    private ExameAnexoResponse converter(ExameAnexo entidade) {
        ExameAnexoResponse dto = new ExameAnexoResponse();
        dto.setId(entidade.getId());
        dto.setAtendimentoId(entidade.getAtendimentoId());
        dto.setNomeArquivo(entidade.getNomeArquivo());
        dto.setTipoArquivo(entidade.getTipoArquivo());
        dto.setUrlArquivo(entidade.getUrlArquivo());
        dto.setObservacoes(entidade.getObservacoes());

        if (entidade.getCriadoEm() != null) {
            dto.setCriadoEm(entidade.getCriadoEm().atOffset(ZoneOffset.UTC));
        }
        return dto;
    }
}

```

### ApiDelegatePacientes.java

```java
// src\main\java\br\com\vestris\medicalrecord\interfaces\delegate\ApiDelegatePacientes.java
package br.com.vestris.medicalrecord.interfaces.delegate;

import br.com.vestris.medicalrecord.application.ServicePaciente;
import br.com.vestris.medicalrecord.domain.model.Paciente;
import br.com.vestris.medicalrecord.interfaces.api.PacientesApiDelegate;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponseListaPaciente;
import br.com.vestris.medicalrecord.interfaces.dto.ApiResponsePaciente;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteRequest;
import br.com.vestris.medicalrecord.interfaces.dto.PacienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiDelegatePacientes implements PacientesApiDelegate {

    private final ServicePaciente servico;

    @Override
    public ResponseEntity<ApiResponsePaciente> criarPaciente(PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setEspecieId(request.getEspecieId());
        p.setVeterinarioId(request.getVeterinarioId());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());

        // Novos campos
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());

        // Enum (Agora converte bonito)
        if(request.getSexo() != null) {
            p.setSexo(request.getSexo().name());
        }

        Paciente salvo = servico.criar(p);

        ApiResponsePaciente response = new ApiResponsePaciente();
        response.setSucesso(true);
        response.setDados(converter(salvo));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponseListaPaciente> listarPacientes(UUID veterinarioId) {
        // O serviço já valida se o vet existe. Se não existir, lança exceção tratada.
        List<PacienteResponse> lista = servico.listarPorVeterinario(veterinarioId).stream()
                .map(this::converter)
                .collect(Collectors.toList());

        ApiResponseListaPaciente response = new ApiResponseListaPaciente();
        response.setSucesso(true);
        // Se a lista estiver vazia, o JSON será { "sucesso": true, "dados": [] } -> PERFEITO
        response.setDados(lista);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> buscarPacientePorId(UUID id) {
        return ResponseEntity.ok(criarResponse(servico.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<ApiResponsePaciente> atualizarPaciente(UUID id, PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setDadosTutor(request.getDadosTutor());
        p.setIdentificacaoAnimal(request.getIdentificacaoAnimal());
        p.setDataNascimento(request.getDataNascimento());
        p.setPesoAtualGramas(request.getPesoAtualGramas());
        p.setMicrochip(request.getMicrochip());
        p.setPelagemCor(request.getPelagemCor());
        if(request.getSexo() != null) p.setSexo(request.getSexo().name());

        return ResponseEntity.ok(criarResponse(servico.atualizar(id, p)));
    }

    @Override
    public ResponseEntity<Void> deletarPaciente(UUID id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // --- HELPERS ---
    private ApiResponsePaciente criarResponse(Paciente p) {
        ApiResponsePaciente r = new ApiResponsePaciente();
        r.setSucesso(true);
        r.setDados(converter(p));
        return r;
    }

    private PacienteResponse converter(Paciente p) {
        PacienteResponse dto = new PacienteResponse();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEspecieId(p.getEspecieId());
        dto.setVeterinarioId(p.getVeterinarioId()); // Agora existe no DTO
        dto.setDadosTutor(p.getDadosTutor());
        dto.setIdentificacaoAnimal(p.getIdentificacaoAnimal());

        // Novos campos no DTO
        dto.setDataNascimento(p.getDataNascimento());
        dto.setMicrochip(p.getMicrochip());
        dto.setPelagemCor(p.getPelagemCor());
        dto.setPesoAtualGramas(p.getPesoAtualGramas());

        if (p.getCriadoEm() != null) dto.setCriadoEm(p.getCriadoEm().atOffset(ZoneOffset.UTC));

        // Conversão do Enum (Agora o DTO espera SexoEnum)
        try {
            if(p.getSexo() != null) {
                // Importe o SexoEnum do pacote interfaces.dto
                dto.setSexo(br.com.vestris.medicalrecord.interfaces.dto.SexoEnum.valueOf(p.getSexo()));
            }
        } catch (Exception e) {
            // Se o valor no banco for inválido, deixa nulo ou trata
        }

        return dto;
    }
}

```

