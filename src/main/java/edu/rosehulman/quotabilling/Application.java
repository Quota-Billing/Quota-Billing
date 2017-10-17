package edu.rosehulman.quotabilling;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class Application {

  public static void main(String[] args) {
    port(8081); // Set the port to run on

    staticFiles.location("/public");
    post(Paths.ADD_USER, new AddToBillingController()); // Consume an AddUser call from Quota
  }
}
