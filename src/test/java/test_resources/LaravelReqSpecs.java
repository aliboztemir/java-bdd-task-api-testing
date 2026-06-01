package test_resources;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java_resources.ExternalData;
import java_resources.Payloads;

public class LaravelReqSpecs {

	private static String baseUri = ExternalData.getGlobalData("baseUri");
	private static ContentType contentType = ContentType.JSON;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private static LocalDateTime time;
	
	public static RequestSpecification laravelReqSpec()
	{
		PrintStream log = null;
		try
		{
			log = new PrintStream(new FileOutputStream("logging.txt", true));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		time = LocalDateTime.now();
		log.append("\n******************************************************\n");
		log.append("***   Laravel Request Sent at: " + dtf.format(time).toString() + "   ***\n");
		log.append("******************************************************\n\n");
		
		// Create a generic RequestSpecification that can be used for all Laravel API requests (and not just for AddUserAPI)
		RequestSpecification laravelReqSpec =	new RequestSpecBuilder()
												.addFilter(RequestLoggingFilter.logRequestTo(log))
												.addFilter(ResponseLoggingFilter.logResponseTo(log))
												.setBaseUri(baseUri)
												.setContentType(contentType)
												.build();
		
		return laravelReqSpec;
	}
	public static RequestSpecification loginApiReqSpec(String email, String password)
	{
		RequestSpecification loginApiReqSpec = given().spec(LaravelReqSpecs.laravelReqSpec()).body(Payloads.loginApiPayload(email, password));
		return loginApiReqSpec;
	}
	
	public static RequestSpecification addTaskReqSpec(String title, String access_token)
	{
		RequestSpecification addTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(LaravelReqSpecs.laravelReqSpec()).body(Payloads.addTaskPayload(title));
		return addTaskReqSpec;
	}
	
	public static RequestSpecification putTaskReqSpec(String task_id, String title, String access_token)
	{
		RequestSpecification putTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(LaravelReqSpecs.laravelReqSpec()).body(Payloads.putTaskPayload(title)).pathParam("id", task_id);
		return putTaskReqSpec;
	}
	
	public static RequestSpecification getUserReqSpec(String user_id, String data)
	{
		if (data.equals("invalid")) {
			user_id = "invaliduserid";
		} 
		
		RequestSpecification getUserReqSpec = given().spec(LaravelReqSpecs.laravelReqSpec().pathParam("id", user_id));
		return getUserReqSpec;
		
	}
	
	public static RequestSpecification getTaskReqSpec(String task_id, String data, String access_token)
	{
		if (data.equals("invalid")) {
			task_id = "invaliduserid";
		} 
		
		RequestSpecification getTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(LaravelReqSpecs.laravelReqSpec().pathParam("id", task_id));
		return getTaskReqSpec;
		
	}
	
	public static RequestSpecification deleteTaskReqSpec(String task_id, String data, String access_token)
	{
		if (data.equals("invalid")) {
			task_id = "invaliduserid";
		} 
		
		RequestSpecification deleteTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(LaravelReqSpecs.laravelReqSpec().pathParam("id", task_id));
		return deleteTaskReqSpec;
		
	}
	
	public static RequestSpecification getAllTaskReqSpec(String data, String access_token)
	{
		RequestSpecification getAllTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(LaravelReqSpecs.laravelReqSpec());
		return getAllTaskReqSpec;
		
	}
}
