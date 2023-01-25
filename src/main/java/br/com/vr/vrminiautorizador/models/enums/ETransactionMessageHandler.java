package br.com.vr.vrminiautorizador.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ETransactionMessageHandler {
    INSUFFICIENT_FUNDS(900L, "SALDO_INSUFICIENTE"),
    INVALID_PASSWORD(901L, "SENHA_INVALIDA"),
    NON_EXISTENT_CARD(902L, "CARTAO_INEXISTENTE");
    Long id;
    String message;

}
