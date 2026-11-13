package br.com.ekan.beneficiarios_api.repository;

import br.com.ekan.beneficiarios_api.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import br.com.ekan.beneficiarios_api.entity.Documento;

@Repository
public interface DocumentoRepository 
        extends JpaRepository<Documento, Long> {

        List<Documento> findByBeneficiarioId(Long beneficiarioId);
}