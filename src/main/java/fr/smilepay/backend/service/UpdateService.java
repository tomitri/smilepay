package fr.smilepay.backend.service;

import fr.smilepay.backend.dto.AddressDto;
import fr.smilepay.backend.dto.MerchantDto;
import fr.smilepay.backend.dto.ProductDto;
import fr.smilepay.backend.model.Address;
import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.model.Product;


public interface UpdateService {
	
	public Merchant updateMerchant(final MerchantDto aMerchant);
	
	public Product updateProduct(final ProductDto aProduct);
	
	public Address updateAddress(final AddressDto anAddress);
	
}
