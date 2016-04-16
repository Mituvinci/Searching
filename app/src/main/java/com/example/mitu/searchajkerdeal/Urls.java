package com.example.mitu.searchajkerdeal;

/**
 * Created by mitu on 4/9/16.
 */
public class Urls {
    public static final String HOST = "http://api.ajkerdeal.com";

        private static String[] urls = {

                "/Search/SearchByKeywords"                     //1

        };

    public static String getUrl(UrlType urlType){
        int index = urlType.getValue();
        return HOST + urls[index];
    }

    public enum UrlType {
        SEARCH_ITEM(0);


        private final int value;

        UrlType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
