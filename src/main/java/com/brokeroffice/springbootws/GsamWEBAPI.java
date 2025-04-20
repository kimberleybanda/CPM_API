package com.brokeroffice.springbootws;





import com.brokeroffice.springbootws.helpers.AppConfigReader;
import com.brokeroffice.springbootws.helpers.Dao;


import com.brokeroffice.springbootws.models.SalesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import zw.co.paynow.constants.MobileMoneyMethod;
import zw.co.paynow.core.Payment;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.responses.MobileInitResponse;

import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//@SpringBootApplication
@EnableScheduling
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2WebMvc
@EnableCaching
//@EnableSwagger2

//@SpringBootApplication
public class GsamWEBAPI {

	 ReentrantLock lock = new ReentrantLock();
	//static String pollUrl="";
	//static Paynow paynow;
	//static WebInitResponse response;
	//static StatusResponse status;
	int counter = 0;
/*
public static class Prices{
	Prices(String name, double price){
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Prices{" +
				"name='" + name + '\'' +
				", price=" + price +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Prices prices = (Prices) o;
		return Double.compare(prices.price, price) == 0 && Objects.equals(name, prices.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, price);
	}

	String name;
	double price;
}

public static List<Prices> ss(){
	List<Prices>counterDetails = new ArrayList<>();
	try{

		System.out.println(String.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now())));
		    CallableStatement stmt =  Dao.connection().prepareCall("{call spEQCounterDetails(?, ?)}");
			// Set the input parameters
			stmt.setString(1, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
			stmt.setString(2,DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
		    ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			//System.out.println(rs.getString("name"));
		//	System.out.println(rs.getInt("Price"));
			counterDetails.add(new Prices(rs.getString("name"),rs.getInt("Price")));

		}
System.out.println(counterDetails);
	}
	catch(Exception ex)
	{
		System.out.println(ex.getMessage());
	}
	return counterDetails;
	}
*/


//	public static void sendJsonArray(String name,byte[]   base64,String level) throws Exception {
	public static void sendJsonArray(String name,String  base64,String level) throws Exception {
		// Implement your code to send the JSON array through an endpoint
		// This can involve making an HTTP request, using a REST client, etc.
		// Example:
		Connection connection = Dao.connection();
		String sql = "INSERT INTO pdf (name1,level,base64m) VALUES (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, level);
		//statement.setBytes(3, base64);
		statement.setString(3, base64);




		// Execute the SQL statement
		int rowsInserted = statement.executeUpdate();
		if (rowsInserted > 0) {
			System.out.println("JSON array inserted successfully.");
		} else {
			System.out.println("Failed to insert JSON array.");
		}

		// Close the resources
		//statement.close();
		//connection.close();
	}
	public static final String ACCOUNT_SID = System.getenv("AC1160313e79d5dd90762d6551f21736f0");
	public static final String AUTH_TOKEN = System.getenv("c597d255ed2f5034fbd21b9c0ddf9759");

	public static void main(String[] args) throws Exception{

		SpringApplication.run(GsamWEBAPI.class, args);


		int x =9;
		}



	public static boolean validatePhoneNumber(String phoneNumber) {
		// Define the pattern to match exactly "07" followed by 8 digits

		Pattern pattern = Pattern.compile("^2637[0-9]{8}$");
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

	@Bean
	public WebMvcConfigurer customConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
				configurer.defaultContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON));
			}
		};
	}


	@Bean
	//public CorsFilter corsFilter(){
		public void corsFilter(){
	//	X-Frame-Options "allow-from *"
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://biactutors.co.zw","http://localhost:3000","http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("X-Frame-Options","Origin","Access-Control-Allow-Origin",
				"Content-Type","Accept","Jwt-Token","Authorization",
				"Origin,Accept","X-Requested-With","Access-Control-Requested-Method",
				"Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("X-Frame-Options","Origin","Access-Control-Allow-Origin",
				"Content-Type","Accept","Jwt-Token","Authorization",
				"Origin,Accept","X-Requested-With","Access-Control-Requested-Method",
				"Access-Control-Request-Headers","filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT","PATCH","OPTIONS"));
		//urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
		urlBasedCorsConfigurationSource.registerCorsConfiguration("allow-from *",corsConfiguration);
		//return new CorsFilter(urlBasedCorsConfigurationSource);
	}



	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD",
				"GET", "POST", "PUT", "DELETE", "PATCH"));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

//@Scheduled(fixedRate = 1000) // Executes every 1 second
	public void insertSales() throws UnirestException {
	System.out.println("Fetching data from the API...");

	Unirest.setTimeouts(0, 0);
	HttpResponse<String> response = Unirest.get("https://vendorapp.generikssolutions.co.zw/api.php?id=cpm")
			.asString();

	// Check if the request was successful
	if (response.getStatus() == 200) {
		// Get the JSON response body
		String jsonResponse = response.getBody();

		// Parse the JSON into a list of Product objects
		Gson gson = new Gson();
		Type productListType = new TypeToken<List<SalesModel>>(){}.getType();
		List<SalesModel> products = gson.fromJson(jsonResponse, productListType);
		bulkInsertProducts(products);
		// Print the products
		/*for (SalesModel product : products) {
			System.out.println(product);
		}*/
	} else {
		System.err.println("Failed to fetch data. Status code: " + response.getStatus());
	}


	}

///===========
public static void bulkInsertProducts(List<SalesModel> products) {
	String sql = "INSERT INTO customer_sales (uuid, shopId, productName, price, count, phone) VALUES (?, ?, ?, ?, ?, ?)";

	try (Connection connection = Dao.connection();
		 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

		// Disable auto-commit to manage transactions manually
		connection.setAutoCommit(false);

		// Add each product to the batch
		for (SalesModel product : products) {
			preparedStatement.setString(1, product.getUuid());
			preparedStatement.setString(2, product.getShopId());
			preparedStatement.setString(3, product.getProductName());
			preparedStatement.setString(4, product.getPrice());
			preparedStatement.setInt(5, product.getCount());
			preparedStatement.setString(6, product.getPhone());
			preparedStatement.addBatch(); // Add the statement to the batch
		}

		// Execute the batch
		int[] result = preparedStatement.executeBatch();

		// Commit the transaction
		connection.commit();

		System.out.println("Bulk insert completed. Rows inserted: " + result.length);

	} catch (SQLException e) {
		System.err.println("Error during bulk insert: " + e.getMessage());
		e.printStackTrace();
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
}
	@Override
	public boolean equals(Object o) {
		Double dataObj_=0.0;
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DataObj dataObj = (DataObj) o;
		return Double.compare(dataObj.dataObj, dataObj_) == 0 && Objects.equals(dataObj.dataObj, dataObj_);
	}
	class DataObj{
	Double dataObj;
	}

}




