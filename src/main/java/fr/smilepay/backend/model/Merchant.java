package fr.smilepay.backend.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "merchant")
public class Merchant {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long idMerchant;
	
	@Column(name="name")
	private String nameMerchant;
	
	@Column(name="lastname")
	private String lastNameMerchant;
	
	@Column(name="birthdate")
	private Date birthdateMerchant;
	
	@Column(name="create_date")
	private Timestamp createDateMerchant;

	@ManyToMany
	@JoinTable( name = "merchant_product",
    joinColumns = @JoinColumn( name = "id_merchant_fk" ),
    inverseJoinColumns = @JoinColumn( name = "id_product_fk" ) )
	private List<Product> products;
	
	@OneToMany
	@JoinTable( name = "merchant_address",
    joinColumns = @JoinColumn( name = "id_merchant_fk" ),
    inverseJoinColumns = @JoinColumn( name = "id_adress_fk" ) )
	private List<Address> addresses;
	
	public Long getIdMerchant() {
		return idMerchant;
	}

	
	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
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
