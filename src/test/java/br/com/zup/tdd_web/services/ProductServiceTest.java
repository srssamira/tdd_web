package br.com.zup.tdd_web.services;

import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.model.ProductType;
import br.com.zup.tdd_web.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class ProductServiceTest {
    @MockitoBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    public void testWhenRegisterProductHappyPath(){
        Mockito.when(productRepository.existsByNameAndProductType(Mockito.anyString(), Mockito.any())).thenReturn(false);

        Product product = new Product();
        product.setName("Xablau");
        product.setProductType(ProductType.GUITARRA);

        Product returnByService = productService.register(product);

        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testWhenRegisterProductWithNameAndTypeAlreadyExists(){
        Mockito.when(productRepository.existsByNameAndProductType(Mockito.anyString(), Mockito.any())).thenReturn(true);

        Product product = new Product();
        product.setName("Xablau");
        product.setProductType(ProductType.GUITARRA);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> productService.register(product));

        Assertions.assertEquals("Product already exists", exception.getMessage());
        Mockito.verify(productRepository, Mockito.times(0)).save(Mockito.any());
    }

}
