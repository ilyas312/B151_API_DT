package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Get06 extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/22
    When
        User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is “application/json”
    And
        Response body should be like;
      {
        "firstname": "John",
        "lastname": "Smith",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
     */

    @Test
    public void get() {
        spec.pathParams("first","booking"
                ,"second",22);

        Response response=given().spec(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //1.Yol
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("John"),
                        "lastname",equalTo("Smith"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast")
                );

        //2.Yol :Responce data içerisindeki değerlere ulaşmak için Jsonpath kullanılır.
        JsonPath json=response.jsonPath();
       // System.out.println(json.getInt("totalprice"));
        assertEquals("John",json.getString("firstname"));
        assertEquals("Smith",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));
        assertTrue(json.getBoolean("depositpaid"));
        assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));
        assertEquals("Breakfast",json.getString("additionalneeds"));

        //SoftAssertion 3 adımda yapılır -->TestNG pom.xml e eklenmelidir.

        //1.Adım Softassertion objesi oluşturulur.
        SoftAssert softAssert=new SoftAssert();

        //2. Adım Assertion yapılır.
        softAssert.assertEquals(json.getString("firstname"),"John","firstname uyuşmadı ----->");
        softAssert.assertEquals(json.getString("lastname"),"Smith","lastname uyuşmadı ----->");
        softAssert.assertEquals(json.getInt("totalprice"),111,"totalprice uyuşmadı ------>");
        softAssert.assertEquals(json.getBoolean("depositpaid"),true);
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast");

        //3. softAssert.assertAll anahtar kelimesini bitirilir
        softAssert.assertAll();

    }
}
