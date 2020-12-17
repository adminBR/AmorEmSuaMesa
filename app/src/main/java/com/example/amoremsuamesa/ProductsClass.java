package com.example.amoremsuamesa;

import android.widget.ImageView;

public class ProductsClass {
    //private ImageView productImage;
    private int id;
    private String name;
    private String price;

    ProductsClass(int ID, String mName,String mPrice){
        this.id = ID;
        this.name = mName;
        this.price = mPrice;
        //this.productImage = iv;
    }

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public ImageView getProductImage() {return productImage;}

   // public void setProductImage(ImageView productImage) {this.productImage = productImage;}

}
