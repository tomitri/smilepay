package fr.smilepay.backend.service;

import fr.smilepay.backend.dto.AddressDto;
import fr.smilepay.backend.dto.MerchantDto;
import fr.smilepay.backend.dto.ProductDto;
import fr.smilepay.backend.model.Address;
import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.model.Product;

public interface AddService {

	public Merchant addMerchant(final MerchantDto aMerchant);
	
	public Product addProduct(final ProductDto aProduct);
	
	public Address addAddress(final AddressDto anAddress);
	
}
