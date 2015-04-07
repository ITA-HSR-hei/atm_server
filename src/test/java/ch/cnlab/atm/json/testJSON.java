package ch.cnlab.atm.json;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.hasXPath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class testJSON {
	@Before
	public void setUp() {
		RestAssured.registerParser("text/json", Parser.JSON);
		RestAssured.basePath = "/atm";
	}

	// @Test
	// public void testStationsListMaennedorf() {
	// expect().statusCode(200).body("stationList.id", hasItems(1),
	// "stationList.name", hasItem("Maennedorf")).when()
	// .get("/public/0/getStations");
	// }
	/**
	 * @Test public void testStationsListWeesen(){ expect().statusCode(200)
	 *       .body("stationList.id", hasItem(2),"stationList.name",
	 *       hasItem("Weesen")) .when().post("/public/0/getStations").print();
	 * 
	 *       }
	 **/

}
