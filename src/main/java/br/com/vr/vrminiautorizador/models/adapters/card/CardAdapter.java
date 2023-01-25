package br.com.vr.vrminiautorizador.models.adapters.card;

import br.com.vr.vrminiautorizador.models.dtos.card.CreateCardDTO;
import br.com.vr.vrminiautorizador.models.entities.Card;

import java.math.BigDecimal;

public class CardAdapter {
    private static final BigDecimal DEFAULT_CARD_BALANCE = BigDecimal.valueOf(500);

    public static Card createCardDtoToEntity(final CreateCardDTO createCardDTO){
        return Card.builder()
                .cardNumber(createCardDTO.getNumeroCartao())
                .password(createCardDTO.getSenha())
                .balance(DEFAULT_CARD_BALANCE)
                .build();
    }

}
