package com.example.mitu.searchajkerdeal;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by mitu on 4/9/16.
 */
public class SearchRequest {

    private static final String TAG = "SearchRequest";


    public void getSearchRequest(final Context context, String keyword,final RequestCallback<SearchModels> requestCallback) {
        String url ="http://api.ajkerdeal.com/Search/SearchByKeywords";
        /*String body = "{\"Keywords\" :\"mobile\"}";*/
        String body = "{\"Keywords\" :\""+keyword+"\"}";

        Log.d("URL",url);

        if(!Connectivity.isInternetConnected(context)) {
            Log.e(TAG, "getDealDetails: No Internet");
            return;
        }
        GsonRequest<SearchModels> serviceCall = new GsonRequest<>(Request.Method.POST, url,
                SearchModels.class, null, body, new Response.Listener<SearchModels>() {
            @Override
            public void onResponse(SearchModels response) {
                requestCallback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
            }
        });
        serviceCall.setRetryPolicy(NetworkRetryPolicy.getRetryPolicyForFiveSecond());
        Volley.getInstance(context).addToRequestQueue(serviceCall);
    }
}
