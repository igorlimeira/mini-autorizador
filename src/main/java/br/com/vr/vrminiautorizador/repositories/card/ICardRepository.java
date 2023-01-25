package br.com.vr.vrminiautorizador.repositories.card;

import br.com.vr.vrminiautorizador.models.entities.Card;

import java.util.Optional;

public interface ICardRepository {

    boolean existsByCardNumber(String cardNumber);

    Optional<Card> findByCardNumber(String cardNumber);

    Card saveAndFlush(Card cardDtoToEntity);
}
