package br.com.zup.tdd_web.controllers;


import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.model.ProductType;
import br.com.zup.tdd_web.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private ObjectMapper mapper;
    private Product product;


    @BeforeEach
    public void setUp(){
        mapper = new ObjectMapper();

        product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setStorage(10);
        product.setName("Guitarra Gibson");
        product.setProductType(ProductType.GUITARRA);
    }

}
