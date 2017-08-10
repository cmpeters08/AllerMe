package com.cmpeters08.lc101final.models;

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

    private static ArrayList<Trigger> existingTriggers;



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


    public static ArrayList<String> compareNewItem(){

        String[] productOneSplit = getProductOne().toLowerCase().split(", ");
        ArrayList<String> productOneList = new ArrayList<String>(Arrays.asList(productOneSplit));



        ArrayList<String> moreCommonItems = new ArrayList();

      // String existingTriggerStr = existingTriggers.toString();

        for (String item : productOneList){
            if(getExistingTriggers().contains(item)){
                moreCommonItems.add(item);
            }
        }
        if (moreCommonItems.isEmpty()){
            moreCommonItems.add("No Matches Found");
        }
        return moreCommonItems;
    }

    public static ArrayList<Trigger> getExistingTriggers() {
        return existingTriggers;
    }

    public static void setExistingTriggers(ArrayList<Trigger> existingTriggers) {
        CompareIngredients.existingTriggers = existingTriggers;
    }
}

