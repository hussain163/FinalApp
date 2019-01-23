package com.ecommerce.finalapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecommerce.finalapp.R;
import com.ecommerce.finalapp.response.OrderHistory.ProductListItem;

import java.util.ArrayList;
import java.util.List;

public class SingleProductListAdapter extends RecyclerView.Adapter<SingleProductListAdapter.SingleProductViewHolder> {

//    private List<String> productNameList = new ArrayList<>();
//    private List<String> productPriceList = new ArrayList<>();
//    private List<String> productImageUrlList = new ArrayList<>();
//    private List<String> merchantNameList = new ArrayList<>();

    private List<ProductListItem> productListItems = new ArrayList<>();

    public SingleProductListAdapter(List<ProductListItem> productListItems){
        this.productListItems = productListItems;
    }

//    public SingleProductListAdapter(List<String> productNameList,List<String> productPriceList,List<String> productImageUrlList,List<String> merchantNameList){
//        this.productNameList = productNameList;
//        this.productPriceList = productPriceList;
//        this.productImageUrlList = productImageUrlList;
//        this.merchantNameList = merchantNameList;
//    }

    @NonNull
    @Override
    public SingleProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_history_single_card, viewGroup, false);
        return new SingleProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleProductViewHolder singleProductViewHolder, int i) {
        ((SingleProductViewHolder) singleProductViewHolder).bind(productListItems.get(i));
    }

    @Override
    public int getItemCount() {
        return productListItems.size();
    }

    public class SingleProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productImageView;
        TextView productName;
        TextView productPrice;
        TextView merchantName;
        public SingleProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = (ImageView) itemView.findViewById(R.id.productimageView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            merchantName = (TextView) itemView.findViewById(R.id.merchantName);
        }
        public void bind(ProductListItem productListItem){
            this.productPrice.setText(String.valueOf(productListItem.getPrice()));
            this.productName.setText(productListItem.getProductName());
            this.merchantName.setText(productListItem.getMerchantId());
            Glide.with(productImageView.getContext()).load(productListItem.getProductImageUrl()).into(productImageView);
        }
    }

}
