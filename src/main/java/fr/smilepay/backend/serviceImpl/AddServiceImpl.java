package fr.smilepay.backend.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.smilepay.backend.dto.AddressDto;
import fr.smilepay.backend.dto.MerchantDto;
import fr.smilepay.backend.dto.ProductDto;
import fr.smilepay.backend.model.Address;
import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.model.Product;
import fr.smilepay.backend.repository.AddressRepository;
import fr.smilepay.backend.repository.MerchantRepository;
import fr.smilepay.backend.repository.ProductRepository;
import fr.smilepay.backend.service.AddService;

@Service
@Transactional
public class AddServiceImpl implements AddService {

	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	@Transactional
	public Merchant addMerchant(MerchantDto aMerchant) {
		Merchant mer = new Merchant();
		BeanUtils.copyProperties(aMerchant, mer);
		return this.merchantRepository.save(mer);
	}

	@Override
	@Transactional
	public Product addProduct(ProductDto aProduct) {
		Product produit = new Product();
		BeanUtils.copyProperties(aProduct, produit);
		return this.productRepository.save(produit);
	}

	@Override
	@Transactional
	public Address addAddress(AddressDto anAddress) {
		Address adr = new Address();
		BeanUtils.copyProperties(anAddress, adr);
		return this.addressRepository.save(adr);
	}

}
