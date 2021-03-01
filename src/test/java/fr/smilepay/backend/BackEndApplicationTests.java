package fr.smilepay.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.xml.transform.StringSource;

import fr.smilepay.backend.configuration.CustomWebMVConfiguration;
import fr.smilepay.backend.product_ws.AddProductResponse;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { CustomWebMVConfiguration.class })
class BackEndApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
    ApplicationContext applicationContext;

    @Autowired
    ResourceLoader resourceLoader;
    
    private MockWebServiceClient mockClient;
    private Resource xsdSchema = new ClassPathResource("xsd/product.xsd");

    @Before
    public void init(){
    	Assert.assertNotNull(applicationContext);
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }
    
    @Test
    public void addingProduct() throws IOException {

        Source responsePayload = new StringSource(
                "<ns2:getBeerResponse xmlns:ns2=\"https://memorynotfound.com/beer\"></ns2:getBeerResponse>");
        
        final RequestCreator requestCreator; // Creator for the request
//      final ResponseMatcher responseMatcher; // Matcher for the response
//
      //Resource requestPayLoad = resourceLoader.getResource("classpath:/add-product-request.xml");
      //Resource requestPayLoad = new ClassPathResource("resources/request/add-product-request.xml");
        Source requestPayLoad = (Source) getClass().getClassLoader().getResourceAsStream(
            "add-product-request.xml.xml");
//      Resource responsePayload = resourceLoader.getResource("classpath:com/lmig/dragon/controller/test/responsePayload.xml");
//
//      requestCreator = RequestCreators
//              .withSoapEnvelope(requestPayLoad);

//      responseMatcher = ResponseMatchers.soapEnvelope(responsePayload);
//
//      // Calls the endpoint
//      mockClient.sendRequest(requestCreator).andExpect(responseMatcher);

        mockClient
                .sendRequest(withPayload(requestPayLoad))
                //.sendRequest(requestCreator)
                .andExpect(noFault())
//                .andExpect(payload(responsePayload))
                .andExpect(validPayload(xsdSchema));
    }
    
}
