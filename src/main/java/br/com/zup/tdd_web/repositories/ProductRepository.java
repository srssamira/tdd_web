package br.com.zup.tdd_web.repositories;

import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByNameAndProductType(String name, ProductType productType);
}
