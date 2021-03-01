package fr.smilepay.backend.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MerchantDto {
	
	private Long idMerchant;
	
	private String nameMerchant;
	
	private String lastNameMerchant;
	
	private Date birthdateMerchant;
	
	private Timestamp createDateMerchant;

	private List<ProductDto> products;
	
	private List<AddressDto> addresses;
	
	public Long getIdMerchant() {
		return idMerchant;
	}

	
	public List<ProductDto> getProducts() {
		return products;
	}


	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}


	public List<AddressDto> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}


	public void setIdMerchant(Long idMerchant) {
		this.idMerchant = idMerchant;
	}


	public String getNameMerchant() {
		return nameMerchant;
	}

	public void setNameMerchant(String nameMerchant) {
		this.nameMerchant = nameMerchant;
	}

	public String getLastNameMerchant() {
		return lastNameMerchant;
	}

	public void setLastNameMerchant(String lastNameMerchant) {
		this.lastNameMerchant = lastNameMerchant;
	}

	public Date getBirthdateMerchant() {
		return birthdateMerchant;
	}

	public void setBirthdateMerchant(Date birthdateMerchant) {
		this.birthdateMerchant = birthdateMerchant;
	}

	public Timestamp getCreateDateMerchant() {
		return createDateMerchant;
	}

	public void setCreateDateMerchant(Timestamp createDateMerchant) {
		this.createDateMerchant = createDateMerchant;
	}
	
	
}
