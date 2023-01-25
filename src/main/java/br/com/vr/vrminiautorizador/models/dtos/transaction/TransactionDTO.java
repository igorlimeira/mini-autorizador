package br.com.vr.vrminiautorizador.models.dtos.transaction;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid()
public class TransactionDTO {
    @NotNull
    @Size(min = 16, max = 16, message = "Número do Cartão inválido")
    private String numeroCartao;
    @NotNull
    @Size(min = 4, max = 12, message = "Tamanho mínimo 4 máximo 12")
    private String senhaCartao;

    @NotNull
    private BigDecimal valor;
}
