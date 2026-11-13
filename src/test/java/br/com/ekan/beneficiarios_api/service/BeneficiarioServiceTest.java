package br.com.ekan.beneficiarios_api.service;

import br.com.ekan.beneficiarios_api.dto.BeneficiarioRequestDTO;
import br.com.ekan.beneficiarios_api.entity.Beneficiario;
import br.com.ekan.beneficiarios_api.repository.BeneficiarioRepository;
import br.com.ekan.beneficiarios_api.service.BeneficiarioService;
import br.com.ekan.beneficiarios_api.exception.RecursoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BeneficiarioServiceTest {

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @InjectMocks 
    private BeneficiarioService beneficiarioService;

    @Test
    void deveListarTodosOsBeneficiarios() {
        beneficiarioService.listarTodos();

        verify(beneficiarioRepository).findAll();
    }

    @Test
    void deveCadastrarBeneficiario() {
        BeneficiarioRequestDTO dados = new BeneficiarioRequestDTO();
        dados.setNome("Maria Silva");
        dados.setTelefone("74999999999");
        dados.setDataNascimento(LocalDate.of(1995, 5, 10));

        Beneficiario beneficiarioSalvo = new Beneficiario();
        beneficiarioSalvo.setId(1L);
        beneficiarioSalvo.setNome(dados.getNome());
        beneficiarioSalvo.setTelefone(dados.getTelefone());
        beneficiarioSalvo.setDataNascimento(dados.getDataNascimento());

        when(beneficiarioRepository.save(any(Beneficiario.class)))
                .thenReturn(beneficiarioSalvo);

        var resposta = beneficiarioService.cadastrar(dados);

        assertEquals("Maria Silva", resposta.getNome());
        assertEquals(1L, resposta.getId());

        verify(beneficiarioRepository).save(any(Beneficiario.class));
    }

    @Test
    void deveLancarExcecaoQuandoBeneficiarioNaoExistir() {
        when(beneficiarioRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> beneficiarioService.buscarPorId(999L)
        );
    }

    @Test
    void deveAtualizarBeneficiario() {
        Beneficiario existente = new Beneficiario();
        existente.setId(1L);
        existente.setNome("Maria Silva");
        existente.setTelefone("74999999999");

        BeneficiarioRequestDTO dados = new BeneficiarioRequestDTO();
        dados.setNome("Maria Souza");
        dados.setTelefone("74988888888");
        dados.setDataNascimento(LocalDate.of(1995, 5, 10));

        when(beneficiarioRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(beneficiarioRepository.save(any(Beneficiario.class)))
                .thenAnswer(invocacao -> invocacao.getArgument(0));

        var resposta = beneficiarioService.atualizar(1L, dados);

        assertEquals("Maria Souza", resposta.getNome());
        assertEquals("74988888888", resposta.getTelefone());
        assertEquals(1L, resposta.getId());

        verify(beneficiarioRepository).save(any(Beneficiario.class));
    }

    @Test
    void deveExcluirBeneficiario() {
        Beneficiario existente = new Beneficiario();
        existente.setId(1L);

        when(beneficiarioRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        beneficiarioService.excluir(1L);

        verify(beneficiarioRepository).delete(existente);
    }
}