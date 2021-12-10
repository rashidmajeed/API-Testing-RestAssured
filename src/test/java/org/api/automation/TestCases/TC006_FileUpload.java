package org.api.automation.TestCases;

import java.io.File;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TC006_FileUpload {
    RequestSpecification httpRequest;

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "http://localhost:5002";
        RestAssured.basePath = "/api/upload";
        Header acceptHeader = new Header("Accept", "application/json");
        httpRequest =  RestAssured.given().auth().basic("admin", "admin").header(acceptHeader);
    }

    @Test
    public void uploadFile() {

        File file = new File(System.getProperty("user.dir") + "/src/test/resources/Uploads/rashid.jpeg");
        Response response = httpRequest
                .multiPart("file", file)
                .formParams("name", "QA BOX LET'S TEST")
                .when()
                .post()
                .andReturn();
        System.out.println(response.asPrettyString());

    }

}