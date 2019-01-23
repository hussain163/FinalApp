package com.ecommerce.finalapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecommerce.finalapp.R;
import com.ecommerce.finalapp.response.OrderHistory.ProductListItem;
import com.ecommerce.finalapp.response.OrderHistory.Response;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ProductViewHolder> {


    public ArrayList<List<ProductListItem>> orderHistoryProductList = new ArrayList<>();
    public ArrayList<String> orderIdList = new ArrayList<>();

    public OrderHistoryAdapter(ArrayList<String> orderIdList, ArrayList<List<ProductListItem>> orderHistoryProductList){
        this.orderIdList = orderIdList;
        this.orderHistoryProductList = orderHistoryProductList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_card_recyclerview, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        ((OrderHistoryAdapter.ProductViewHolder) productViewHolder).bind(orderIdList.get(i), orderHistoryProductList.get(i));

    }

    @Override
    public int getItemCount() {
        return orderIdList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView orderId;
        RecyclerView productRecyclerView;

        ArrayList<String> productNameList = new ArrayList<>();
        ArrayList<Integer> productPriceList = new ArrayList<>();
        ArrayList<String> productMerchantList = new ArrayList<>();
        ArrayList<String> productImageUrl = new ArrayList<>();

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = (TextView) itemView.findViewById(R.id.orderId);
            productRecyclerView = (RecyclerView) itemView.findViewById(R.id.singleItem);
        }

        public void bind(String orderId, List<ProductListItem> productListItem){
            this.orderId.setText(orderId);
//            productNameList.add(productListItem.getProductName());
//            productPriceList.add(productListItem.getPrice());
//            productMerchantList.add(productListItem.getProductId());
//            productImageUrl.add(productListItem.getProductImageUrl());

            this.productRecyclerView.setLayoutManager(new LinearLayoutManager(productRecyclerView.getContext(), LinearLayoutManager.HORIZONTAL, true));
            SingleProductListAdapter adapter = new SingleProductListAdapter(productListItem);
            this.productRecyclerView.setAdapter(adapter);

        }

    }
}
