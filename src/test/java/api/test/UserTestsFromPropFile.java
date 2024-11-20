package api.test;

import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsFromPropertiesFile;
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsFromPropFile {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********** Creating User ***************");
		Response response=UserEndPointsFromPropertiesFile.createUser(userPayload);
		response.then()
			.statusCode(200)
			.body("type",equalTo("unknown"))
			.log().all();
		
		//Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("access-control-allow-origin"),"*");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("**********  User Created ***************");
	}
	
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("********** Fetching User ***************");
		Response response=UserEndPointsFromPropertiesFile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);	
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("********** User Displayed ***************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("********** Updating User ***************");
		
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPointsFromPropertiesFile.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
				
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********** User updated ***************");
		//Checking data after update
		Response responseAfterupdate=UserEndPointsFromPropertiesFile.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("**********   Deleting User  ***************");
		
		Response response=UserEndPointsFromPropertiesFile.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("********** User deleted ***************");
	}
	
	
	
}
