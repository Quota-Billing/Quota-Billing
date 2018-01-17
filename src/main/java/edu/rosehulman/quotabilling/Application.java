package edu.rosehulman.quotabilling;

import edu.rosehulman.quotabilling.models.Partner;

import java.util.Optional;
import java.util.UUID;

import static spark.Spark.*;

public class Application {

  public static void main(String[] args) {
    port(8084); // Set the port to run on

    staticFiles.location("/public");

    get("/test", (req, res) -> "hello");

    post(Paths.ADD_USER, new AddUserController());
    post(Paths.ADD_PARTNER, new AddPartnerController());
    post(Paths.ADD_PRODUCT_TO_PARTNER, new AddProductController());
    post(Paths.ADD_QUOTA, new AddQuotaController());
    post(Paths.ADD_Tier, new AddTierController());
    delete(Paths.DELETE_USER, new DeleteUserController());
    post(Paths.SET_CONFIG, new SetConfigController());
    get(Paths.GET_QUOTA, new GetQuotaController());
    get(Paths.GET_PARTNER, new GetPartnerController());
    get(Paths.GET_PRODUCT, new GetProductController());
    get(Paths.GET_TIER, new GetTierController());

    post("/signUp", new SignUpController()); // TODO add to Paths class
    post("/logIn", new LogInController());
    get("/logout", (request, response) -> {
      Optional<Partner> partnerOptional = Database.getInstance().getPartnerBySession(request.session().attribute("value"));
      if (!partnerOptional.isPresent()) {
        throw halt(403);
      }
      Partner partner = partnerOptional.get();
      Database.getInstance().updatePartnerSession(partner, UUID.randomUUID());
      response.redirect("/");
      return "";
    });
    get("/dashboard", (request, response) -> {
      Optional<Partner> partnerOptional = Database.getInstance().getPartnerBySession(request.session().attribute("value"));
      if (!partnerOptional.isPresent()) {
        throw halt(403);
      }
      Partner partner = partnerOptional.get();
      return "Welcome, " + partner.getName() + "<br/>Api key: " + partner.getApikey() + "<br/><br/><a href='/logout'>Logout</a><br/><br/>" +
              "<div><form method=\"post\" action=\"setConfig\" enctype=\"multipart/form-data\"><input type=\"file\" name=\"uploaded_file\" accept=\".json\"><br/><br/><button>Upload JSON</button></form></div>";
    });
  }
}
