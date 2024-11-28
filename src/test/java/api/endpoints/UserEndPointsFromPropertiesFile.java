package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Pet;
import api.payload.Store;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPointsFromPropertiesFile {

		// method created for getting URL's from properties file
		static ResourceBundle getURL()
		{
			ResourceBundle routes= ResourceBundle.getBundle("routes"); // Load properties file (name of the properties file)
			return routes;
		}
	
	
		// USER MODEL
		public static Response createUser(User payload)
		{       //get the value of 'post_url' from routes properties file stored in /resources 
			String post_url=getURL().getString("post_url");
			
			
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_url);
				
			return response;
		}
		
		
		public static Response readUser(String userName)
		{
			String get_url=getURL().getString("get_url");
			
			
			Response response=given()
							.pathParam("username",userName)
			.when()
				.get(get_url);
				
			return response;
		}
		
		
		public static Response updateUser(String userName, User payload)
		{
			String update_url=getURL().getString("update_url");
			
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when()
				.put(update_url);
				
			return response;
		}
		
		
		public static Response deleteUser(String userName)
		{
			String delete_url=getURL().getString("delete_url");
			
			Response response=given()
							.pathParam("username",userName)
			.when()
				.delete(delete_url);
				
			return response;
		}	
		
		// STORE MODEL
		public static Response getStoreInventory()
		{
			String store_inventory=getURL().getString("get_url_store_inventory");
			Response response = 
					given()
						.accept(ContentType.JSON)
					.when()
						.get(store_inventory);
			return response;
		}
		
		public static Response placeOrder(Store payload)
		{
			String place_order=getURL().getString("place_url_store_order"); 
			Response response = 
					given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(payload)
					.when()
						.post(place_order);
			return response;
		}
		
		public static Response getOrder(int id)
		{
			String get_order=getURL().getString("get_url_store_order");
			Response response = 
					given()
						.accept(ContentType.JSON)
						.pathParam("orderId", id)
					.when()
						.get(get_order);
			return response;
		}
		
		public static Response deleteOrder(int id)
		{
			String delete_order=getURL().getString("delete_url_store_order");
			Response response = 
					given()
						.accept(ContentType.JSON)
						.pathParam("orderId", id)
					.when()
						.get(delete_order);
			return response;
		}
		
		
		// PET MODEL
		public static Response getPetByStatus()
		{
			String get_StatusPet=getURL().getString("get_pet_ByStatus");
			Response response = 
					given()
						.accept(ContentType.JSON)
						.queryParam("status","available") //can be SOLD AVAILABLE or PENDING
					.when()
						.get(get_StatusPet);
			return response;
		}
		
		public static Response getSinglePet(int pet_id)
		{
			String get_Pet=getURL().getString("get_single_pet");
			Response response = 
					given()
						.accept(ContentType.JSON)
						.pathParam("id", pet_id)
						//.queryParam("status","available") 
					.when()
						.get(get_Pet);
			return response;
		}
		
		public static Response createPet(Pet petPayload)
		{       //get the value of 'post_pet' from routes properties file stored in /resources 
			String create_pet=getURL().getString("post_pet");
						
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(petPayload)
			.when()
				.post(create_pet);
				
			return response;
		}
		
		public static Response updatePet(Pet petPayload, String idPet)
		{       //get the value of 'post_url' from routes properties file stored in /resources 
			String update_pet=getURL().getString("put_pet");
						
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("id", idPet)
				.body(petPayload)
			.when()
				.post(update_pet);
				
			return response;
		}
		
}
