package br.com.vr.vrminiautorizador.repositories.card;

import br.com.vr.vrminiautorizador.models.entities.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardRepository implements ICardRepository{

    @Autowired
    private CardJPARepository cardJPARepository;

    @Override
    public boolean existsByCardNumber(String cardNumber) {
        return this.cardJPARepository.existsByCardNumber(cardNumber);
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) {
        return this.cardJPARepository.findByCardNumber(cardNumber);
    }

    @Override
    public Card saveAndFlush(Card card) {
        return this.cardJPARepository.saveAndFlush(card);
    }

}
