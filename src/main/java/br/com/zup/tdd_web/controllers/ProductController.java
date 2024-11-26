package br.com.zup.tdd_web.controllers;

import br.com.zup.tdd_web.controllers.dtos.ProductRegisterDto;
import br.com.zup.tdd_web.mappers.ProductMapper;
import br.com.zup.tdd_web.model.Product;
import br.com.zup.tdd_web.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    private ResponseEntity<?> registerProduct(@RequestBody ProductRegisterDto productRegisterDto){
        Product product = ProductMapper.toProduct(productRegisterDto);
        try {
            product = productService.register(product);
            return ResponseEntity.status(201).body(ProductMapper.toProductReponseDto(product));
        }catch (RuntimeException exception){
            return ResponseEntity.status(422).body(Map.of("message", exception.getMessage()));
        }
    }
}
