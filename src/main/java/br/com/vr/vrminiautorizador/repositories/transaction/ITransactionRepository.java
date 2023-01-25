package br.com.vr.vrminiautorizador.repositories.transaction;

import br.com.vr.vrminiautorizador.models.entities.Card;
import br.com.vr.vrminiautorizador.models.entities.Transaction;

public interface ITransactionRepository {
    Transaction saveAndFlush(Transaction transaction);
}
