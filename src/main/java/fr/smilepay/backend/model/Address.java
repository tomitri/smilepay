package fr.smilepay.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long idAddress;
	
	@Column(name="number")
	private int numAddress;
	
	@Column(name="street")
	private String streetAddress;
	
	@Column(name="zipcode")
	private String zipcode;
	
	public void setIdAddress(Long idAddress) {
		this.idAddress = idAddress;
	}

	public Long getIdAddress() {
		return idAddress;
	}
	
	public int getNumAddress() {
		return numAddress;
	}
	
	public void setNumAddress(int numAddress) {
		this.numAddress = numAddress;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}
