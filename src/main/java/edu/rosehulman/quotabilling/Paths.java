package edu.rosehulman.quotabilling;

public class Paths {

  public static final String ADD_QUOTA = "partner/:partnerId/product/:productId/quota/:quotaId/name/:name/type/:type";

  public static final String ADD_PRODUCT_TO_PARTNER = "partner/:partnerId/name/:name/product/:productId";

  public static final String ADD_USER = "partner/:partnerId/product/:productId/user/:userId";

  public static final String BILLING_BASE = "http://srproj-18.csse.rose-hulman.edu:8085/";

  public static final String ADD_PARTNER = "partner/:partnerId/name/:name/key/:api_key/password/:password";

  public static final String ADD_Tier = "partner/:partnerId/product/:productId/quota/:quotaId/name/:name/tier/:tierId/price/:price/max/:max";

  public static final String DELETE_USER = "partner/:partnerId/product/:productId/user/:userId";
}
