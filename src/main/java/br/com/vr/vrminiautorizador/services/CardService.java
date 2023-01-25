package br.com.vr.vrminiautorizador.services;

import br.com.vr.vrminiautorizador.models.adapters.card.CardAdapter;
import br.com.vr.vrminiautorizador.models.dtos.card.CreateCardDTO;
import br.com.vr.vrminiautorizador.models.entities.Card;
import br.com.vr.vrminiautorizador.models.vos.CardBalanceVO;
import br.com.vr.vrminiautorizador.repositories.card.ICardRepository;
import br.com.vr.vrminiautorizador.validators.EntityValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CardService {
    private ICardRepository cardRepository;

    public CardService(ICardRepository repository) {
        this.cardRepository = repository;
    }
    public CreateCardDTO createCard(CreateCardDTO createCardDTO) {
        this.validateNewCardRules(createCardDTO);
        this.cardRepository.saveAndFlush(CardAdapter.createCardDtoToEntity(createCardDTO));
        return createCardDTO;
    }

    private void validateNewCardRules(CreateCardDTO createCardDTO) {
        if(this.cardRepository.existsByCardNumber(createCardDTO.getNumeroCartao())){
            //Para o caso onde o cartão já exista, preferi retornar uma mensagem de exceção ao invés do payload.
            throw EntityValidator.invalidProcessException(String.format("Cartão %s existente", createCardDTO.getNumeroCartao()));
        }
    }

    public CardBalanceVO getCardBalance(String cardNumber) {
        //Para o caso onde o cartão não exista, preferi retornar uma mensagem de exceção ao invés de um body vazio.
        Card card = this.cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> EntityValidator.notFoundException(String.format("Cartão não encontrado!")));
        return CardBalanceVO.builder()
                .balance(card.getBalance())
                .build();
    }
}
