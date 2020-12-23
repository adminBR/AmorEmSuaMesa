package com.example.amoremsuamesa;

import java.util.ArrayList;
import java.util.List;

public class ProductsClass {
    //private ImageView productImage;
    private String dbID = "";
    private int id = 0;
    private String name = "";
    private String descricao = "";
    private List<String> imgUrl = new ArrayList<>();
    private String price = "";

    ProductsClass(int ID, String mName,String mPrice){ //buttons construct
        this.id = ID;
        this.name = mName;
        this.price = mPrice;
    }
    ProductsClass(String dId, int ID, String mName, String mDescricao, List<String> mImgUrl,String mPrice){ //buttons construct
        this.dbID = dId;
        this.id = ID;
        this.name = mName;
        this.descricao = mDescricao;
        this.imgUrl = mImgUrl;
        this.price = mPrice;
    }


    public String getDBID() {
        return dbID;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return name;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getImages() {
        return imgUrl;
    }

    public String getPreco() {
        return price;
    }


    public void setId(int ID) {
        this.id = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setImages(List<String> imgs) {
        this.imgUrl = imgs;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
