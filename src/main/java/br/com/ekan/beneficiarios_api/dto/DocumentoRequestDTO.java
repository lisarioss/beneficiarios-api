
package br.com.ekan.beneficiarios_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoRequestDTO {

    @NotBlank(message = "O tipo do documento é obrigatório")
    private String tipoDocumento;

    @NotBlank(message = "A descrição do documento é obrigatória")
    private String descricao;
}
