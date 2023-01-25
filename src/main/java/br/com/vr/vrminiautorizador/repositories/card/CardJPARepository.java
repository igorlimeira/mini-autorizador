package br.com.vr.vrminiautorizador.repositories.card;

import br.com.vr.vrminiautorizador.models.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardJPARepository extends JpaRepository<Card, UUID> {
    boolean existsByCardNumber(String cardNumber);

    Optional<Card> findByCardNumber(String cardNumber);
}
