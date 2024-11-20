package api.test;

import static org.hamcrest.Matchers.equalTo;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPointsFromPropertiesFile;
import api.endpoints.UserEndpoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTestsFromPropFile {

	Random random = new Random();
	Store order = new Store();
	Faker faker = new Faker();
	public Logger logger;
	
	
	@BeforeClass
	public void setupData(){
	
	int upperbound = 10;
	int int_random = random.nextInt(upperbound);
	order.setId(int_random);
	order.setPetId(faker.idNumber().hashCode()+3);
	order.setQuantity(int_random);
	order.setShipdate("2024-11-21T16:20:16");
	order.setStatus("placed");
	order.setComplete(true);
	
	//logs
	logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testStoreInventory()
	{
		logger.info("############# Store Inventory launched #############");
		Response response=UserEndPointsFromPropertiesFile.getStoreInventory();
		response.then()
			//.body("AVAILABLE",equalTo(2))
			//.body("sold",equalTo(35))
			//.body("totvs",equalTo(5))
			//.body("pending",equalTo(6))
			.log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);	
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("############# Store Inventory end  #############");
	}
	
	@Test(priority=2)
	public void testStorePlaceOrder(){
		logger.info("############# Place Store Order #############");
		Response response=UserEndPointsFromPropertiesFile.placeOrder(order);
		response.then()
			.log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("############# Placed Store Order! #############");
		
	}
	
	
	@Test(priority=3)
	public void testStoreGetOrder()
	{
		logger.info("############# Get Store Order #############");
		Response response=UserEndPointsFromPropertiesFile.getOrder(this.order.getId());
		response.then()
			.statusCode(200)
			.log().all();
		logger.info("############# Store Order Displayed #############");
	}
	
	@Test(priority=4)
	public void testStoreDeleteOrder()
	{
		logger.info("############# Delete Store Order #############");
		Response response=UserEndPointsFromPropertiesFile.deleteOrder(this.order.getId());
		response.then()
			.statusCode(200)
			.log().all();
		logger.info("############# Deleted Store Order! #############");
	}
	
	
}
