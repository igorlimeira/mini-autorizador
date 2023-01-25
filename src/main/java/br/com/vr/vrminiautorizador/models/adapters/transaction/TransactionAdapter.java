package br.com.vr.vrminiautorizador.models.adapters.transaction;

import br.com.vr.vrminiautorizador.models.dtos.transaction.TransactionDTO;
import br.com.vr.vrminiautorizador.models.entities.Transaction;

public class TransactionAdapter {

    public static Transaction createTransactionDtoToEntity(final TransactionDTO transactionDTO){
        return Transaction.builder()
                .cardNumber(transactionDTO.getNumeroCartao())
                .transactionValue(transactionDTO.getValor())
                .cardNumber(transactionDTO.getNumeroCartao())
                .build();
    }

}
