package com.example.mitu.searchajkerdeal;

/**
 * Created by mitu on 4/9/16.
 */
public class SearchCategories {
    private int CategoryId;
    private int HasProduct;
    private String Category;

    public SearchCategories(int categoryId, int hasProduct, String category) {
        CategoryId = categoryId;
        HasProduct = hasProduct;
        Category = category;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public int getHasProduct() {
        return HasProduct;
    }

    public String getCategory() {
        return Category;
    }
}
