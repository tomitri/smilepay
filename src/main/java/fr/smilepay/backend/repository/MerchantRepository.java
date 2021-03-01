package fr.smilepay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.smilepay.backend.model.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
