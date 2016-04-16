package com.example.mitu.searchajkerdeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private  EditText mSearchEditText;
    private String search="";
    private String mdeals;
    private Button button;

    private RecyclerView mRecyclerViewSearch;
    private SearchAdapter mAdapterSearch;
    private RecyclerView.LayoutManager mLayoutManagerSearch;

    public static ArrayList<SearchModels> searchModelsesArrayList = new ArrayList<>();
    public static ArrayList<SearchDeals> searchDealsArrayList = new ArrayList<>();
    public static ArrayList<SearchCategories> searchCategoriesArrayList = new ArrayList<>();
    public static ArrayList<SearchMerchants> searchMerchantsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchEditText = (EditText) findViewById(R.id.editText);
        mLayoutManagerSearch = new LinearLayoutManager(this);

        button = (Button) findViewById(R.id.button);


       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = mSearchEditText.getText().toString();
                Log.d("String search  ",search);
                searchDeal(search);
                hideKeyboard(v);
            }
        });*/



        mSearchEditText.addTextChangedListener(new TextWatcher() {
            private Timer timer=new Timer();
            private final long DELAY = 1500; // milliseconds
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("beforeTextChanged ",s+"");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged ",s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        search = mSearchEditText.getText().toString();
                        Log.d("String search  ",search);

                        searchDeal(search);
                    }
                },DELAY);

            }
        });

    }




    public void searchDeal(String search) {
        new SearchRequest().getSearchRequest(getApplicationContext(),search,
                new RequestCallback<SearchModels>() {


                    @Override
                    public void onSuccess(SearchModels data) {

                        Log.e("Data....?",data+"");
                        SearchDeals[] searchsdeals = data.getDeals();
                        SearchMerchants[] searchMerchantses = data.getMerchants();
                        SearchCategories[] searchCategories = data.getCategories();

                        SearchModels searchModels =   new SearchModels(data.getDeals(),data.getCategories(), data.getMerchants());
                        getDataDeals(searchModels.getDeals());
                        getDataCategories(searchModels.getCategories());
                        //getDataMerchant(searchModels.getMerchants());
                        Log.e("Deals" ,searchsdeals.toString()+"");
                        Log.e("Categories" ,searchModels.getCategories()+"");
                        mRecyclerViewSearch = (RecyclerView) findViewById(R.id.recycleSearch);
                        mRecyclerViewSearch.setHasFixedSize(true);
                        mRecyclerViewSearch.setLayoutManager(mLayoutManagerSearch);
                        mAdapterSearch = new SearchAdapter(getDataDeals(searchModels.getDeals()),getDataCategories(searchModels.getCategories()),getApplicationContext());
                        mRecyclerViewSearch.setAdapter(mAdapterSearch);
                       // mRecyclerViewSearch.setNestedScrollingEnabled(false);



                    }
                });
    }


    private ArrayList<SearchDeals> getDataDeals(SearchDeals[] response) {
        ArrayList<SearchDeals> searchDealses = new ArrayList<>();

        int index = 0;
        for (SearchDeals obj : response) {

            searchDealsArrayList.add(obj);
            Log.d("DEALS :", obj.getDealId()+","+obj.getDealPrice()+","+obj.getDealTitle()+" ,"+obj.getFolderName()+","+obj.getAccountsTitle());
            searchDealses.add(index, new SearchDeals(obj.getDealId(),obj.getDealPrice(),obj.getDealTitle(),obj.getFolderName(),obj.getAccountsTitle()));

            index++;
        }

        return searchDealses;
    }


    private ArrayList<SearchCategories> getDataCategories(SearchCategories[] response) {
        ArrayList<SearchCategories> searchCategory = new ArrayList<>();

        int index = 0;
        for (SearchCategories obj : response) {

            searchCategoriesArrayList.add(obj);
            Log.d("Categories :", obj.getCategoryId()+","+obj.getHasProduct()+" ,"+obj.getCategory());
            searchCategory.add(index, new SearchCategories(obj.getCategoryId(),obj.getHasProduct(),obj.getCategory()));

            index++;
        }

        return searchCategory;
    }

 /*   private ArrayList<SearchMerchants> getDataMerchant(SearchMerchants[] response) {
        ArrayList<SearchMerchants> searchMerchant = new ArrayList<>();

        int index = 0;
        for (SearchMerchants obj : response) {

            searchMerchantsArrayList.add(obj);
            Log.d("Merchant :", obj.getProfileId()+","+obj.getCompanyName());
            searchMerchant.add(index, new SearchMerchants(obj.getProfileId(),obj.getCompanyName()));

            index++;
        }

        return searchMerchant;
    }*/

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
