package com.ecommerce.finalapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ecommerce.finalapp.R;
import com.ecommerce.finalapp.adapter.HomePageAdapter;
import com.ecommerce.finalapp.adapter.OrderHistoryAdapter;
import com.ecommerce.finalapp.controller.IApi;
import com.ecommerce.finalapp.response.HomePageResponse.HomePageResponse;
import com.ecommerce.finalapp.response.OrderHistory.ProductListItem;
import com.ecommerce.finalapp.response.OrderHistory.Response;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderHistory extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<String> orderIdList = new ArrayList<>();
    private ArrayList<List<ProductListItem>> productListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        mRecyclerView = (RecyclerView) findViewById(R.id.cartRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        OkHttpClient client = new OkHttpClient.Builder().build();
        //http://10.0.2.2:8000 or http://localhost:8000
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://demo2805796.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();
        IApi iApi = retrofit.create(IApi.class);

        iApi.getOrderHistory().enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                for(int i=0;i<response.body().size();i++) {
                    orderIdList.add(response.body().get(i).getOrderId());
                    productListItems.add(response.body().get(i).getProductList());
                }
                mAdapter = new OrderHistoryAdapter(orderIdList, productListItems);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {
                System.out.println("Phat gaya");
            }
        });
    }


}

