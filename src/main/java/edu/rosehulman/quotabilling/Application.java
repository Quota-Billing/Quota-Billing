package edu.rosehulman.quotabilling;

import static spark.Spark.*;

public class Application {

  public static void main(String[] args) {
    port(8084); // Set the port to run on

    staticFiles.location("/public");

    get("/test", (req, res) -> "hello");

    post(Paths.ADD_USER, new AddUserController());
    post(Paths.ADD_PRODUCT_TO_PARTNER, new AddProductController());
    post(Paths.ADD_QUOTA, new AddQuotaController());
    post(Paths.ADD_Tier, new AddTierController());
    delete(Paths.DELETE_USER, new DeleteUserController());
    post(Paths.SET_CONFIG, new SetConfigController());
    get(Paths.GET_QUOTA, new GetQuotaController());
    get(Paths.GET_PARTNER, new GetPartnerController());
    get(Paths.GET_PRODUCT, new GetProductController());
    get(Paths.GET_TIER, new GetTierController());

    post(Paths.SIGN_UP, new SignUpController());
    post(Paths.LOG_IN, new LogInController());
    get(Paths.LOG_OUT, new LogoutController());
    get(Paths.DASHBOARD, new DashboardController());
  }
}
