package edu.rosehulman.quotabilling;

import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.rosehulman.quotabilling.models.Partner;
import edu.rosehulman.quotabilling.models.Product;
import edu.rosehulman.quotabilling.models.User;

public class Database {

  private static Database instance;

  private MongoCollection<Document> partnerCollection;
  private MongoClient mongoClient;
  private MongoDatabase database;
  private Datastore datastore;
  private Database() {
   /* MongoClient mongoClient = new MongoClient("localhost", 27017); // TODO set these in config
    MongoDatabase database = mongoClient.getDatabase("quotabilling");
    partnerCollection = database.getCollection("partner");*/
	  this.mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
	  this.database = mongoClient.getDatabase("quotabillingshare");
	  Morphia morphia = new Morphia();
	  morphia.mapPackage("edu.rosehulman.quotabilling");
	  this.datastore = morphia.createDatastore(this.mongoClient, "quotabillingshare");
	  
  }

  public static synchronized Database getInstance() {
    if (instance == null) {
      instance = new Database();
    }
    return instance;
  }

  /*public void addUser(String partnerId, String productId, String userId) {
    Document userDocument = new Document("$set", new Document("_id", partnerId)
        .append("products", new Document("_id", productId)
            .append("users", new Document("_id", userId))));
    partnerCollection.updateOne(and(eq("_id", partnerId), eq("products", productId)), userDocument); // TODO This does not work, not using lists
  }*/
  
  // add a partner
  public String addPartner(String partnerId, String name, String apiKey, String password) {
     
    try {
//            MongoCollection<Document> collection = database.getCollection("Partner");
//            Document doc = new Document("_id", partnerId)
//                    .append("name", name)
//                    .append("api_key", apiKey)
//                    .append("password", password)
//                    .append("product", product);           
           // .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//            collection.insertOne(doc);
    		Partner partner= new Partner(partnerId, name, apiKey);
    		partner.setPassword(password);
    		this.datastore.save(partner);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    
    return "ok";
    
  }
  
  // add a simple user
  public String addUser(String id, String productId, String partnerId) {
     
    try {
//            MongoCollection<Document> collection = database.getCollection("User");
//            Document doc = new Document("_id", id).append("partnerId", partnerId).append("productId", productId);         
//           // .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//            collection.insertOne(doc);
    		Product product = datastore.createQuery(Product.class).field("productId").equal(productId).asList().get(0);
    		Partner partner = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList().get(0);
    		User user = new User(id, product, partner);
    		this.datastore.save(user);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    
    return "ok";
    
  }


  // add user to product
//  public String addUserToProduct(int i, int productId) {    
//    try {
//            //MongoCollection<Document> collection = database.getCollection("Product");                    
//       /*     DBObject findQuery = new BasicDBObject("_id", 1);
//            DBObject listItem = new BasicDBObject("user", 5);
//            DBObject updateQuery = new BasicDBObject("$push", listItem);*/
//            //collection.updateOne(new Document("_id", productId),
//                //new Document("$push", new Document("user", i)));
//            
//            
//    } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            mongoClient.close();
//        }
//    
//    return "ok";
//  }

  // add product to partner in the table
  public String addProductToPartner(String partnerId, String name, String productId) {
    
    try {
//            MongoCollection<Document> collection = database.getCollection("Partner");
//            
//            collection.updateOne(new Document("_id", partnerId),
//                new Document("$push", new Document("product", productId)));
        //haven't decide how to do this one
		Partner partner = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList().get(0);
		Product product = new Product(productId, name);
		this.datastore.save(product);
    } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    
    return "ok";
    
  }


  
}
