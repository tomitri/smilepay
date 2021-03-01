package fr.smilepay.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long idProduct;
	
	@Column(name="label")
	private String label;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="unit_price")
	private int unitPrice;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="height")
	private int height;
	
	@ManyToMany
	@JoinTable( name = "merchant_product",
    joinColumns = @JoinColumn( name = "id_product_fk" ),
    inverseJoinColumns = @JoinColumn( name = "id_merchant_fk" ) )
	private List<Merchant> merchants;

	
	public List<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
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
