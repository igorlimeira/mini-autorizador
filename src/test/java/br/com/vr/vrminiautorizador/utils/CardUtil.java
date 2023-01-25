package br.com.vr.vrminiautorizador.utils;

import br.com.vr.vrminiautorizador.models.dtos.card.CreateCardDTO;

public class CardUtil {
    public static CreateCardDTO getInsertableCard(){
        return CreateCardDTO.builder()
                .numeroCartao("6549873025634501")
                .senha("1234")
                .build();
    }

    public static CreateCardDTO getCardWithCardNumberBigThanSixteen(){
        return CreateCardDTO.builder()
                .numeroCartao("6549873025634501908789")
                .senha("1234")
                .build();
    }

    public static CreateCardDTO getCardWithCardNumberLessThanSixteen(){
        return CreateCardDTO.builder()
                .numeroCartao("6549873025634")
                .senha("1234")
                .build();
    }

    public static CreateCardDTO getCardWithCardPasswordBiggerThanTwelve(){
        return CreateCardDTO.builder()
                .numeroCartao("6549873025634501")
                .senha("1234567890987")
                .build();
    }

    public static CreateCardDTO getCardWithCardPasswordLessThanFour(){
        return CreateCardDTO.builder()
                .numeroCartao("6549873025634501")
                .senha("123")
                .build();
    }
}
