package fr.smilepay.backend.endpoint;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.smilepay.backend.bind_ws.BindRequest;
import fr.smilepay.backend.bind_ws.BindResponse;
import fr.smilepay.backend.bind_ws.ServiceStatus;
import fr.smilepay.backend.bind_ws.MerchantInfo;
import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.service.BindService;

/*
 * The endpoint for the operation that bind a merchant to a product
 */
@Endpoint
public class BindMerchantToProduitEndPoint {
	
	private static final String NAMESPACE_URI = "http://www.smile.com/bindingMerchantToProduct-ws";

	
	@Autowired
	BindService bindService;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "bindRequest")
	@ResponsePayload
	public BindResponse bindMerchantToProduct(@RequestPayload BindRequest request) {
		BindResponse response = new BindResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Merchant merchantBindToProduct = bindService.bindMerchantToProduct(request.getIdMerchant(), request.getIdProduct());

		if (merchantBindToProduct == null) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Bindind failed");
			response.setServiceStatus(serviceStatus);
		} else {
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Binding Successfully");
			response.setServiceStatus(serviceStatus);
			MerchantInfo merchantInfo = new MerchantInfo();
			BeanUtils.copyProperties(merchantBindToProduct, merchantInfo);
			response.setMerchantInfo(merchantInfo);
		}
		return response;
	}

}
