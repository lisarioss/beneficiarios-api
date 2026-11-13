package br.com.ekan.beneficiarios_api.service;

import br.com.ekan.beneficiarios_api.dto.DocumentoResponseDTO;
import br.com.ekan.beneficiarios_api.entity.Documento;
import br.com.ekan.beneficiarios_api.exception.RecursoNaoEncontradoException;
import br.com.ekan.beneficiarios_api.repository.BeneficiarioRepository;
import br.com.ekan.beneficiarios_api.repository.DocumentoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final BeneficiarioRepository beneficiarioRepository;

    public DocumentoService(
            DocumentoRepository documentoRepository,
            BeneficiarioRepository beneficiarioRepository) {
        this.documentoRepository = documentoRepository;
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public List<DocumentoResponseDTO> listarPorBeneficiario(Long beneficiarioId) {

        beneficiarioRepository.findById(beneficiarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Beneficiário não encontrado: " + beneficiarioId
                ));

        return documentoRepository.findByBeneficiarioId(beneficiarioId)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    private DocumentoResponseDTO converterParaResponse(
            Documento documento) {

        return new DocumentoResponseDTO(
                documento.getId(),
                documento.getTipoDocumento(),
                documento.getDescricao(),
                documento.getDataInclusao(),
                documento.getDataAtualizacao()
        );
    }
}

