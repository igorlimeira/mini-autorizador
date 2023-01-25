package br.com.vr.vrminiautorizador.models.vos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardBalanceVO {
    private BigDecimal balance;
}
