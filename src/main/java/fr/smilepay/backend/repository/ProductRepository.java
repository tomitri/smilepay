package fr.smilepay.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.smilepay.backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
