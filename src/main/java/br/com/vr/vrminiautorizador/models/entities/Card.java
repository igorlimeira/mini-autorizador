package br.com.vr.vrminiautorizador.models.entities;


import br.com.vr.vrminiautorizador.core.AuditMetadataEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "card")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AuditMetadataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "card_number", length = 16, nullable = false, updatable = false, unique = true)
    private String cardNumber;

    @Column(name = "card_password", length = 12, nullable = false)
    private String password;

    @Column(name = "card_balance", precision = 12, scale = 4, nullable = false)
    private BigDecimal balance;

}
