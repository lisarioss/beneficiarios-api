
package br.com.ekan.beneficiarios_api.controller;

import br.com.ekan.beneficiarios_api.dto.DocumentoResponseDTO;
import br.com.ekan.beneficiarios_api.service.DocumentoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios/{beneficiarioId}/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> listarPorBeneficiario(
            @PathVariable Long beneficiarioId) {

        return ResponseEntity.ok(
                documentoService.listarPorBeneficiario(beneficiarioId)
        );
    }
}
