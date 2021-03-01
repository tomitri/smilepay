package fr.smilepay.backend.configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@ComponentScan(basePackages = {"fr.smilepay.backend"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"fr.smilepay.backend.repository"})
@EnableWs
public class CustomWebMVConfiguration {

	@Resource
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url(env.getRequiredProperty("smilepay.db.url"));
		dataSourceBuilder.username(env.getRequiredProperty("smilepay.db.username"));
		dataSourceBuilder.password(env.getRequiredProperty("smilepay.db.password"));
		//chercher comment jouer les script sql dans la base postgre au demarage de l'app. 
		return dataSourceBuilder.build();
	}

	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgreEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(getDataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan("fr.smilepay");
		HibernateJpaVendorAdapter vendorAdaptater = new HibernateJpaVendorAdapter();
		vendorAdaptater.setDatabase(Database.POSTGRESQL);
		vendorAdaptater.setGenerateDdl(false);
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptater);
		return entityManagerFactoryBean;
	}
	
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/soapws/*");
	}
	
	
	@Bean(name = "product")
	public DefaultWsdl11Definition productWsdl11Definition(XsdSchema productSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ProductPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.smile.com/product-ws");
		wsdl11Definition.setSchema(productSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema productSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/product.xsd"));
	}
	
	@Bean(name = "merchant")
	public DefaultWsdl11Definition merchantWsdl11Definition(XsdSchema merchantSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("MerchantPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.smile.com/merchant-ws");
		wsdl11Definition.setSchema(merchantSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema merchantSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/merchant.xsd"));
	}
	
	@Bean(name = "address")
	public DefaultWsdl11Definition addressWsdl11Definition(XsdSchema addressSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("AddressPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.smile.com/address-ws");
		wsdl11Definition.setSchema(addressSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema addressSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/address.xsd"));
	}
	
	@Bean(name = "bind")
	public DefaultWsdl11Definition bindWsdl11Definition(XsdSchema bindSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("BindPort");
		wsdl11Definition.setLocationUri("/soapws");
		wsdl11Definition.setTargetNamespace("http://www.smile.com/bindingMerchantToProduct-ws");
		wsdl11Definition.setSchema(bindSchema);
		return wsdl11Definition;
	}
	
	@Bean
	public XsdSchema bindSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/bind.xsd"));
	}
	
}
