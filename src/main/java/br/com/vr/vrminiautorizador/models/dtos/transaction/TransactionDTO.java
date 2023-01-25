package br.com.vr.vrminiautorizador.models.dtos.transaction;

import br.com.vr.vrminiautorizador.models.enums.ETransactionMessageHandler;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid()
public class TransactionDTO implements Serializable {

    //A ideia aqui era trazer a mensageria do póprio enum, mas por algum motivo com
    // a versão mais recente do Spring utilizando a lib do Jakarta nas validações, ele não aceita o valor do enum
    // como parâmetro de mensagem.
    @NotNull(message = "NUMERO_CARTAO_INVALIDO")
    @Size(min = 16, max = 16, message = "NUMERO_CARTAO_INVALIDO")
    private String numeroCartao;
    @NotNull(message = "SENHA_INVALIDA")
    @Size(min = 4, max = 12, message = "SENHA_INVALIDA")
    private String senhaCartao;
    @NotNull(message = "VALOR_NAO_INFORMADO")
    private BigDecimal valor;
}
