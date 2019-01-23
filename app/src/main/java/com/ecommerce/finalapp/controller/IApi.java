package com.ecommerce.finalapp.controller;



import com.ecommerce.finalapp.request.AddToCart;
import com.ecommerce.finalapp.request.LoginRequest;
import com.ecommerce.finalapp.request.SignInRequest;
import com.ecommerce.finalapp.response.HomePageResponse.HomePageResponse;
import com.ecommerce.finalapp.response.LoginResponse;
import com.ecommerce.finalapp.response.OrderHistory.Response;
import com.ecommerce.finalapp.response.ProductDetailAndMerchantList.ProductDetailsResponse;
import com.ecommerce.finalapp.response.ProductDetailsWithPriceAndCount.ProductDetailPriceAndCount;
import com.ecommerce.finalapp.response.SignInResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApi {


    @GET("users/profile/{userId}")
    public Call<SignInResponse> getuser(@Path("userId") int userId);
    @PUT("/users/editProfile")
    public Call<SignInResponse> edit(@Body SignInResponse signInResponse);

    @POST("/users/signIn")
    public Call<SignInResponse> sign(@Body SignInRequest signInRequest);

    @POST("/users/login")
    public Call<LoginResponse>  login(@Body LoginRequest loginRequest);


    /*
    *
    * HomePage
    *
    * */
    @GET("/category/")
    public Call<List<HomePageResponse>> getCategories();

    @GET("/products/findByCategory/{categoryId}")

    public Call<List<ProductDetailPriceAndCount>> productsByCategory(@Path("categoryId") String categoryId);


    @GET("/product/query")
    public Call<List<ProductDetailPriceAndCount>> searchProductsResult(@Query("queryText") String searchText);

    /*
     *
     * Product Details Page
     *
     * */

    @GET("/productMerchants/getByProductId/{productId}")
    public Call<ProductDetailsResponse> getProductDetails(@Path("productId") String productId);

    @POST("carts/addToCart")
    public Call<AddToCart> addToCart(@Body AddToCart cart );

    @GET("/user/userId/1")
    public Call<List<Response>> getOrderHistory();


}
