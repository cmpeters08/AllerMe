package com.cmpeters08.lc101final.models;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cmp on 7/24/2017.
 */
public class CompareIngredients {

    //@NotNull
    private static String productOne;

    //@NotNull
    private static String productTwo;



    public static ArrayList<String> commonItems(){

        String[] productOneSplit = getProductOne().toLowerCase().split(", ");
        ArrayList<String> productOneList = new ArrayList<String>(Arrays.asList(productOneSplit));
        String[] productTwoSplit = getProductTwo().toLowerCase().split(", ");
        ArrayList<String> productTwoList = new ArrayList<String>(Arrays.asList(productTwoSplit));

        ArrayList<String> commonItems = new ArrayList();

        for (String item : productOneList){
            if(productTwoList.contains(item)){
                commonItems.add(item);
            }
        }
        if (commonItems.isEmpty()){
            commonItems.add("No Matches Found");
        }
        return commonItems;
    }


    public static String getProductOne() {
        return productOne;
    }

    public static void setProductOne(String productOne) {
        CompareIngredients.productOne = productOne;
    }

    public static String getProductTwo() {
        return productTwo;
    }

    public static void setProductTwo(String productTwo) { CompareIngredients.productTwo= productTwo;}
}

