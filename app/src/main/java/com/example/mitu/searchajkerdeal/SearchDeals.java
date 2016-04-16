package com.example.mitu.searchajkerdeal;

/**
 * Created by mitu on 4/9/16.
 */
public class SearchDeals {
    private int DealId;
    private double DealPrice;
    private String DealTitle;
    private  String FolderName;
    private  String AccountsTitle;
    private String mImage;



    public SearchDeals(int dealId, double dealPrice , String dealTitle, String folderName, String accountsTitle) {
        DealId = dealId;
        DealTitle = dealTitle;
        FolderName = folderName;
        AccountsTitle = accountsTitle;
        DealPrice = dealPrice;

        mImage = "http://www.ajkerdeal.com/Images/Deals/"+FolderName+"/smallImage1.jpg";
    }

    public int getDealId() {
        return DealId;
    }

    public String getDealTitle() {
        return DealTitle;
    }

    public String getFolderName() {
        return FolderName;
    }

    public String getAccountsTitle() {
        return AccountsTitle;
    }
    public String getmImage(){return  mImage;}
    public double getDealPrice() { return DealPrice; }

    @Override
    public String toString() {
        return "SearchDeals{" +
                "DealId=" + DealId +
                ", DealTitle='" + DealTitle + '\'' +
                ", FolderName='" + FolderName + '\'' +
                ", AccountsTitle='" + AccountsTitle + '\'' +
                '}';
    }
}
