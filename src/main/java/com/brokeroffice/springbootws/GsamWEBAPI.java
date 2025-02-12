package com.brokeroffice.springbootws;





import com.brokeroffice.springbootws.helpers.Dao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.mail.*;
import javax.mail.internet.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


//@SpringBootApplication
//@EnableScheduling
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




