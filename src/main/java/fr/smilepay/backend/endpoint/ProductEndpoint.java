package fr.smilepay.backend.endpoint;

import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import fr.smilepay.backend.dto.ProductDto;
import fr.smilepay.backend.model.Product;
import fr.smilepay.backend.product_ws.AddProductRequest;
import fr.smilepay.backend.product_ws.AddProductResponse;
import fr.smilepay.backend.product_ws.DeleteProductRequest;
import fr.smilepay.backend.product_ws.DeleteProductResponse;
import fr.smilepay.backend.product_ws.ProductInfo;
import fr.smilepay.backend.product_ws.ServiceStatus;
import fr.smilepay.backend.product_ws.UpdateProductRequest;
import fr.smilepay.backend.product_ws.UpdateProductResponse;
import fr.smilepay.backend.service.AddService;
import fr.smilepay.backend.service.DeleteService;
import fr.smilepay.backend.service.UpdateService;

/*
 * The endpoint for operation on the table product
 */
@Endpoint
public class ProductEndpoint {

	private static final String NAMESPACE_URI = "http://www.smile.com/product-ws";
	
	//Logger log = Logger.Fa

	
	@Autowired
	AddService addService;
	
	@Autowired
	UpdateService updateService;
	
	@Autowired
	DeleteService deleteService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addProductRequest")
	@ResponsePayload
	public AddProductResponse addProduct(@RequestPayload AddProductRequest request) {
		AddProductResponse response = new AddProductResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		ProductDto productDto = new ProductDto();
		productDto.setLabel(request.getLabel());
		productDto.setCurrency(request.getCurrency());
		Product addedProduct = addService.addProduct(productDto);

		if (addedProduct == null) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Adding failed");
			response.setServiceStatus(serviceStatus);
		} else {
			ProductInfo productInfo = new ProductInfo();
			BeanUtils.copyProperties(addedProduct, productInfo);
			response.setProductInfo(productInfo);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Content Added Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateProductRequest")
	@ResponsePayload
	public UpdateProductResponse updateArticle(@RequestPayload UpdateProductRequest request) {
		UpdateProductResponse response = new UpdateProductResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(request.getProductInfo(), productDto);
		Product updateProduct = updateService.updateProduct(productDto);

		if (updateProduct == null) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Updated failed");
			response.setServiceStatus(serviceStatus);
		} else {
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Updated Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductRequest")
	@ResponsePayload
	public DeleteProductResponse deleteArticle(@RequestPayload DeleteProductRequest request) {
		DeleteProductResponse response = new DeleteProductResponse();
		ServiceStatus serviceStatus = new ServiceStatus();

		if ( request == null ) {
			serviceStatus.setStatusCode("ERROR");
			serviceStatus.setMessage("Delete failed");
			response.setServiceStatus(serviceStatus);
		} else {
			ProductDto productDto = new ProductDto();
			productDto.setIdProduct(request.getIdProduct());
			deleteService.deleteProduct(productDto);
			serviceStatus.setStatusCode("SUCCESS");
			serviceStatus.setMessage("Deleted Successfully");
			response.setServiceStatus(serviceStatus);
		}
		return response;
	}	
}
