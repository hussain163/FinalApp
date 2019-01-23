package com.ecommerce.finalapp.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ecommerce.finalapp.R;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.ecommerce.finalapp.adapter.ProductByCategoryAdapter;
import com.ecommerce.finalapp.controller.IApi;
import com.ecommerce.finalapp.response.ProductDetailsWithPriceAndCount.ProductDetailPriceAndCount;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductByCategory extends AppCompatActivity {


    private List<String> imageUrlList = new ArrayList<>();
    private List<String> nameList = new ArrayList<>();
    private List<String> uspList = new ArrayList<>();
    private List<String> productIdList = new ArrayList<>();
    private List<Integer> highestPriceList = new ArrayList<>();
    private List<Integer> lowestPriceList = new ArrayList<>();
    private List<Integer> merchantCountList = new ArrayList<>();



    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category);
        //System.out.println(getIntent().getStringExtra("categoryId"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        OkHttpClient client = new OkHttpClient.Builder().build();
//http://10.0.2.2:8000 or http://localhost:8000
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://molbhaav-product.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
        IApi iApi = retrofit.create(IApi.class);

        iApi.productsByCategory(getIntent().getStringExtra("categoryId")).enqueue(new Callback<List<ProductDetailPriceAndCount>>() {
            @Override
            public void onResponse(Call<List<ProductDetailPriceAndCount>> call, Response<List<ProductDetailPriceAndCount>> response) {
                for(int i=0;i<response.body().size();i++) {
                    uspList.add(String.valueOf(i));
                    nameList.add(response.body().get(i).getProductName());
                    imageUrlList.add(response.body().get(i).getProductImageUrl());
                    productIdList.add(response.body().get(i).getProductId());
                    highestPriceList.add(response.body().get(i).getHighestPrice());
                    lowestPriceList.add(response.body().get(i).getLowestPrice());
                    merchantCountList.add(response.body().get(i).getMerchantCount());
                }
                mAdapter = new ProductByCategoryAdapter(productIdList, nameList, imageUrlList, uspList,highestPriceList,lowestPriceList,merchantCountList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<ProductDetailPriceAndCount>> call, Throwable t) {

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        System.out.println("Option menu created");
        return true;
    }
}