package br.com.zup.tdd_web.controllers.dtos;

import br.com.zup.tdd_web.model.Category;
import br.com.zup.tdd_web.model.ProductType;

import java.util.List;

public class ProductResponseDto {
    private String id;
    private String name;
    private float price;
    private ProductType productType;
    private List<Category> categories;
    private String description;
    private int storage;

    public ProductResponseDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}
