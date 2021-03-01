package fr.smilepay.backend.endpoint;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.smilepay.backend.dto.MerchantDto;
import fr.smilepay.backend.merchant_ws.AddMerchantRequest;
import fr.smilepay.backend.merchant_ws.AddMerchantResponse;
import fr.smilepay.backend.merchant_ws.DeleteMerchantRequest;
import fr.smilepay.backend.merchant_ws.DeleteMerchantResponse;
import fr.smilepay.backend.merchant_ws.MerchantInfo;
import fr.smilepay.backend.merchant_ws.ServiceStatus;
import fr.smilepay.backend.merchant_ws.UpdateMerchantRequest;
import fr.smilepay.backend.merchant_ws.UpdateMerchantResponse;
import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.service.AddService;
import fr.smilepay.backend.service.DeleteService;
import fr.smilepay.backend.service.UpdateService;

/*
 * The endpoint for operation on the table Merchant
 */
@Endpoint
public class MerchantEndPoint {

private static final String NAMESPACE_URI = "http://www.smile.com/merchant-ws";

	
	@Autowired
	AddService addService;
	
	@Autowired
	UpdateService updateService;
	
	@Autowired
	DeleteService deleteService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMerchantRequest")
	@ResponsePayload
	public AddMerchantResponse addMerchant(@RequestPayload AddMerchantRequest request) {
		AddMerchantResponse response = new AddMerchantResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		MerchantDto merDto = new MerchantDto();
		merDto.setNameMerchant(request.getMerchantInfo().getNameMerchant());
		merDto.setLastNameMerchant(request.getMerchantInfo().getLastnameMerchant());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		merDto.setCreateDateMerchant(timestamp);
		
		Merchant addedMerchant = addService.addMerchant(merDto);

		if (addedMerchant == null) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Adding Merchant failed");
			response.setServiceStatus(serviceStatus);
		} else {
			MerchantInfo merchantInfo = new MerchantInfo();
			BeanUtils.copyProperties(addedMerchant, merchantInfo);
			response.setMerchantInfo(merchantInfo);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Merchant Added Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMerchantRequest")
	@ResponsePayload
	public UpdateMerchantResponse updateMerchant(@RequestPayload UpdateMerchantRequest request) {
		UpdateMerchantResponse response = new UpdateMerchantResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		MerchantDto merDto = new MerchantDto();
		merDto.setIdMerchant(request.getMerchantInfo().getIdMerchant());
		merDto.setNameMerchant(request.getMerchantInfo().getNameMerchant());
		merDto.setLastNameMerchant(request.getMerchantInfo().getLastnameMerchant());
		Merchant updateMerchant = updateService.updateMerchant(merDto);

		if (updateMerchant == null) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Updated Merchant failed");
			response.setServiceStatus(serviceStatus);
		} else {
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Updated Merchant Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMerchantRequest")
	@ResponsePayload
	public DeleteMerchantResponse deleteMerchant(@RequestPayload DeleteMerchantRequest request) {
		DeleteMerchantResponse response = new DeleteMerchantResponse();
		ServiceStatus serviceStatus = new ServiceStatus();

		if ( request == null ) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Delete failed");
			response.setServiceStatus(serviceStatus);
		} else {
			MerchantDto merDto = new MerchantDto();
			merDto.setIdMerchant(request.getIdMerchant());
			deleteService.deleteMerchant(merDto);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Deleted Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}
	
}
