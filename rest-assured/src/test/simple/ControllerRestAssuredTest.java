package simple;

import groovy.transform.Trait;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.Header;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import simple.app.Application;
import simple.app.model.Example;

import javax.transaction.Transactional;
import java.time.LocalDate;

//parameters needed
@SpringBootTest(
		classes = Application.class,
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@RunWith(SpringRunner.class)
public class ControllerRestAssuredTest {


	@Test
	public void testGetByName() {
		RestAssured.get("/examples/one").then().statusCode(200).assertThat()
				.body("name", CoreMatchers.equalTo("one"));
	}

	@Test
	public void testGetByNameFullObjectResultCheck() {

		Example check = new Example("one", 1l, LocalDate.of(2023,9,10));

		Example result = RestAssured.get("/examples/one").then().statusCode(200)
				.extract().as(Example.class);

		Assert.assertEquals(result, check);
	}

	@Test
	public void testCreateExample() {
		RestAssured.given()
				.header(new Header("Content-Type", "application/json"))
				.header(new Header("Accept", "application/json"))
				.with()
				.body(new Example("newName", 10l, LocalDate.of(2023, 11,1)))
				.when()
				.post( "/examples")
				.then()
				.statusCode(204);
	}

	@Test
	public void testPutExample() {
		RestAssured.given()
				.header(new Header("Content-Type", "application/json"))
				.header(new Header("Accept", "application/json"))
				.with()
				.body(new Example("newName", 10l, LocalDate.of(2023, 11,1)))
				.when()
				.put( "/examples/one")
				.then()
				.statusCode(204);
	}

	//dirties context used to mark the application context as dirty and force reset (getByName might return 404 without this)
	@DirtiesContext()
	@Test
	public void testDeleteExample() {

		RestAssured.delete("/examples/one")
				.then()
				.statusCode(204);
	}
}
