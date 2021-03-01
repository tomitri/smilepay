package fr.smilepay.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.smilepay.backend.dto.MerchantDto;
import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.model.Product;
import fr.smilepay.backend.repository.MerchantRepository;
import fr.smilepay.backend.repository.ProductRepository;
import fr.smilepay.backend.serviceImpl.BindServiceImpl;

public interface BindService {
	
	public Merchant bindMerchantToProduct(Long idMerchant, Long IdProduct);

}

