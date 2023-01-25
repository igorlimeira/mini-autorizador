package br.com.vr.vrminiautorizador.utils;

import br.com.vr.vrminiautorizador.models.dtos.transaction.TransactionDTO;

import java.math.BigDecimal;

public class TransactionUtil {
    public static TransactionDTO getTransaction(){
        return TransactionDTO.builder()
                .numeroCartao("6549873025634599")
                .senhaCartao("1234")
                .valor(BigDecimal.valueOf(250))
                .build();
    }

    public static TransactionDTO getTransactionWithNonExistentCard(){
        return TransactionDTO.builder()
                .numeroCartao("9999999999999999")
                .senhaCartao("1234")
                .valor(BigDecimal.TEN)
                .build();
    }

    public static TransactionDTO getTransactionWithInvalidCardPassword(){
        return TransactionDTO.builder()
                .numeroCartao("6549873025634599")
                .senhaCartao("9999")
                .valor(BigDecimal.TEN)
                .build();
    }
}
