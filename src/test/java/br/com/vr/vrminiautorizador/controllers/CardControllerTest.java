package br.com.vr.vrminiautorizador.controllers;

import br.com.vr.vrminiautorizador.utils.CardUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    CardInMemoryRepository cardRepository = new CardInMemoryRepository();
//
//    CardService cardService = new CardService(cardRepository);

    @Test
    @Order(1)
    public void should_be_able_to_save_one_card() throws Exception {
        final var card = CardUtil.getInsertableCard();
        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao").value("6549873025634501"))
                .andExpect(jsonPath("$.senha").value("1234"));
    }

    @Test
    @Order(2)
    public void checking_the_balance_of_the_newly_created_card() throws Exception {
        mockMvc.perform(get("/cartoes/6549873025634501")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(BigDecimal.valueOf(500.0)));
    }



    @Test
    @Order(3)
    public void should_not_be_able_to_repeat_one_card() throws Exception {
        final var card = CardUtil.getInsertableCard();
        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Order(4)
    public void should_not_be_able_to_save_one_card_with_card_number_bigger_than_sixteen() throws Exception {
        final var card = CardUtil.getCardWithCardNumberBigThanSixteen();
        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Número do Cartão inválido"));
    }

    @Test
    @Order(5)
    public void should_not_be_able_to_save_one_card_with_card_number_less_than_sixteen() throws Exception {
        final var card = CardUtil.getCardWithCardNumberLessThanSixteen();
        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Número do Cartão inválido"));
    }

    @Test
    @Order(6)
    public void should_not_be_able_to_save_one_card_with_password_bigger_than_twelve() throws Exception {
        final var card = CardUtil.getCardWithCardPasswordBiggerThanTwelve();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Tamanho mínimo 4 máximo 12"));
    }

    @Test
    @Order(7)
    public void should_not_be_able_to_save_one_card_with_password_less_than_four() throws Exception {
        final var card = CardUtil.getCardWithCardPasswordLessThanFour();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Tamanho mínimo 4 máximo 12"));
    }

}