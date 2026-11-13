
package br.com.ekan.beneficiarios_api.controller;

import br.com.ekan.beneficiarios_api.dto.BeneficiarioRequestDTO;
import br.com.ekan.beneficiarios_api.dto.BeneficiarioResponseDTO;
import br.com.ekan.beneficiarios_api.service.BeneficiarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(
            BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @PostMapping
    public ResponseEntity<BeneficiarioResponseDTO> cadastrar(
            @Valid @RequestBody BeneficiarioRequestDTO dados) {

        BeneficiarioResponseDTO resposta =
                beneficiarioService.cadastrar(dados);

        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<BeneficiarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(beneficiarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficiarioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(beneficiarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody BeneficiarioRequestDTO dados) {

        return ResponseEntity.ok(
                beneficiarioService.atualizar(id, dados)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        beneficiarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
