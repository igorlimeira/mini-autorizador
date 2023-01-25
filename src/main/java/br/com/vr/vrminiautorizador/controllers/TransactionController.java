package br.com.vr.vrminiautorizador.controllers;

import br.com.vr.vrminiautorizador.models.dtos.card.CreateCardDTO;
import br.com.vr.vrminiautorizador.models.dtos.transaction.TransactionDTO;
import br.com.vr.vrminiautorizador.models.vos.CardBalanceVO;
import br.com.vr.vrminiautorizador.services.CardService;
import br.com.vr.vrminiautorizador.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
@RequestMapping(path = "transacoes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping()
    public ResponseEntity<String> newTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        log.debug("BEGIN newTransaction: transactionDTO={}", transactionDTO);
        return new ResponseEntity<>(this.transactionService.newTransaction(transactionDTO), HttpStatus.CREATED);
    }

}
