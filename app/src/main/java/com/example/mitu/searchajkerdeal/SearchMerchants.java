package com.example.mitu.searchajkerdeal;

/**
 * Created by mitu on 4/9/16.
 */
public class SearchMerchants {
    private int ProfileId;
    private String CompanyName;

    public SearchMerchants(int profileId, String companyName) {
        ProfileId = profileId;
        CompanyName = companyName;
    }

    public int getProfileId() {
        return ProfileId;
    }

    public String getCompanyName() {
        return CompanyName;
    }
}
