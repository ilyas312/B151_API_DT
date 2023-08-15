package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
    Given
     https://restful-booker.herokuapp.com/booking
    When
     User send a request to the URL
    Then
     Status code is 200
    And
     Among the data there should be someone whose firstname is "Sally" and last name is "Brown"

     */

    @Test
    public void get() {
        //1. Set Base Url
        spec.pathParam("first","booking")
                .queryParams("firstname","John","lastname","Smith");

        //2. Set expected
        //3. Request
        Response response=given(spec).when().get("{first}");
        response.prettyPrint();

        //4. Do Assertion
        response
                .then()
                .statusCode(200)
                .body(containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0)));

        assertTrue(response.asString().contains("bookingid"));




    }
}
