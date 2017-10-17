package edu.rosehulman.quotabilling;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class Application {

  public static void main(String[] args) {
    port(8084); // Set the port to run on

    staticFiles.location("/public");

    get("/test", (req, res) -> "hello");

    post(Paths.ADD_USER, new AddUserController()); // Consume an AddUser call from Quota
  }
}
