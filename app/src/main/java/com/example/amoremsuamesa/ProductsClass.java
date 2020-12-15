package com.example.amoremsuamesa;

public class ProductsClass {

    private String name;
    private String price;

    ProductsClass(String mName,String mPrice){
        this.name = mName;
        this.price = mPrice;
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

}
