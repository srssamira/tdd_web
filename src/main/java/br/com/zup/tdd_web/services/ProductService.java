package br.com.zup.tdd_web.services;

import br.com.zup.tdd_web.controllers.FinishStorageException;
import br.com.zup.tdd_web.controllers.NotFoundProductStorageException;
import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Product updateStorage(String idProduct){
        Optional<Product> productOptional = productRepository.findById(idProduct);
        if(productOptional.isEmpty()){
            throw new NotFoundProductStorageException("Product not find");
        }

        Product product = productOptional.get();

        if(product.getStorage() <= 0){
            throw new FinishStorageException("Don't have this item on storage");
        }

        product.setStorage(product.getStorage()-1);
        return productRepository.save(product);
    }
}
