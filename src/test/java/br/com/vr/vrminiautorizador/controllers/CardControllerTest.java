package br.com.vr.vrminiautorizador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CardControllerTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private MockMvc mockMvc;

    private UUID randomUUID;

    @BeforeAll
    void setUp() {
        randomUUID = UUID.randomUUID();
        cardRepository.deleteAll();
    }

    @Test
    @Order(1)
    void should_be_able_to_save_one_card() throws Exception {
        final var card = new Card().builder()
        .id(randomUUID)
        .cardNumber("123456789101112")
        .password("1234567")
        .balance(BigDecimal.ZERO).build();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(randomUUID))
                .andExpect(jsonPath("$.cardNumber").value("123456789101112"))
                .andExpect(jsonPath("$.password").value("1234567"))
                .andExpect(jsonPath("$.balance").value(BigDecimal.valueOf(500)));
    }

    @Test
    @Order(2)
    void should_not_be_able_to_repeat_one_card() throws Exception {
        final var card = new Card().builder()
                .id(randomUUID)
                .cardNumber("123456789101112")
                .password("1234567")
                .balance(BigDecimal.ZERO).build();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Order(3)
    void should_not_be_able_to_save_one_card_with_card_number_bigger_than_sixteen() throws Exception {
        final var card = new Card().builder()
                .id(randomUUID)
                .cardNumber("12345678910111213")
                .password("123")
                .balance(BigDecimal.ZERO).build();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    void should_not_be_able_to_save_one_card_with_card_number_less_than_sixteen() throws Exception {
        final var card = new Card().builder()
                .id(randomUUID)
                .cardNumber("123456789101112")
                .password("123")
                .balance(BigDecimal.ZERO).build();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    void should_not_be_able_to_save_one_card_with_password_bigger_than_twelve() throws Exception {
        final var card = new Card().builder()
                .id(randomUUID)
                .cardNumber("1234567891011121")
                .password("1234567891011")
                .balance(BigDecimal.ZERO).build();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(6)
    void should_not_be_able_to_save_one_card_with_password_less_than_four() throws Exception {
        final var card = new Card().builder()
                .id(randomUUID)
                .cardNumber("123456789101112")
                .password("123")
                .balance(BigDecimal.ZERO).build();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    void should_be_able_to_save_one_card_with_balance_equals_to_five_hundred() throws Exception {
        final var card = new Card().builder()
                .id(randomUUID)
                .cardNumber("123456789101112")
                .password("1234567")
                .balance(BigDecimal.ZERO).build();

        mockMvc.perform(post("/cartoes")
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(randomUUID))
                .andExpect(jsonPath("$.cardNumber").value("123456789101112"))
                .andExpect(jsonPath("$.password").value("1234567"))
                .andExpect(jsonPath("$.balance").value(BigDecimal.valueOf(500)));
    }

    @Test
    @Order(8)
    void should_be_able_to_retrieve_balance() throws Exception {
        mockMvc.perform(get("/cartoes/123456789101112")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(randomUUID))
                .andExpect(jsonPath("$.cardNumber").value("123456789101112"))
                .andExpect(jsonPath("$.password").value("1234567"))
                .andExpect(jsonPath("$.balance").value(BigDecimal.valueOf(500)));
    }

}