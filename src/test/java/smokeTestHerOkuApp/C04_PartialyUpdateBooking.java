package smokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static smokeTestHerOkuApp.C01_CreateBooking.bookingId;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04_PartialyUpdateBooking extends HerOkuAppBaseUrl {

    /*
    Given
      https://restful-booker.herokuapp.com/booking/:id
    And

        {
        "firstname" : "Sakin",
        "lastname" : "Browny"
        }

        When
            sent patch request
        Then
            statuscode 200
        And
                        {
                "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
                    "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
                },
                "additionalneeds" : "Breakfast"
            }

 */

    @Test
    public void patch() {
        //Set Url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set Expected Data
        Map<String, Object> payload = new HerOkuAppTestData().expectedDataMapper(
                "Sakin"
                , "Browny"
                , null
                , null
                , null
                , null);
        System.out.println("payload = " + payload);

        //Send req and res
        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData = convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(payload.get("firstname"),actualData.get("firstname"));
        assertEquals(payload.get("lastname"),actualData.get("lastname"));

    }
}
