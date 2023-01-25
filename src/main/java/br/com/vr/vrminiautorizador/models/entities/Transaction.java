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

@Table(name = "transaction")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends AuditMetadataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "card_number", length = 16, nullable = false, updatable = false)
    private String cardNumber;

    @Column(name = "transaction_value", precision = 12, scale = 4, nullable = false)
    private BigDecimal transactionValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_number", referencedColumnName = "card_number", nullable = false, insertable = false, updatable = false)
    private Card card;

}
