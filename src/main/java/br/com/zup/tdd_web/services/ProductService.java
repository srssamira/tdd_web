package br.com.zup.tdd_web.services;

import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository; // Injeção de dependencia.

    public Product register(Product product){
        if(productRepository.existsByNameAndProductType(product.getName(), product.getProductType())){
            throw new RuntimeException("Product already exists");
        }
        return productRepository.save(product);
    }
}
