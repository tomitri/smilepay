package fr.smilepay.backend.dto;

public class AddressDto {
	
	private Long idAddress;

	private int numAddress;
	
	private String streetAddress;
	
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
