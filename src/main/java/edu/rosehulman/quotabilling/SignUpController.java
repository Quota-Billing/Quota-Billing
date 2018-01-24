package edu.rosehulman.quotabilling;

import edu.rosehulman.quotabilling.models.Partner;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.UUID;

import static spark.Spark.halt;

public class SignUpController implements Route {
  @Override
  public Object handle(Request request, Response response) throws Exception {
    String name = request.queryParams("name");
    String password = request.queryParams("password");

    if (Database.getInstance().partnerExists(name)) {
      throw halt(401); // Cannot create a partner that already exists
    }

    UUID sessionValue = UUID.randomUUID();
    Partner partner = Database.getInstance().createPartner(name, password);
    request.session(true);
    request.session().attribute("value", sessionValue.toString());
    Database.getInstance().updatePartnerSession(partner, sessionValue);

    QuotaClient.getInstance().addPartner(partner); // TODO Check this boolean

    boolean added = BillingClient.getInstance().addPartner(partner.getId());
    if (!added) {
      throw new Exception();
    }

    return "";
  }
}
