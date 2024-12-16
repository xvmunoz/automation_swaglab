package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    String pName = "Sauce Labs Backpack";
    String pPrice = "$29.99";
    String bStatus = "Remove";
    List<String> productDetails = new ArrayList<>();

    public List<String> addProductDetails(String name, String price, String buttonStatus){
        productDetails.add(name);
        productDetails.add(price);
        productDetails.add(buttonStatus);
        return productDetails;
    }

    public static void main(String[] args) {

    }


}