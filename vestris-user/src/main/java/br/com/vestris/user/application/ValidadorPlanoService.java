package br.com.vestris.user.application;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ValidadorPlanoService {
    void validarLimiteVeterinarios(UUID clinicaId);
}
