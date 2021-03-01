package fr.smilepay.backend.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.smilepay.backend.model.Merchant;
import fr.smilepay.backend.model.Product;
import fr.smilepay.backend.repository.MerchantRepository;
import fr.smilepay.backend.repository.ProductRepository;
import fr.smilepay.backend.service.BindService;

@Service
@Transactional
public class BindServiceImpl implements BindService {

	@Autowired
	MerchantRepository merchantRepository;

	@Autowired
	ProductRepository productRepository;

	
	@Override
	public Merchant bindMerchantToProduct(Long idMerchant, Long IdProduct) {
		Optional<Merchant> mer = merchantRepository.findById(idMerchant);
		Optional<Product> pro = productRepository.findById(IdProduct);

		if (mer.isPresent() && pro.isPresent()) {
			if (mer.get().getProducts() != null) {
				mer.get().getProducts().add(pro.get());
			} else {
				List<Product> products = new ArrayList<Product>();
				products.add(pro.get());
				mer.get().setProducts(products);
			}
			
			return merchantRepository.save(mer.get());
		}
		else {
			return null;
		}
	}
}

