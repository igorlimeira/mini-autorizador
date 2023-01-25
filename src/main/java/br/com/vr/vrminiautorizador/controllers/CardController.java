package br.com.vr.vrminiautorizador.controllers;

import br.com.vr.vrminiautorizador.models.dtos.card.CreateCardDTO;
import br.com.vr.vrminiautorizador.models.vos.CardBalanceVO;
import br.com.vr.vrminiautorizador.services.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "cartoes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CardController {

    @Autowired
    private CardService cardService;
    @PostMapping()
    public ResponseEntity<CreateCardDTO> createCard(@RequestBody @Valid CreateCardDTO createCardDTO) {
        log.debug("BEGIN createCard: createCardDTO={}", createCardDTO);
        return new ResponseEntity<>(this.cardService.createCard(createCardDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{numeroCartao}")
    private ResponseEntity<CardBalanceVO> getCardBalance(@PathVariable String numeroCartao) {
        return ResponseEntity.ok().body(this.cardService.getCardBalance(numeroCartao));
    }
}
