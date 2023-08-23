package smokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static smokeTestHerOkuApp.C01_CreateBooking.bookingId;

public class C05_DeleteBooking extends HerOkuAppBaseUrl {
    /*
Given
    https://restful-booker.herokuapp.com/booking/1

When
    sent delete request
Then
    StatusCode 201
And
    body : Created

 */
    @Test
    public void delete() {
        //Set url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set Expected Data
        String expectedData="Created";

        //Send req and res
       Response response = given(spec).when().delete("{first}/{second}");
       response.prettyPrint();

       //Do Assertion
       assertEquals(201,response.statusCode());
       assertEquals(expectedData,response.asString());


    }
}
