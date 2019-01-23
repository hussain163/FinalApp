package com.ecommerce.finalapp.response.OrderHistory;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Response{

	@SerializedName("placedOn")
	private String placedOn;

	@SerializedName("orderId")
	private String orderId;

	@SerializedName("userId")
	private String userId;

	@SerializedName("productList")
	private List<ProductListItem> productList;

	public void setPlacedOn(String placedOn){
		this.placedOn = placedOn;
	}

	public String getPlacedOn(){
		return placedOn;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setProductList(List<ProductListItem> productList){
		this.productList = productList;
	}

	public List<ProductListItem> getProductList(){
		return productList;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"placedOn = '" + placedOn + '\'' + 
			",orderId = '" + orderId + '\'' + 
			",userId = '" + userId + '\'' + 
			",productList = '" + productList + '\'' + 
			"}";
		}
}