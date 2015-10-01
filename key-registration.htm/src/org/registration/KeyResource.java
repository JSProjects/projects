package org.registration;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/keys")
public class KeyResource {

	KeyDAO dao = new KeyDAO();
	

	@POST 
	@Path("dataprocessor")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Key createKeyRequest(Key key) {
		System.out.println("createKeyRequest: " + key);
		return dao.createKey(key);
	}
	
	@GET
	@Path("validate/{serialnumber}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Integer find(@PathParam("serialnumber") String serialnumber) {
		System.out.println("validation for : " + serialnumber);
		int count= dao.validate(serialnumber);
		return count;
	}
	
}
