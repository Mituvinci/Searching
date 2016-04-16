package com.example.mitu.searchajkerdeal;

/**
 * Created by mitu on 4/9/16.
 */
public class SearchModels {

        private SearchDeals[] Deals;
        private SearchCategories[] Categories;
        private SearchMerchants[] Merchants;

    public SearchModels(SearchDeals[] deals, SearchCategories[] categories, SearchMerchants[] merchants) {
        Deals = deals;
        Categories = categories;
        Merchants = merchants;
    }

    public SearchDeals[] getDeals() {
        return Deals;
    }

    public SearchCategories[] getCategories() {
        return Categories;
    }

    public SearchMerchants[] getMerchants() {
        return Merchants;
    }
}
