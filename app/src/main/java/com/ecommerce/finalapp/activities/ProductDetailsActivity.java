package com.ecommerce.finalapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.ecommerce.finalapp.R;
import com.ecommerce.finalapp.adapter.staticAttributesListAdapter;
import com.ecommerce.finalapp.controller.AppControl;
import com.ecommerce.finalapp.controller.IApi;
import com.ecommerce.finalapp.request.AddToCart;
import com.ecommerce.finalapp.response.ProductDetailAndMerchantList.ProductDetailsResponse;

import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailsActivity extends AppCompatActivity {
    String pId;
    String mId;
    int uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);


        final RecyclerView recyclerView=findViewById(R.id.attributesRV);
        final ImageView productImageView = (ImageView) findViewById(R.id.imageView);
        final TextView productName = (TextView) findViewById(R.id.productName);
        final TextView productPrice = (TextView) findViewById(R.id.productPrice);
        final TextView productUsp = (TextView) findViewById(R.id.productUsp);
        final TextView soldBy = (TextView) findViewById(R.id.merchantName);
        final TextView description=(TextView) findViewById(R.id.productDescription);
        final Map<String, String> attributeList = new HashMap<>();

        final SharedPreferences sharedPreferences= getSharedPreferences("package com.example.projectfinal;", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();


        OkHttpClient client = new OkHttpClient.Builder().build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://demo2805796.mockable.io/productMerchants/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IApi iApiClass = retrofit.create(IApi.class);
        iApiClass.getProductDetails(getIntent().getStringExtra("productId")).enqueue(new Callback<ProductDetailsResponse>(){
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {

                System.out.println(response.body().getProductImageUrl());
                Glide.with(ProductDetailsActivity.this).load(response.body().getProductImageUrl()).into(productImageView);
                productName.setText(response.body().getProductName());
                productPrice.setText(Double.toString(response.body().getMerchantDTOList().get(0).getPrice()));
                productUsp.setText(response.body().getProductUsp());
                soldBy.setText(response.body().getMerchantDTOList().get(0).getName());
                description.setText(response.body().getProductDescription());
                recyclerView.setLayoutManager( new LinearLayoutManager(ProductDetailsActivity.this));

                pId = response.body().getProductId();
                uId = sharedPreferences.getInt("user", 0);
                mId = response.body().getMerchantDTOList().get(0).getMerchantId();
                for(int i=0;i<response.body().getStaticAttributeList().size();i++){
                    attributeList.put(response.body().getStaticAttributeList().get(i).getAttribute(),response.body().getStaticAttributeList().get(i).getAttributeDescription());
                    System.out.println(attributeList.get(response.body().getStaticAttributeList().get(i).getAttribute()));
                }
                staticAttributesListAdapter staticAttributesListAdapter=new staticAttributesListAdapter(attributeList);
                recyclerView.setAdapter(staticAttributesListAdapter);
            }
            @Override
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                System.out.println("Failure");
            }
        });

        final Button addToCart = (Button) findViewById(R.id.addToCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToCart cartObject = new AddToCart(String.valueOf(uId), pId, mId);
                sendNetworkRequest(cartObject);
            }
        });

    }
    public void sendNetworkRequest(AddToCart cartObject){
        Retrofit.Builder builder =new Retrofit.Builder()
                .baseUrl("http://demo5585107.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        IApi iApiClass = retrofit.create(IApi.class);
        Call<AddToCart> call = iApiClass.addToCart(cartObject);
        call.enqueue(new Callback<AddToCart>() {
            @Override
            public void onResponse(Call<AddToCart> call, Response<AddToCart> response) {
                Toast.makeText(ProductDetailsActivity.this, "Item Added to Cart!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<AddToCart> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Can't Add Item to Cart, contact developer!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
