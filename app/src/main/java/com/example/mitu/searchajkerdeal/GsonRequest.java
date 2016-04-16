package com.example.mitu.searchajkerdeal;

import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by mitu on 4/9/16.
 */
public class GsonRequest<T>  extends JsonRequest<T> {
    private static final String TAG = "GsonRequest";
    private final Gson gson;
    private final Class<T> model;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public GsonRequest(int method, String url, Class<T> model, Map<String, String> headers,
                       String requestBody,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        gson = new Gson();
        this.model = model;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String payloadInJson = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(payloadInJson, model),
                    HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (UnsupportedEncodingException | JsonSyntaxException e) {
            Log.e(TAG, "parseNetworkResponse: ", e);
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
