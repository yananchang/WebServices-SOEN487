# README #

# Assignment Project Milestone 1 (PM1) #

Due: Feb 14th

	Part #1: XML and Java, a warm up exercise
	Part #2: A simple SOAP service

## How to run: 1) XML and Java, a warm up exercise ##

This part can be found in the package: "soen487.g4.a1.util.xmlutils.[...]" where [...] is the type of the data source.
	
- To run RSS, go to **soen487.g4.a1.util.xmlutils.rss**
	=> Run "XMLWarmupRSS" as a Java Application
	
- To run NN, go to **soen487.g4.a1.util.xmlutils.nn**
	=> Run "XMLWarmupNeuralNetwork" as a Java Application
	
- To run MARFCATInput, go to **soen487.g4.a1.util.xmlutils.marfcatinput**
	=> Run "XMLWarmupMARFCATInput" as a Java Application
	
- To run MARFCATOutput, go to **soen487.g4.a1.util.xmlutils.marfcatoutput**
	=> Run "Driver" as a Java Application
	
- To run WSDL, go to **soen487.g4.a1.util.xmlutils.wsdlparser**
	=> Run "WsdlWarmupDom" as a Java Application
		
Note: for WSDL data source, we tried to use JAXB (see wsdlparser package: the class WsdlWarmup is the driver, and the package "FaultSampleClasses" contains the Java classes corresponding to the WSDL file generated using wsimport) but we kept getting "null" as an output when we try to access an element. So, we used DOM as an alternative to get the elements and attributes.


## How to run: 2) A simple SOAP service ##

1. Deploy project soen487-w18-team04, which contains the SOAP web service soen487.g4.a1.util.xmlutils.SoapXMLReader.
2. Open the XMLReaderClient project (as a separate project). It's located in the main folder for the repo.
3. Build the XMLReaderClient project, then run application.XMLReaderClient. The client application will run in the console.
4. You can test each data source by hitting Enter for the URI input, then entering "rss", "nn", "marfcat-in", "marfcat-out" and "wsdl".

# Assignment Project Milestone 2 (PM2) #

Due: March 11th

	Part #1: creation of a RESTful Web service from database
	Part #2: develop a RESTful Web service from Java bean classes

## How to run: 1) RESTful web service from database

1. Open soen487-w18-team04/CustomerDB and CustomerDB_Client
2. Start JavaDB server.
3. Start the Glassfish web application server.
4. Deploy the project CustomerDB and run CustomerDB_Client
5. open http://localhost:8080/CustomerDB_Client/client.html.
6. Search "state" and click submit.


## How to run: 2) RESTful web service from Java bean classes

1. Open soen487-w18-team04/TemperatureService.
2. Start the Glassfish web application server.
3. Deploy the project/application.
4. Run web/index.html.

Note: After this application runs, the value of the temperature on the server becomes 20.0. If you want to run it again and see the example output with an initial value 10.0, you need to re-deploy the application.

# Assignment Project Milestone 2 (PM2):  Part 3: SC #

## Global architecture: ##

The architecture of our application is a microservice architecture. 
In total, we have 3 different microservices interacting together:  

