package edu.rosehulman.quotabilling;

public class Paths {

  public static final String ADD_QUOTA = "partner/:partnerId/product/:productId/quota/:quotaId/name/:name/type/:type";

  public static final String ADD_PRODUCT_TO_PARTNER = "partner/:partnerId/name/:name/product/:productId";

  public static final String ADD_USER = "partner/:partnerId/product/:productId/user/:userId";

  //public static final String BILLING_BASE = "http://srproj-18.csse.rose-hulman.edu:8085/";
  public static final String BILLING_BASE = "http://localhost:8085/";

  public static final String ADD_Tier = "partner/:partnerId/product/:productId/quota/:quotaId/name/:name/tier/:tierId/price/:price/max/:max";

  public static final String DELETE_USER = "partner/:partnerId/product/:productId/user/:userId";

  public static final String QUOTA_BASE = "http://localhost:8080/";

  public static final String SET_CONFIG = "setConfig";
  
  public static final String GET_USER = "partner/:partnerId/product/:productId/user/:userId";
  
  public static final String GET_PARTNER = "partner/:partnerId";

  public static final String GET_PRODUCT = "partner/:partnerId/product/:productId";
  
  public static final String GET_QUOTA = "partner/:partnerId/product/:productId/quota/:quotaId";
  
  public static final String GET_TIER = "partner/:partnerId/product/:productId/quota/:quotaId/tier/:tierId";

  public static final String SIGN_UP = "/signUp";

  public static final String LOG_IN = "/logIn";

  public static final String LOG_OUT = "/logout";

  public static final String DASHBOARD = "/dashboard";
}
