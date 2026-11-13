
package br.com.ekan.beneficiarios_api.service;

import br.com.ekan.beneficiarios_api.dto.BeneficiarioRequestDTO;
import br.com.ekan.beneficiarios_api.dto.BeneficiarioResponseDTO;
import br.com.ekan.beneficiarios_api.dto.DocumentoRequestDTO;
import br.com.ekan.beneficiarios_api.entity.Beneficiario;
import br.com.ekan.beneficiarios_api.entity.Documento;
import br.com.ekan.beneficiarios_api.repository.BeneficiarioRepository;
import br.com.ekan.beneficiarios_api.exception.RecursoNaoEncontradoException;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public List<BeneficiarioResponseDTO> listarTodos() {
        return beneficiarioRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public BeneficiarioResponseDTO buscarPorId(Long id) {
        return converterParaResponse(obterEntidade(id));
    }

    public BeneficiarioResponseDTO cadastrar(BeneficiarioRequestDTO dados) {
        Beneficiario beneficiario = new Beneficiario();

        beneficiario.setNome(dados.getNome());
        beneficiario.setTelefone(dados.getTelefone());
        beneficiario.setDataNascimento(dados.getDataNascimento());

        LocalDateTime agora = LocalDateTime.now();
        beneficiario.setDataInclusao(agora);

        if (dados.getDocumentos() != null) {
            for (DocumentoRequestDTO documentoDTO : dados.getDocumentos()) {
                Documento documento = new Documento();

                documento.setTipoDocumento(documentoDTO.getTipoDocumento());
                documento.setDescricao(documentoDTO.getDescricao());
                documento.setDataInclusao(agora);
                documento.setBeneficiario(beneficiario);

                beneficiario.getDocumentos().add(documento);
            }
        }

        return converterParaResponse(
                beneficiarioRepository.save(beneficiario)
        );
    }

    public BeneficiarioResponseDTO atualizar(
            Long id, BeneficiarioRequestDTO dados) {

        Beneficiario beneficiario = obterEntidade(id);

        beneficiario.setNome(dados.getNome());
        beneficiario.setTelefone(dados.getTelefone());
        beneficiario.setDataNascimento(dados.getDataNascimento());
        beneficiario.setDataAtualizacao(LocalDateTime.now());

        return converterParaResponse(
                beneficiarioRepository.save(beneficiario)
        );
    }

    public void excluir(Long id) {
        beneficiarioRepository.delete(obterEntidade(id));
    }

    private Beneficiario obterEntidade(Long id) {
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Beneficiário não encontrado: " + id
                ));
    }

    private BeneficiarioResponseDTO converterParaResponse(
            Beneficiario beneficiario) {

        return new BeneficiarioResponseDTO(
                beneficiario.getId(),
                beneficiario.getNome(),
                beneficiario.getTelefone(),
                beneficiario.getDataNascimento(),
                beneficiario.getDataInclusao(),
                beneficiario.getDataAtualizacao()
        );
    }
}
