package fr.smilepay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.smilepay.backend.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
