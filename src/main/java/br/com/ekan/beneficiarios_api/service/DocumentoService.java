
package br.com.ekan.beneficiarios_api.service;

import br.com.ekan.beneficiarios_api.dto.DocumentoResponseDTO;
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

    public List<DocumentoResponseDTO> listarPorBeneficiario(
            Long beneficiarioId) {

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
