package br.com.vestris.pharmacology.domain.repository;

import br.com.vestris.pharmacology.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioMedicamento extends JpaRepository<Medicamento, UUID> {

}
