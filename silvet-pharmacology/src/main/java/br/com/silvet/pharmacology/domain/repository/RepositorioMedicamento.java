package br.com.silvet.pharmacology.domain.repository;

import br.com.silvet.pharmacology.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositorioMedicamento extends JpaRepository<Medicamento, UUID> {

}
