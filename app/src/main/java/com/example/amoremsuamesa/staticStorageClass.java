package com.example.amoremsuamesa;

import java.util.ArrayList;
import java.util.List;

public class staticStorageClass {

    public static List<ProductsClass> ListaCompletaProdutos = new ArrayList<>();
    public static List<ProductsClass> carrinho = new ArrayList<>();
    public static ProductsClass selectedProduct;

    public static String usuarioID = "";
}
