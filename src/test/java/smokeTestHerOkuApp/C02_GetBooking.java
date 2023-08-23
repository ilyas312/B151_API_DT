package smokeTestHerOkuApp;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static smokeTestHerOkuApp.C01_CreateBooking.bookingId;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C02_GetBooking extends HerOkuAppBaseUrl {
    /*

    Given
        https://restful-booker.herokuapp.com/booking/:id
When
        Send Get request
Then
        Statuscode 200
And
        Body:
                    {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }

     */


    @Test
    public void get() {
        spec.pathParams("first","booking","second",bookingId);
        // Set expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2018-01-01");
        BookingPojo expectedData = new BookingPojo("Jim"
                , "Brown", 111
                , true, bookingDatesPojo
                , "Breakfast");
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData = convertJsonToJava(response.asString(), BookingPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());


    }
}
