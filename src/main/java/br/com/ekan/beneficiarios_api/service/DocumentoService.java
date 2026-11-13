package br.com.ekan.beneficiarios_api.service;

import br.com.ekan.beneficiarios_api.entity.Documento;
import br.com.ekan.beneficiarios_api.repository.DocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> listarPorBeneficiario(long beneficiarioId) {
        return documentoRepository.findByBeneficiarioId(beneficiarioId);
    }
}