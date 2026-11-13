package br.com.ekan.beneficiarios_api.service;

import br.com.ekan.beneficiarios_api.entity.Beneficiario;
import br.com.ekan.beneficiarios_api.repository.BeneficiarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    public List<Beneficiario> listarTodos() {
        return beneficiarioRepository.findAll();
    }

    public Beneficiario buscarPorId(Long id) {
        return beneficiarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Beneficiário não encontrado: " + id
                ));
    }

    public Beneficiario cadastrar(Beneficiario beneficiario) {
        return beneficiarioRepository.save(beneficiario);
    }

    public Beneficiario atualizar(Long id, Beneficiario dadosAtualizados) {
        Beneficiario beneficiario = buscarPorId(id);

        beneficiario.setNome(dadosAtualizados.getNome());
        beneficiario.setTelefone(dadosAtualizados.getTelefone());
        beneficiario.setDataNascimento(dadosAtualizados.getDataNascimento());

        return beneficiarioRepository.save(beneficiario);
    }

    public void excluir(long id) {
        Beneficiario beneficiario = buscarPorId(id);
        beneficiarioRepository.delete(beneficiario);
    }
}