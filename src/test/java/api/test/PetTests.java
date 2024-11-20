package api.test;

import static org.hamcrest.Matchers.equalTo;
import java.util.ArrayList;
import java.util.List;
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
	
	Faker faker;
	Pet pet;
	Category cate;
	Tag tag;
	public Logger logger;
	int petIdToSearch;
	//List<Tag> allTags;
	
	ArrayList<Tag> allTags = new ArrayList<>();
	
	@BeforeClass
	public void setupData(){
		
		Random random = new Random();
		faker=new Faker();
		pet=new Pet();
		cate=new Category();
		tag=new Tag();
		
		// to understand the PET payload consider
		//web site https://jsonformatter.com
		int upperbound = 15;
		int int_random = random.nextInt(upperbound);
		pet.setId(int_random);
		pet.setCategory(cate);
		cate.setName(faker.name().username());
		cate.setId(faker.idNumber().hashCode());
		pet.setName(faker.name().firstName());
		pet.setPhotoUrls(null);
		pet.setTags(allTags);
		
		//Must pass a list of tags (TO BE DEVELOPED)
		
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
		System.out.println("Pet ID selected: "+this.pet.getId());
		System.out.println(this.pet.getCategory().getId());
		System.out.println(this.pet.getCategory().getName());
		
		Assert.assertEquals(response.getStatusCode(),200);	
		Assert.assertEquals(response.header("Content-Type"),"application/json");
		Assert.assertEquals(response.header("Access-Control-Allow-Methods"),"GET, POST, DELETE, PUT");
		Assert.assertEquals(response.header("Access-Control-Allow-Headers"),"Content-Type, api_key, Authorization");
		Assert.assertEquals(response.header("Server"),"Jetty(9.2.9.v20150224)");
		logger.info("%%%%%%%% Pet By Id Displayed %%%%%%%%"); 
		
		int idPet=response.jsonPath().get("id");
		Assert.assertEquals(idPet,this.pet.getId());
	}

}
