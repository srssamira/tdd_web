package br.com.zup.tdd_web.mappers;

import br.com.zup.tdd_web.controllers.dtos.ProductRegisterDto;
import br.com.zup.tdd_web.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductMapper {

    public static Product toProduct(ProductRegisterDto productRegisterDto){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(productRegisterDto, Product.class);
    }

    public static ProductReponseDto toProductReponseDto(Product product){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(product, ProductReponseDto.class);
    }
}
