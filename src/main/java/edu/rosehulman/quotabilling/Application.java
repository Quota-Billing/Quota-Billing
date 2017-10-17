package edu.rosehulman.quotabilling;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.*;
import static spark.Spark.staticFiles;

public class Application {

  public static void main(String[] args) {
    port(8084); // Set the port to run on

    Database db =  Database.getInstance();
    
    staticFiles.location("/public");

   get("/test", (req, res) -> "hello");
    
    post(Paths.ADD_USER, new AddUserController()); // Consume an AddUser call from Quota
    
    /*post("/addPartner", (req, res) -> {
      return db.addPartner("4", "testPartner", "NEW_KEY", "Secret_Pass", "1");
       AddBillingController()
    });
    
    // need to use ints!!!
    post("/addUser", (req, res) -> {
      return db.addUser(5);
    });
    
    post("/addUserToProduct", (req, res) -> {
      return db.addUserToProduct(1, 6);
    }); */
  }
}
