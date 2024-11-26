package br.com.zup.tdd_web.controllers;


import br.com.zup.tdd_web.controllers.dtos.ProductRegisterDto;
import br.com.zup.tdd_web.model.Category;
import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.model.ProductType;
import br.com.zup.tdd_web.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
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
        product.setPrice(1000f);
        product.setName("Guitarra Gibson");
        product.setProductType(ProductType.GUITARRA);
    }

    @Test
    public void testWhenRegisterProductHappyPath() throws Exception {
        ProductRegisterDto productRegisterDto = new ProductRegisterDto("Guitarra Gibson", 1000f,
                ProductType.GUITARRA, List.of(Category.CORDA), "Guitarra guitarrosa", 3);
        String json = mapper.writeValueAsString(productRegisterDto);

        Mockito.when(productService.register(Mockito.any(Product.class))).thenReturn(product);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(product.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Guitarra Gibson")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(1000.0)));
    }
}
