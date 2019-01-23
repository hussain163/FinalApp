package com.ecommerce.finalapp.response.OrderHistory;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ProductListItem{

	@SerializedName("productImageUrl")
	private String productImageUrl;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("productId")
	private String productId;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("staticAttributeList")
	private List<StaticAttributeListItem> staticAttributeList;

	@SerializedName("price")
	private int price;

	@SerializedName("category")
	private Category category;

	@SerializedName("productDescription")
	private String productDescription;

	@SerializedName("productName")
	private String productName;

	@SerializedName("productUsp")
	private String productUsp;

	public void setProductImageUrl(String productImageUrl){
		this.productImageUrl = productImageUrl;
	}

	public String getProductImageUrl(){
		return productImageUrl;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setStaticAttributeList(List<StaticAttributeListItem> staticAttributeList){
		this.staticAttributeList = staticAttributeList;
	}

	public List<StaticAttributeListItem> getStaticAttributeList(){
		return staticAttributeList;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setProductDescription(String productDescription){
		this.productDescription = productDescription;
	}

	public String getProductDescription(){
		return productDescription;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductUsp(String productUsp){
		this.productUsp = productUsp;
	}

	public String getProductUsp(){
		return productUsp;
	}

	@Override
 	public String toString(){
		return 
			"ProductListItem{" + 
			"productImageUrl = '" + productImageUrl + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",productId = '" + productId + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",staticAttributeList = '" + staticAttributeList + '\'' + 
			",price = '" + price + '\'' + 
			",category = '" + category + '\'' + 
			",productDescription = '" + productDescription + '\'' + 
			",productName = '" + productName + '\'' + 
			",productUsp = '" + productUsp + '\'' + 
			"}";
		}
}