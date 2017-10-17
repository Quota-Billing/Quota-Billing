package edu.rosehulman.quotabilling;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Database {

  private static Database instance;

  private MongoCollection<Document> partnerCollection;

  private Database() {
    MongoClient mongoClient = new MongoClient("localhost", 27017); // TODO set these in config
    MongoDatabase database = mongoClient.getDatabase("quotabilling");
    partnerCollection = database.getCollection("partner");
  }

  public static synchronized Database getInstance() {
    if (instance == null) {
      instance = new Database();
    }
    return instance;
  }

  public void addUser(String partnerId, String productId, String userId) {
    Document userDocument = new Document("$set", new Document("_id", partnerId)
        .append("products", new Document("_id", productId)
            .append("users", new Document("_id", userId))));
    partnerCollection.updateOne(and(eq("_id", partnerId), eq("products", productId)), userDocument); // TODO This does not work, not using lists
  }
  
  public static String addToSharedDB(String partnerId, String name, String apiKey, String password, String product) {
 
    
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
    
    try {
            MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
            MongoCollection<Document> collection = database.getCollection("partner");
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

  
}
