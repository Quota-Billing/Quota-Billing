package edu.rosehulman.quotabilling;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Database {

  private static Database instance;

  private MongoCollection<Document> partnerCollection;

  private Database() {
   /* MongoClient mongoClient = new MongoClient("localhost", 27017); // TODO set these in config
    MongoDatabase database = mongoClient.getDatabase("quotabilling");
    partnerCollection = database.getCollection("partner");*/
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
  
  public String addPartner(String partnerId, String name, String apiKey, String password, String product) {
 
    
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
    
    try {
            MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
            MongoCollection<Document> collection = database.getCollection("Partner");
            Document doc = new Document("_id", partnerId)
                    .append("name", name)
                    .append("api_key", apiKey)
                    .append("password", password)
                    .append("product", product);           
           // .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
            collection.insertOne(doc);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    
    return "ok";
    
  }
  
  public String addUser(int id) {
 
    
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
    
    try {
            MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
            MongoCollection<Document> collection = database.getCollection("User");
            Document doc = new Document("_id", id);         
           // .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
            collection.insertOne(doc);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    
    return "ok";
    
  }



  public String addUserToProduct(int i, int productId) {
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
    
    try {
            MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
            MongoCollection<Document> collection = database.getCollection("Product");
            
                        
       /*     DBObject findQuery = new BasicDBObject("_id", 1);
            DBObject listItem = new BasicDBObject("user", 5);
            DBObject updateQuery = new BasicDBObject("$push", listItem);*/
            collection.updateOne(new Document("_id", productId),
                new Document("$push", new Document("user", i)));
            
            
    } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    
    return "ok";
  }

  public String addProductToPartner(int partnerId, int productId) {
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
    
    try {
            MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
            MongoCollection<Document> collection = database.getCollection("Partner");
            
            collection.updateOne(new Document("_id", partnerId),
                new Document("$push", new Document("product", productId)));
            
            
    } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    
    return "ok";
    
  }
  

  
}
