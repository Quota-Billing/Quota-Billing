package edu.rosehulman.quotabilling;

import java.io.IOException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ObjectIdSerializer extends StdSerializer<Object> {
    
	public ObjectIdSerializer(){
		this(null);
	}
	
	protected ObjectIdSerializer(Class<Object> t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void serialize(Object o, JsonGenerator j, SerializerProvider s) throws IOException, JsonProcessingException {
        if(o == null) {
        	System.out.println("getting null");
            j.writeNull();
        } else {
        	System.out.println("Serializing");
        	System.out.println(o);
            j.writeString(o.toString());
        }
    }
}