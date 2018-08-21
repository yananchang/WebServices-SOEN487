/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.g4.sc.retailer.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import soen487.g4.sc.retailer.business.Retailer;
import soen487.g4.sc.retailer.business.Root;

/**
 *  Calls the 4 warehouses on startup to get all the products in their inventories and stores them in its own catalogue "file.json"
 */
@ApplicationPath("/retailer/")
public class RetailerStarterRestApi extends Application{

    private Root root;
    private String[] warehouseNames = {"1","2","3","4"};
    private Retailer retailer = new Retailer();
    public static final String RETAILER_CATALOGUE = "C:\\Users\\Yanan Chang\\Desktop\\General Folder\\Concordia\\2018 Winter\\SOEN487\\code\\retailer\\file.json";
    //public static final String RETAILER_CATALOGUE = "C:\\Users\\yousra\\Documents\\NetBeansProjects\\r";

    /**
     * @desc Default constructor: only one warehouse is available get the item
     * list from the warehouse
     */
    public RetailerStarterRestApi(){
         
    	//load warehouse arrays and combine them
    	Root[] _rootArr = new Root[warehouseNames.length];
    	
    	for(int i = 0; i < warehouseNames.length;i++) {
    		_rootArr[i] = retailer.loadSingleWarehouse(warehouseNames[i]);
    		
    	}
    	
    	root = retailer.combineFromWarehouses(_rootArr);
//        root = retailer.loadSingleWarehouse(warehouseNames[0]);
        
        //write to json file
        Gson _gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            //TODO check file path
            FileWriter writer = new FileWriter(RETAILER_CATALOGUE);
            _gson.toJson(root, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
