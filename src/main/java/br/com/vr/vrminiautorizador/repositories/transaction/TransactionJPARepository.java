package br.com.vr.vrminiautorizador.repositories.transaction;

import br.com.vr.vrminiautorizador.models.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionJPARepository extends JpaRepository<Transaction, UUID> {
}
