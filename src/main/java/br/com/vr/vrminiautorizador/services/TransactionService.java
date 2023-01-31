package br.com.vr.vrminiautorizador.services;

import br.com.vr.vrminiautorizador.models.adapters.transaction.TransactionAdapter;
import br.com.vr.vrminiautorizador.models.dtos.transaction.TransactionDTO;
import br.com.vr.vrminiautorizador.models.entities.Card;
import br.com.vr.vrminiautorizador.models.enums.ETransactionMessageHandler;
import br.com.vr.vrminiautorizador.repositories.card.ICardRepository;
import br.com.vr.vrminiautorizador.repositories.transaction.ITransactionRepository;
import br.com.vr.vrminiautorizador.validators.EntityValidator;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@NoArgsConstructor
public class TransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private ICardRepository cardRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW, timeout = 30)
    public String newTransaction(TransactionDTO transactionDTO) {
        try{
            Card card = this.cardRepository.findByCardNumber(transactionDTO.getNumeroCartao())
                    .orElseThrow(() -> EntityValidator.invalidProcessException(ETransactionMessageHandler.NON_EXISTENT_CARD.getMessage()));
            this.validateIfIsValidTransaction(transactionDTO, card);
            this.transactionRepository.saveAndFlush(TransactionAdapter.createTransactionDtoToEntity(transactionDTO));
            card.setBalance(card.getBalance().subtract(transactionDTO.getValor()));
            this.cardRepository.saveAndFlush(card);
            return "OK";
        }catch(Exception e){
            log.error("Unexpected error while processing transaction", e);
            throw e;
        }
    }

    private void validateIfIsValidTransaction(TransactionDTO transactionDTO, Card card) {
        if(!transactionDTO.getSenhaCartao().equalsIgnoreCase(card.getPassword())){
            throw EntityValidator.invalidProcessException(ETransactionMessageHandler.INVALID_PASSWORD.getMessage());
        }
        if(transactionDTO.getValor().compareTo(card.getBalance()) > 0){
            throw EntityValidator.invalidProcessException(ETransactionMessageHandler.INSUFFICIENT_FUNDS.getMessage());
        }
    }
}
