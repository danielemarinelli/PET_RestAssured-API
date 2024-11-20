package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Store;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java 
//Created to perform Create, Read, Update, Delete requests to the User services 

public class UserEndpoints 
{
	
	// for USER Model!!!
	public static Response createUser(User payload)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.post_url);
		return response;
	}

	public static Response readUser(String userName)
	{
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.get(Routes.get_url);
		return response;
	}

	public static Response updateUser(String userName, User payload)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username", userName)
					.body(payload)
				.when()
					.put(Routes.update_url);
		return response;
	}

	public static Response deleteUser(String userName)
	{
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.delete(Routes.delete_url);
		return response;
	}
	
	
	// for STORE Model!!!
	public static Response getStoreInventory()
	{
		Response response = 
				given()
					.accept(ContentType.JSON)
				.when()
					.get(Routes.get_url_store_inventory);
		return response;
	}
	
	public static Response placeOrder(Store payload)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.place_url_store_order);
		return response;
	}
	
	public static Response getOrder(int id)
	{
		Response response = 
				given()
					.accept(ContentType.JSON)
					.pathParam("orderId", id)
				.when()
					.get(Routes.get_url_store_order);
		return response;
	}
	
	public static Response deleteOrder(int id)
	{
		Response response = 
				given()
					.accept(ContentType.JSON)
					.pathParam("orderId", id)
				.when()
					.get(Routes.delete_url_store_order);
		return response;
	}
	
	
}