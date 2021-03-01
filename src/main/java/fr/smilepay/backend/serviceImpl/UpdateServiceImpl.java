package fr.smilepay.backend.serviceImpl;

import java.util.Optional;

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
import fr.smilepay.backend.service.UpdateService;

@Service
@Transactional
public class UpdateServiceImpl implements UpdateService{
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public Merchant updateMerchant(MerchantDto aMerchant) {
		Merchant mer = new Merchant();
		Optional<Merchant> merc = merchantRepository.findById(aMerchant.getIdMerchant());
		if ( merc.isPresent() ) {
			BeanUtils.copyProperties(merc.get(), mer);
			BeanUtils.copyProperties(aMerchant, mer, "createDateMerchant");
			return merchantRepository.save(mer);
		}
		else {
			return null;
		}
	}

	@Override
	public Product updateProduct(ProductDto aProduct) {
		Product produit = new Product();
		BeanUtils.copyProperties(aProduct, produit);
		return this.productRepository.save(produit);
	}

	@Override
	public Address updateAddress(AddressDto anAddress) {
		Address adr = new Address();
		BeanUtils.copyProperties(anAddress, adr);
		return this.addressRepository.save(adr);
	}

}
