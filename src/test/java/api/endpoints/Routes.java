package api.endpoints;

/* 
Swagger URI --> https://petstore.swagger.io

Create user(Post) : https://petstore.swagger.io/v2/user
Get user (Get): https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

*/

public class Routes 
{
 	public static String base_url  ="https://petstore.swagger.io/v2";
 	
 	//User Model
    public static String post_url  = base_url+"/user";
    public static String get_url=base_url+"/user/{username}";
    public static String update_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";
     
    
    //Store Model
    public static String get_url_store_inventory  = base_url+"/store/inventory";
    public static String place_url_store_order  = base_url+"/store/order";
    public static String get_url_store_order  = base_url+"/store/order/{orderId}";
    public static String delete_url_store_order  = base_url+"/store/order/{orderId}";
    
    //Pet Model
    	//Here you can add Pet url's
}


