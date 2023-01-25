package br.com.vr.vrminiautorizador.controllers;

import br.com.vr.vrminiautorizador.models.enums.ETransactionMessageHandler;
import br.com.vr.vrminiautorizador.utils.TransactionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void carrying_out_several_transactions_until_the_balance_is_zeroed() throws Exception {
        final var transaction = TransactionUtil.getTransaction();
        HttpStatus statusExpectedWheInsufficientFunds = HttpStatus.OK;
        String returnMessage = "";
        while(!statusExpectedWheInsufficientFunds.is4xxClientError()){
            ResultActions resultActions = mockMvc.perform(post("/transacoes")
                            .content(new ObjectMapper().writeValueAsString(transaction))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print());
            MvcResult mvcResult = resultActions.andReturn();
            statusExpectedWheInsufficientFunds = HttpStatus.valueOf(mvcResult.getResponse().getStatus());
            returnMessage = mvcResult.getResponse().getErrorMessage();
        }
        verify(statusExpectedWheInsufficientFunds.is4xxClientError()
                && returnMessage.equalsIgnoreCase(ETransactionMessageHandler.INSUFFICIENT_FUNDS.getMessage()));
    }

    @Test
    @Order(2)
    public void transaction_should_have_a_valid_password() throws Exception {
        final var transaction = TransactionUtil.getTransactionWithInvalidCardPassword();
        mockMvc.perform(post("/transacoes")
                        .content(new ObjectMapper().writeValueAsString(transaction))
                        .contentType(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
                        .accept(jakarta.ws.rs.core.MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message")
                        .value(ETransactionMessageHandler.INVALID_PASSWORD.getMessage()));

    }

    @Test
    @Order(3)
    public void transaction_should_have_a_valid_card() throws Exception {
        final var transaction = TransactionUtil.getTransactionWithNonExistentCard();
        mockMvc.perform(post("/transacoes")
                        .content(new ObjectMapper().writeValueAsString(transaction))
                        .contentType(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
                        .accept(jakarta.ws.rs.core.MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message")
                        .value(ETransactionMessageHandler.NON_EXISTENT_CARD.getMessage()));
    }
}
