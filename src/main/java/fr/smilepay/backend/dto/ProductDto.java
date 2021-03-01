package fr.smilepay.backend.dto;

import java.util.List;

public class ProductDto {
	
	private Long idProduct;
	
	private String label;
	
	private String currency;
	
	private int unitPrice;
	
	private int weight;
	
	private int height;
	
	private List<MerchantDto> merchants;

	
	public List<MerchantDto> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<MerchantDto> merchants) {
		this.merchants = merchants;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}
