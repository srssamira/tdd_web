package br.com.zup.tdd_web.services;

import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.model.ProductType;
import br.com.zup.tdd_web.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ProductServiceTest {
    @MockitoBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp(){
        this.product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Xablau");
        product.setProductType(ProductType.GUITARRA);
        product.setStorage(10);
    }

    @Test
    public void testWhenRegisterProductHappyPath(){
        Mockito.when(productRepository.existsByNameAndProductType(Mockito.anyString(), Mockito.any())).thenReturn(false);
        Product returnByService = productService.register(product);

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void testWhenRegisterProductWithNameAndTypeAlreadyExists(){
        Mockito.when(productRepository.existsByNameAndProductType(Mockito.anyString(), Mockito.any())).thenReturn(true);

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> productService.register(product));

        Assertions.assertEquals("Product already exists", exception.getMessage());
        Mockito.verify(productRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void testWhenStorageIsUpdateOnHappyPath(){
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product productTest = productService.updateStorage(product.getId());

        Assertions.assertEquals(9, productTest.getStorage());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

}
