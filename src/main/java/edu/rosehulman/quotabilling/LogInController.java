package edu.rosehulman.quotabilling;

import edu.rosehulman.quotabilling.models.Partner;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Optional;
import java.util.UUID;

import static spark.Spark.halt;

public class LogInController implements Route {
  @Override
  public Object handle(Request request, Response response) throws Exception {
    String name = request.queryParams("name");
    String password = request.queryParams("password");

    Optional<Partner> partnerOptional = Database.getInstance().getPartner(name, password);
    if (!partnerOptional.isPresent()) {
      throw halt(404);
    }

    UUID sessionValue = UUID.randomUUID();
    Partner partner = partnerOptional.get();
    request.session(true);
    request.session().attribute("value", sessionValue.toString());
    Database.getInstance().updatePartnerSession(partner, sessionValue);

    return "";
  }
}