1. Manufacturer Service => Found as a project **Manufacturer** in **soen487-w18-team04** folder  
2. Retailer Service	=> Found as a project **RetailerService** in **soen487-w18-team04/RetailerSystem** folder  
3. Warehosue Service	=> Found as a project **WarehouseService** in **soen487-w18-team04/RetailerSystem** folder (where # is the number of the Warehouse)  

## Dependency ##

Warehouse has a dependecy to json-simple-1.1.1.jar. Before running the project, go to:  
On Netbeans  
        properties -> Librairies -> Compile time -> Add JAR/Folder. Specify the jar and click on OK.  

Retailer has a dependecy to gson.jar. Before running the project, add the gson jar to the project. 

## How to run the whole project? ##
1. Deploy all 4 Manufacturer projects
2. Deploy WarehouseService1  
    Deploye all other WarehouseService (2 to 4)
3. Deploy RetailerService  
4. Deploy SupplyChainWebApp (for testing Warehouse client of PM #1)

## Manufacturer ##

There are 4 manufacturers in total: Brand1, Brand2, Brand3 and Brand4. Each one has its own SOAP web service, which is used by the warehouse services to place orders for products.

The Manufacturer services are organized into 2 main parts:
1. soen487.g4.sc.manufacturer.business, which contains domain model classes for the Manufacturer: Product and PurchaseOrder.
2. soen487.g4.sc.manufacturer.service, which conatins the SOAP web service.
It also has an InitializeInventory class which is used to randomly initialize the manufacturer's product unit prices and stock levels.

The data source for a Manufacturer web service is 2 XML files: product.xml and purchase-order.xml. The files are maintained locally on our system, but outside of
the **soen487-w18-team04** project, so the following action must be take prior to deploying a ManufacturerWebService (this must be done for each manufacturer web service deployed on the system): 
- Copy the 2 xml files in soen487-w18-team04\Manufacturer\src\java\soen487\g4\sc\manufacturer\service to a location on the system that is outside of the project (so they don't get compiled).
- In the ManufacturerWebService class, change the PO_FILE_PATH and PRODUCT_FILE_PATH accordingly.

### part a) Updating the 'goods' prices and initial inventory to be randomly generated###
This is done by the InitializeInventory class, whose constructor is called when the web service is deployed. The constructor calculated random values for the number of DVD players, video cameras and TVs in stock, then
records them in the class variables and in the web service's product.xml file.

## Warehouse ##

We have 4 warehouses in total. Each warehouse offers a soap web service and a restful web service.  
These services are used by a retailer to place an order (using soap WS) and to get information on the products available in the inventory of the warehouse (restful WS).

WarehouseService is organized into 3 parts:  
1. soen487.g4.sc.warehouse.service : contains both the SOAP web service and the Restful API.  
2. soen487.g4.sc.warehouse.business : contains the model classes for Warehouse, like Customer, Item.  
3. soen487.g4.sc.warehouse.util : contains helper classes that will be called by the service layer.  

Our database for Warehouse is as XML documents. Until now, there are 4 database files. Our database is maintained locally into our system, but outside of    
**soen487-w18-team04** project, that's why the following actions have to be made before executing the WarehouseService:  

**Important:** before running the Warehouse, the paths to the 4 database files inside a classe in the WarehouseService project have to be modified. 
First, choose a folder inside of your system, but outside of the **soen487-w18-team04** project path. Then, move the 4 following files into this folder:  
"inventory-record.xml", "ordered-items-record.xml","replenish-orders-record.xml","retailers-record.json" (can be found in **soen487.g4.sc.warehouse.database**).  

Then, go to the following class and change the paths to the path you chose above:  

1. WarehouseWebService inside of **soen487.g4.sc.warehouse.service**   
		=> public static final String XMLPATH = "...";  
		=> public static final String PLACED_ORDERS_FILE = "..."; 
		=> public static final String ORDERED_ITEMS_FILE = "..."; 
                => public static final String RETAILERS_RECORDS_FILE = "...";

Do this for all 4 WarehouseService projects.
 
### How to Deploy Warehouse ###

1. Deploy all 4 **Manufacturer** projects  
2. Deploy **WarehouseService** project  

### part b) RestFul API for Warehouse ###
The RestFul API for Warehouse can be found in **src/java/soen487/g4/sc/warehouse/service/restfulapi/** inside of the project **WarehouseService**  

- For GET operations, the URLs provided (i. to iv.) were used to perform this operation.  
    
- For POST oepration, the URL warehouses/YYY/retailer  has to be used to register a retailer to the warehouse.  
		the format of the post body is:  {"name":"RetailerName"}  
        JSON was used since the Retailer <=> Warehouse communication has to be in JSON
- For DELETE oepration, the URL warehouses/YYY/retailer/RRR (RRR is the name of the retailer) has to be used to dissociate a retailer from the warehouse.  

The database for retailer is maintained as a JSON file (retailers-record.json)  

### part d) ###
When deployed, the warehouse pre-purchases 100 items from each of the 4 manufacturers.  
Can be found in **soen487.g4.sc.warehouse.config.PrepurchaseItems**  
  
### part e) Warehouse Client Test ###
 The client test for PM#2 is as Java client (PM#1 client test was as a JSP/Servlet).
 To run it, go to test package inside of WarehouseService1, then run "TestClient"

## Retailer ##

A Retailer communicates with multiple warehouses(currently 4 warehouses) by calling their Restful API through GET http method.  
Also, it offers its own Restful web service that will be used by the web client application in the following project millestone.   
  
When the retailer is deployed, it establishes a communication to all 4 warehouses to get their inventory of products (by calling their Restful API through GET warehouses/#/products).   
Then, it combines all the products of all 4 warehouses into a catalogue of items based on predefined criteria(currently manufacturername and producttype)and saves this catalogue into its internal database (found in service.file.json).  

**Important: ** before deploying the Retailer, first go to RetailerStarterRestApi and change the file path of RETAILER_CATALOGUE to the path of your system where "file.json" is saved.  

### How to Deploy RetailerService ###
1. Deploy all 4 **Manufacturer** projects  
2. Deploy all 4 **WarehouseService** projects 
3. Deploy **RetailerService** project
 
### part c) Retailer Catalogue of Items and RestfulAPI ###

The retailer catalogue of items is saved in the format of json file, which is synchronized at startup as stated above.
The RestfulAPI can be found in src.java.soen487.g4.sc.retailer.service.RetailerRESTAPI.java  
It has one http method GET that can be accessed through the URL retailer/products/X where X is the product id.  

### part f) Retailer Javascript client ###

The JS client test for retailer can be found in RetailerService/web/retailerClient.html  
  
To run this test, simply run the file retailerClient.html.  
   
### part g) Bonus: Retailer can talk to the 4 Warehouses ###

The retailer communicates with all 4 warehouses as explained above.

# Assignment Project Milestone 3 (PM3) #

## How to run: 1) "Hello BPEL" business process

1. Checkout Tag SOEN487_W18_TEAM_04_PM3.
2. Open soen487-w18-team04/PM3/HelloWorld and HelloWorldCA
3. Deploy the project HelloWorldCA
4. Run TestCase1

## How to run 2) "Compose a simple process which invokes a remote service"

1. Start a Glassfish server (HTTP port 8080), as well as an OpenESB server.
2. Deploy project soen487-w18-team04, which contains the XMLReader web service, on the Glassfish server.
3. For the first part ("invoke the XMLReader service"), deploy the composite application PM3-2_CA on the OpenESB server.
4. There are test cases for each possible XMLReader data type (RSS, NN, MARFACT-IN/OUT, WSDL).
5. Deploy the composite application PM3-2_REST_CA on the OpenESB server.
6. There is a test case for country code IND, which returns all of the states in India.

Note: The tag for this milestone is based on a branch created from the tag for PM1. The reason is that one of the commits for PM2 or PM3 broke the soen487-w18-team04 (error on deployment),
so I decided to use an earlier, working version. Since this code is isolated from the main project, it won't have an impact.

## How to run 3) SC ? ##

The detailed documentation for this PM can be found in doc folder => 487-team04-report.docx

1. Follow the same instructions as for PM 2 regarding Manufacturers, Warehouses and Retailer.
2. Then, Deploy SupplyChainWebService
3. open another IDE (other than OpenESB). Start a Tomcat server on a port other than 8080. 
4. Open SupplyChainWebApp project and deploy it on Tomcat. To access the homepage of the web applciation, go to http://localhost:3000/SupplyChainWebApp/home.jsp
5. To open the BPEL process, go to: RetailerSystem folder, then open WarehousesInvocationModueService
6. To view the BPEL process, open WarehousesInvocationModule_2.bpel
  
parts a) b) c) i and ii are compelte. Also, part v is compelte (we did it in PM 1)  

## General structure ##

* `doc` is for documentation, report, etc. Create a dir for your team there, e.g., `teamX`, and initialize the report document there.

## Templates ##

* `doc/report/project-report.tex` is the most documented example template in LaTeX, single column, more readable. To be able to compile LaTeX report, install and configure [MiKTeX](http://miktex.org) first (LaTeX backend compiler and styles and packages), then [TeXnicCenter](http://texniccenter.org) (GUI front-end to MikTeX, it will find MikTeX installation when installed after MikTeX). Open `project-report.tcp` project file for TeXnicCenter and compile it using F7 3 times; F5 to preview the PDF.

## FYI: below is a boilerplate README from BitBucket ##

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Serguei
* Other community or team contact