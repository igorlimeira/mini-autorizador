package br.com.vr.vrminiautorizador.models.dtos.card;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardDTO {
    @Valid()
    @NotNull
    @Size(min = 16, max = 16, message = "Número do Cartão inválido")
    private String numeroCartao;
    @Valid()
    @NotNull
    @Size(min = 4, max = 12, message = "Tamanho mínimo 4 máximo 12")
    private String senha;

}
