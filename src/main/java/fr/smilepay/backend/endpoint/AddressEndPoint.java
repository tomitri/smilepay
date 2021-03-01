package fr.smilepay.backend.endpoint;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.smilepay.backend.address_ws.AddAddressRequest;
import fr.smilepay.backend.address_ws.AddAddressResponse;
import fr.smilepay.backend.address_ws.AddressInfo;
import fr.smilepay.backend.address_ws.DeleteAddressRequest;
import fr.smilepay.backend.address_ws.DeleteAddressResponse;
import fr.smilepay.backend.address_ws.ServiceStatus;
import fr.smilepay.backend.address_ws.UpdateAddressRequest;
import fr.smilepay.backend.address_ws.UpdateAddressResponse;
import fr.smilepay.backend.dto.AddressDto;
import fr.smilepay.backend.model.Address;
import fr.smilepay.backend.product_ws.UpdateProductResponse;
import fr.smilepay.backend.service.AddService;
import fr.smilepay.backend.service.DeleteService;
import fr.smilepay.backend.service.UpdateService;

/*
 * The endpoint for operation on the table Address
 */
@Endpoint
public class AddressEndPoint {
	
private static final String NAMESPACE_URI = "http://www.smile.com/address-ws";

	
	@Autowired
	AddService addService;
	
	@Autowired
	UpdateService updateService;
	
	@Autowired
	DeleteService deleteService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAddressRequest")
	@ResponsePayload
	public AddAddressResponse addProduct(@RequestPayload AddAddressRequest request) {
		AddAddressResponse response = new AddAddressResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		AddressDto addressDto = new AddressDto();
		addressDto.setNumAddress(request.getAddressInfo().getNuméro());
		addressDto.setStreetAddress(request.getAddressInfo().getStreet());
		addressDto.setZipcode(request.getAddressInfo().getZipcode());
		Address addAddress = addService.addAddress(addressDto);

		if (addAddress == null) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Adding Address failed");
			response.setServiceStatus(serviceStatus);
		} else {
			AddressInfo addressInf = new AddressInfo();
			BeanUtils.copyProperties(addAddress, addressInf);
			response.setAddressInfo(addressInf);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Adding Address Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAddressRequest")
	@ResponsePayload
	public UpdateAddressResponse updateArticle(@RequestPayload UpdateAddressRequest request) {
		UpdateAddressResponse response = new UpdateAddressResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		AddressDto addressDto = new AddressDto();
		addressDto.setNumAddress(request.getAddressInfo().getNuméro());
		addressDto.setStreetAddress(request.getAddressInfo().getStreet());
		addressDto.setZipcode(request.getAddressInfo().getZipcode());
		Address updateAddress = updateService.updateAddress(addressDto);

		if (updateAddress == null) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Updated Address failed");
			response.setServiceStatus(serviceStatus);
		} else {
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Updated Address Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAddressRequest")
	@ResponsePayload
	public DeleteAddressResponse deleteAddress(@RequestPayload DeleteAddressRequest request) {
		DeleteAddressResponse response = new DeleteAddressResponse();
		ServiceStatus serviceStatus = new ServiceStatus();

		if ( request == null ) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Delete Address failed");
			response.setServiceStatus(serviceStatus);
		} else {
			AddressDto addressDto = new AddressDto();
			addressDto.setIdAddress(request.getIdAddress());
			deleteService.deleteAddress(addressDto);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Deleted Address Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}

}
