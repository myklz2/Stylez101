//package com.example.stylez;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductStorage {
//
//    private static final String SHARED_PREFS_NAME = "PRODUCTS_STORAGE";
//    private static final String PRODUCTS_KEY = "PRODUCTS_KEY";
//
//    private SharedPreferences mSharedPreferences;
//    private Gson mGson;
//
//    public ProductStorage(Context context) {
//        mSharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
//        mGson = new Gson();
//    }
//
//    public void addProduct(Product product) {
//        List<Product> productList = getProducts();
//        productList.add(product);
//        String productsJson = mGson.toJson(productList);
//        mSharedPreferences.edit().putString(PRODUCTS_KEY, productsJson).apply();
//    }
//
//    public void removeProduct(Product product) {
//        List<Product> productList = getProducts();
//        productList.remove(product);
//        String productsJson = mGson.toJson(productList);
//        mSharedPreferences.edit().putString(PRODUCTS_KEY, productsJson).apply();
//    }
//
//    public List<Product> getProducts() {
//        String productsJson = mSharedPreferences.getString(PRODUCTS_KEY, "");
//        Type type = new TypeToken<List<Product>>() {}.getType();
//        return mGson.fromJson(productsJson, type);
//    }
//
//    public void clear() {
//        mSharedPreferences.edit().remove(PRODUCTS_KEY).apply();
//    }
//}
