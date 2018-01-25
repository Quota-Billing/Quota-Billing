package edu.rosehulman.quotabilling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import edu.rosehulman.quotabilling.models.*;
import edu.rosehulman.quotabilling.util.Hasher;
import edu.rosehulman.quotabilling.util.ObjectIdMapper;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Database {

	private static Database instance;

	private MongoClient mongoClient;
	private Datastore datastore;

	private Database() {
		this.mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		Morphia morphia = new Morphia();
		morphia.mapPackage("edu.rosehulman.quotabilling");
		this.datastore = morphia.createDatastore(this.mongoClient, "quotabillingshare");
	}

	public static synchronized Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	// getting a partner
	public String getPartner(String partnerId) {
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.size() == 0) {
			System.out.println("wrong partnerId"); // debugging
			return null;
		}
		Partner partner = partners.get(0);
		ObjectMapper mapper = new ObjectIdMapper();
		try {
			System.out.println("returning: "+ mapper.writeValueAsString(partner));
			return mapper.writeValueAsString(partner);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean partnerExists(String name) {
		return !datastore.createQuery(Partner.class).field("name").equal(name).asList().isEmpty();
	}

	public Optional<Partner> getPartner(String name, String password) throws Exception {
		if (!partnerExists(name)) {
			return Optional.empty();
		}
    Partner partner = datastore.createQuery(Partner.class).field("name").equal(name).asList().get(0);
		String hash = Hasher.hash(password, partner.getPasswordSalt());
		if (!hash.equals(partner.getPasswordHash())) {
      return Optional.empty();
    }
    return Optional.of(partner);
	}

	public Optional<Partner> getPartnerById(String partnerId) throws Exception {
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();

		if (partners.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(partners.get(0));
	}

	public Partner createPartner(String name, String password) throws Exception {
    Partner partner = new Partner();
    partner.setApikey(UUID.randomUUID().toString());
    partner.setPartnerId(UUID.randomUUID().toString());
    partner.setName(name);
    partner.setPasswordSalt(Hasher.getRandomSalt());
    partner.setPasswordHash(Hasher.hash(password, partner.getPasswordSalt()));
    this.datastore.save(partner);
    return partner;
  }

  public void updatePartnerSession(Partner partner, UUID sessionValue) {
    partner.setSessionValue(sessionValue.toString());
    this.datastore.save(partner);
  }

  public Optional<Partner> getPartnerBySession(String sessionValue) {
    List<Partner> partners = datastore.createQuery(Partner.class).field("sessionValue").equal(sessionValue).asList();
    if (partners.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(partners.get(0));
  }

	// getting a product with partnerId and productId
	public String getProduct(String partnerId, String productId) {
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.size() == 0) {
			System.out.println("wrong partnerId"); // debugging
			return null;
		}
		Partner partner = partners.get(0);
		Product product = partner.getProduct(productId);
		ObjectMapper mapper = new ObjectIdMapper();
		try {
			System.out.println("returning: "+ mapper.writeValueAsString(product));
			return mapper.writeValueAsString(product);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// getting a user with partnerId, productId, userId
	public User getUser(String partnerId, String productId, String userId){
		List<User> users = datastore.createQuery(User.class).field("partnerId").equal(partnerId).field("productId").equal(productId).field("userId").equal(userId).asList();
		if (users.size() == 0) {
			System.out.println("wrong Ids"); // debugging
			return null;
		}
		User user = users.get(0);

		return user;
	}

	// getting a user with partnerId, productId, quotaId
	public String getQuota(String partnerId, String productId, String quotaId){
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.size() == 0) {
			System.out.println("wrong partnerId"); // debugging
			return null;
		}
		Partner partner = partners.get(0);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);
		ObjectMapper mapper = new ObjectIdMapper();
		try {
			System.out.println("returning: "+ mapper.writeValueAsString(quota));
			return mapper.writeValueAsString(quota);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// getting a list of tiers of a quota
	public List<Tier> getTiers(String partnerId, String productId, String quotaId){
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.size() == 0) {
			System.out.println("wrong partnerId"); // debugging
			return null;
		}

		Partner partner = partners.get(0);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);
		return quota.getTiers();
	}

	// getting a single tier from a quota, given the tierId
	public String getTier(String partnerId, String productId, String quotaId, String TierId){
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.size() == 0) {
			System.out.println("wrong partnerId"); // debugging
			return null;
		}
		Partner partner = partners.get(0);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);
		List<Tier> tiers =  quota.getTiers();
		for(Tier t: tiers){
			if(t.getId().equals(TierId)){
				ObjectMapper mapper = new ObjectIdMapper();
				try {
					System.out.println("returning: "+ mapper.writeValueAsString(t));
					return mapper.writeValueAsString(t);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		return null;
	}

	public String addQuota(String partnerId, String productId, String quotaId, String name, String type) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			Quota quota = new Quota(quotaId, name, type);
			quota.setPartner(partner);
			quota.setProduct(product);
			this.datastore.save(quota);
			Query<Product> query = this.datastore.createQuery(Product.class).field("id").equal(product.getObjectId());
			UpdateOperations<Product> op = this.datastore.createUpdateOperations(Product.class).push("quotas",
					quota);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	// add a simple user by referencing a product and a partner
	public String addUser(String id, String productId, String partnerId) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			User user = new User(id);
			user.setPartner(partner);
			user.setProduct(product);
			this.datastore.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	// adding product to a specific partner, in mongoDB the product will be
	// saved by reference and its ID
	public String addProductToPartner(String partnerId, String name, String productId) {
		try {
			System.out.println(partnerId);
			System.out.println(name);
			System.out.println(productId);
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			System.out.println(partners.size());
			Partner partner = partners.get(0);
			System.out.println(partner.getId());
			Product product = new Product(productId, name);
			partner.addProduct(product);
			this.datastore.save(product);
			Query<Partner> query = this.datastore.createQuery(Partner.class).field("partnerId").equal(partnerId);
			UpdateOperations<Partner> op = this.datastore.createUpdateOperations(Partner.class).push("products",
					product);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addTier(String partnerId, String productId, String quotaId, String tierId, String name, String max,
			String price) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			Quota quota = product.getQuota(quotaId);
			if (quota == null) {
				System.out.println("wrong quotaId");
				return "Wrong quotaId";
			}
			Tier tier = new Tier(quotaId, name, Integer.valueOf(max), Double.valueOf(price));
			tier.setPartner(partner);
			tier.setProduct(product);
			tier.setQuota(quota);
			this.datastore.save(tier);
			Query<Quota> query = this.datastore.createQuery(Quota.class).field("id").equal(quota.getObjectId());
			UpdateOperations<Quota> op = this.datastore.createUpdateOperations(Quota.class).push("tiers",
					tier);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public boolean deleteUser(String partnerId, String productId, String userId) {
	   final Query<User> deleteQuery = datastore.createQuery(User.class).field("userId").equal(userId);
	   List<User> results = deleteQuery.asList();

	   for (User u : results) {
	     // TODO can/should we ever delete multiple?
	     if (u.getPartner().getId().equals(partnerId) && u.getProduct().getId().equals(productId)) {
	       datastore.delete(u);
	       return true;
	     }
	   }
	   return false;
	}
}
