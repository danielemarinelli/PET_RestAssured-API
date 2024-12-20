package api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPointsFromPropertiesFile;
import api.payload.Pet;
import api.payload.Tag;
import api.payload.User;
import api.payload.Category;
import io.restassured.response.Response;

public class PetTests {
	
	Faker faker=new Faker();
	Pet pet=new Pet();
	Category cate=new Category();
	Tag[] tag=new Tag[1];
	Random random = new Random();
	public Logger logger;
	int petIdToSearch;
	static String petCreated_Id;
	int upperbound = 15;
	int num_random = random.nextInt(upperbound);
	int num_random_for_tag = random.nextInt(upperbound);
	String nameUnderCategory = faker.name().username();
	int idUnderCategory = faker.idNumber().hashCode();
	
	
	
	
	@BeforeClass
	public void setupData(){
		
		//Random random = new Random();
		//faker=new Faker();
		//pet=new Pet();
		//cate=new Category();
		//tag=new Tag[1];
		// to understand the PET payload consider
		//web site https://jsonformatter.com
		//int upperbound = 15;
		//int num_random = random.nextInt(upperbound);
		//int num_random_for_tag = random.nextInt(upperbound);
		pet.setId(num_random);
		pet.setCategory(cate);
		cate.setName(nameUnderCategory);
		cate.setId(idUnderCategory);
		pet.setName(faker.name().firstName());
		pet.setPhotoUrls(null);  //keeping the photourls array empty
		tag[0]=new Tag();
		tag[0].setIdTag(num_random_for_tag);
		tag[0].setNameTag("puppy");
		pet.setTags(tag);
		pet.setStatus("available");
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	
	@Test(priority=1)
	public void testGetPetByStatus()
	{
		logger.info("%%%%%%%% Fetching PetByStatus %%%%%%%%");
		Response response=UserEndPointsFromPropertiesFile.getPetByStatus();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);	
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("%%%%%%%% PetByStatus Displayed %%%%%%%%");
	}
	
	@Test(priority=2)
	public void testGetPetById()  
	{
		logger.info("%%%%%%%% Fetching Pet By Id %%%%%%%%");
		Response response=UserEndPointsFromPropertiesFile.getSinglePet(this.pet.getId());
		response.then().log().all();
		/*System.out.println("Pet ID selected: "+this.pet.getId());
		System.out.println(this.pet.getCategory().getId());
		System.out.println(this.pet.getCategory().getName());*/
		
		Assert.assertEquals(response.getStatusCode(),200);	
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("%%%%%%%% Pet By Id Displayed %%%%%%%%"); 
		
		int idPet=response.jsonPath().get("id");
		Assert.assertEquals(idPet,this.pet.getId());
		//System.out.println(response.jsonPath().get("tags[0].name").toString());
	}
	
	
	@Test(priority=3)
	public void testPostUser()
	{
		logger.info("%%%%%%%% Creating Pet %%%%%%%%");
		Response response=UserEndPointsFromPropertiesFile.createPet(pet);
		response.then()
			.statusCode(200)
			//.body("type",equalTo("unknown"))
			.log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("access-control-allow-origin"),"*");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		
		String petName=response.jsonPath().get("name").toString();
		System.out.println("Pet name created is: "+petName);
		Assert.assertEquals(pet.getName(),petName);
		Assert.assertEquals(cate.getName(),response.jsonPath().get("category.name").toString());
		System.out.println("Pet CATEGORY name is: "+response.jsonPath().get("category.name").toString());
		petCreated_Id=response.jsonPath().get("id").toString();
		System.out.println("Pet is created with ID -->>: "+petCreated_Id);
		logger.info("%%%%%%%%  Pet Created %%%%%%%%");
	}
	
	
	//@Test(priority=4)
	public void testUpdatePet()
	{
		logger.info("**********  Updating Pet ***************");
		// MUST PASS ALL THE PAYLOAD! not only the fields to upload
		pet.setName(faker.name().firstName());  // update the pet's name
		pet.setStatus("sold");         // update the pet's status 
		// payload fields that will not change:
		pet.setId(num_random);
		pet.setCategory(cate);
		cate.setName(nameUnderCategory);
		cate.setId(idUnderCategory);
		pet.setPhotoUrls(null);
		tag[0]=new Tag();
		tag[0].setIdTag(num_random_for_tag);
		tag[0].setNameTag("puppy");
		pet.setTags(tag);
		
		
		Response response=UserEndPointsFromPropertiesFile.updatePet(pet,petCreated_Id);
		response.then()
			.statusCode(200)
			//.body("type",equalTo("unknown"))
			.log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("access-control-allow-origin"),"*");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("**********  Pet Updated ***************");
	}
}
