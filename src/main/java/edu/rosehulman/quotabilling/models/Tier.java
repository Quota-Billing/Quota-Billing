package edu.rosehulman.quotabilling.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.rosehulman.quotabilling.util.ObjectIdSerializer;

@Entity("tier")
public class Tier {
	@Id
	@JsonSerialize(using = ObjectIdSerializer.class)
	private ObjectId id;
	@Property
	@JsonProperty("name")
	private String name;
	@Property
	@JsonProperty("tierId")
	private String tierId;
	@Property
	@JsonProperty("max")
	private int max; 
	@Property
	@JsonProperty("value")
	private int value;
	@Property
	@JsonProperty("price")
	private double price;
	@Property
	@JsonProperty("graceExtra")
	private int graceExtra;
	@Reference
	@JsonIgnore
	private Partner partner;
	@Reference
	@JsonIgnore
	private Product product;
	@Reference
	@JsonIgnore
	private Quota quota;

	public Tier() {
		
	}
	
	public Tier(String id, String name, int max, double price) {
		this.id = new ObjectId();
		this.tierId = id;
		this.name = name;
		this.max = max;
		this.price = price;
	}

	public void setPartner(Partner partner){
		this.partner = partner;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public void setQuota(Quota quota){
		this.quota = quota;
	}
	
	@JsonIgnore
	public String getId() {
		return this.tierId;
	}

	public void setId(String id) {
		this.tierId = id;
	}

	@JsonIgnore
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@JsonIgnore
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@JsonIgnore
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@JsonIgnore
	public int getGraceExtra() {
		return graceExtra;
	}

	public void setGraceExtra(int graceExtra) {
		this.graceExtra = graceExtra;
	}

	@Override
	public String toString() {
		return "Tier: " + this.tierId + "\n";
	}
}