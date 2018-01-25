package edu.rosehulman.quotabilling.controllers;

import edu.rosehulman.quotabilling.Database;
import edu.rosehulman.quotabilling.models.Partner;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Optional;
import java.util.UUID;

import static spark.Spark.halt;

public class LogoutController implements Route {
  @Override
  public Object handle(Request request, Response response) throws Exception {
    Optional<Partner> partnerOptional = Database.getInstance().getPartnerBySession(request.session().attribute("value"));
    if (!partnerOptional.isPresent()) {
      throw halt(403);
    }
    Partner partner = partnerOptional.get();
    Database.getInstance().updatePartnerSession(partner, UUID.randomUUID());
    response.redirect("/");
    return "";
  }
}
