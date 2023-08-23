package smokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static smokeTestHerOkuApp.C01_CreateBooking.bookingId;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C06_GetDeletedBooking extends HerOkuAppBaseUrl {
    /*

    Given
        https://restful-booker.herokuapp.com/booking/:id
When
        Send Get request
Then
        Statuscode 200
And
        Body:Not Found

     */


    @Test
    public void get() {
        //Set Url
        spec.pathParams("first","booking","second",bookingId);

        // Set expected data
        String expectedData="Not Found";

        //Send req and res
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,response.asString());

    }
}
