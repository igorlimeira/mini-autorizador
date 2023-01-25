package br.com.vr.vrminiautorizador.repositories.transaction;

import br.com.vr.vrminiautorizador.models.entities.Card;
import br.com.vr.vrminiautorizador.models.entities.Transaction;
import br.com.vr.vrminiautorizador.repositories.card.CardJPARepository;
import br.com.vr.vrminiautorizador.repositories.card.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionRepository implements ITransactionRepository {
    @Autowired
    private TransactionJPARepository transactionJPARepository;
    @Override
    public Transaction saveAndFlush(Transaction transaction) {
        return this.transactionJPARepository.saveAndFlush(transaction);
    }
}
