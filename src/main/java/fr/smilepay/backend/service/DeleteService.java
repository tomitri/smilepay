package fr.smilepay.backend.service;

import fr.smilepay.backend.dto.AddressDto;
import fr.smilepay.backend.dto.MerchantDto;
import fr.smilepay.backend.dto.ProductDto;
import fr.smilepay.backend.model.Address;
import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.model.Product;


public interface DeleteService {
	public void deleteMerchant(final MerchantDto aMerchant);
	
	public void deleteProduct(final ProductDto aProduct);
	
	public void deleteAddress(final AddressDto anAddress);
}
