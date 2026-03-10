package com.example.demo.dto.response;

public class ProductResponseDTO {
    private int id;

    private String nameProduct;

    private String descriptionProduct;

    private double priceProduct;

    private int quantity;

    private String categoryProduct;

    private String imageLink;

    private String subCategoryProduct;

    public ProductResponseDTO(int id, String nameProduct, String descriptionProduct, double priceProduct, int quantity, String categoryProduct, String imageLink, String subCategoryProduct) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.priceProduct = priceProduct;
        this.quantity = quantity;
        this.categoryProduct = categoryProduct;
        this.imageLink = imageLink;
        this.subCategoryProduct = subCategoryProduct;
    }

    public ProductResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getSubCategoryProduct() {
        return subCategoryProduct;
    }
    public void setSubCategoryProduct(String subCategoryProduct) {
        this.subCategoryProduct = subCategoryProduct;
    }
}
