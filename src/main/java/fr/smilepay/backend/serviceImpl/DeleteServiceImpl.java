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
import fr.smilepay.backend.service.DeleteService;

@Service
@Transactional
public class DeleteServiceImpl implements DeleteService{
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public void deleteMerchant(MerchantDto aMerchant) {
		Merchant mer = new Merchant();
		BeanUtils.copyProperties(aMerchant, mer);
		this.merchantRepository.delete(mer);
	}

	@Override
	public void deleteProduct(ProductDto aProduct) {
		Product produit = new Product();
		BeanUtils.copyProperties(aProduct, produit);
		this.productRepository.delete(produit);
	}

	@Override
	public void deleteAddress(AddressDto anAddress) {
		Address adr = new Address();
		BeanUtils.copyProperties(anAddress, adr);
		this.addressRepository.delete(adr);
	}

}
