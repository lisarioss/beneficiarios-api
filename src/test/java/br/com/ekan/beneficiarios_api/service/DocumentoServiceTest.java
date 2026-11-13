package br.com.ekan.beneficiarios_api.service;

import br.com.ekan.beneficiarios_api.repository.DocumentoRepository;
import br.com.ekan.beneficiarios_api.service.DocumentoService;
import br.com.ekan.beneficiarios_api.entity.Beneficiario;
import br.com.ekan.beneficiarios_api.repository.BeneficiarioRepository;
import br.com.ekan.beneficiarios_api.exception.RecursoNaoEncontradoException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.ekan.beneficiarios_api.entity.Documento;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class DocumentoServiceTest {

    @Mock
    private DocumentoRepository documentoRepository;

    @Mock
    private BeneficiarioRepository beneficiarioRepository;

    @InjectMocks
    private DocumentoService documentoService;

    @Test
    void deveListarDocumentosPorBeneficiario() {
        when(beneficiarioRepository.findById(1L))
            .thenReturn(Optional.of(new Beneficiario()));

        documentoService.listarPorBeneficiario(1L);

        verify(documentoRepository).findByBeneficiarioId(1L);
    }

    @Test
    void deveRetornarDocumentosConvertidosEmDTO() {
        when(beneficiarioRepository.findById(1L))
            .thenReturn(Optional.of(new Beneficiario()));
        
        Documento documento = new Documento();
        documento.setId(1L);
        documento.setTipoDocumento("CPF");
        documento.setDescricao("12345678900");

        when(documentoRepository.findByBeneficiarioId(1L))
                .thenReturn(List.of(documento));

        var resposta = documentoService.listarPorBeneficiario(1L);

        assertEquals(1, resposta.size());
        assertEquals("CPF", resposta.get(0).getTipoDocumento());
        assertEquals("12345678900", resposta.get(0).getDescricao());
    }

    @Test
    void deveLancarExcecaoQuandoBeneficiarioNaoExistir() {

        when(beneficiarioRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> documentoService.listarPorBeneficiario(999L)
        );

        verify(documentoRepository, never())
                .findByBeneficiarioId(999L);
    }
    }
