package br.com.vr.vrminiautorizador.core;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditMetadataEntity {
    @CreatedDate
    @Column(name = "created_ts", nullable = false, updatable = false)
    protected LocalDateTime created;

}
