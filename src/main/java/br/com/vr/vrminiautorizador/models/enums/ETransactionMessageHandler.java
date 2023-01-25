package br.com.vr.vrminiautorizador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
public enum ETransactionMessageHandler {
    INSUFFICIENT_FUNDS(900L, "SALDO_INSUFICIENTE"),
    INVALID_PASSWORD(901L, "SENHA_INVALIDA"),
    INVALID_CARD_NBR(902L, "NUMERO_CARTAO_INVALIDO"),
    INVALID_VALUE(903L, "VALOR_NAO_INFORMADO"),
    NON_EXISTENT_CARD(904L, "CARTAO_INEXISTENTE");
    private final Long id;
    private final String message;

}
