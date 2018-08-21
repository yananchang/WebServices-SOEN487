package soen487.g4.sc.retailer.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;

import soen487.g4.sc.retailer.business.Item;
import soen487.g4.sc.retailer.business.Root;

import soen487.g4.sc.retailer.business.*;

@Path("/products")
public class RetailerRESTAPI {

    private Root root;
    private String[] warehouseNames = {"1", "2", "3", "4"};
    private Retailer retailer = new Retailer();

    /*
     // for now, only refer to the xml filename of inventory-record
     private List<String> wareHouses;
     private List<Item> itemLists;*/

	//For now, there is only one default retailer
//	private RetailerRESTAPI retailer = new RetailerRESTAPI();
    /**
     * @desc Default constructor: only one warehouse is available get the item
     * list from the warehouse
     */
    public RetailerRESTAPI() {
        
        //load warehouse arrays and combine them
        Root[] _rootArr = new Root[warehouseNames.length];

        for (int i = 0; i < warehouseNames.length; i++) {
            _rootArr[i] = retailer.loadSingleWarehouse(warehouseNames[i]);

        }

        root = retailer.combineFromWarehouses(_rootArr);
//        root = retailer.loadSingleWarehouse(warehouseNames[0]);

        //write to json file
        Gson _gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            //TODO check file path
            FileWriter writer = new FileWriter(RetailerStarterRestApi.RETAILER_CATALOGUE);
            _gson.toJson(root, writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Desc GET operation that returns the information on a specific product
     * @param pid the id of the product
     * @return the information on the product
     */
    @GET
    @Path("/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProduct(@PathParam("productid") int pid) {

        Set<Item> _item = root.getItem();
        Item _temp = null;
        for (Item _ele : _item) {
            if (_ele.getProductId() == pid) {
                _temp = _ele;
            }
        }

        if (_temp == null) {
            return "<result>their is no product found with the id number.</result>";
        }else {
        	Gson _gson = new Gson();
        	return _gson.toJson(_temp);
        }
        
    }

}
