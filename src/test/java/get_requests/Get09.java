package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {

    /*
    Given
    https://restful-booker.herokuapp.com/booking/92

    When
    I send GET Request to the url

    Then
    Response body should be like that;
            {
            "firstname": "Jane",
            "lastname": "Doe",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
                            },
            "additionalneeds": "Midnight snack"
            }
     */


    @Test
    public void get() {
        //Set url
        spec.pathParams("first", "booking", "second", 92);

        //Set expected data

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");
        System.out.println(bookingDatesMap);

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "Jane");
        expectedDataMap.put("lastname", "Doe");
        expectedDataMap.put("totalprice", 111);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingDatesMap);
        expectedDataMap.put("additionalneeds", "Extra pillows please");
        System.out.println("expectedDataMap = " + expectedDataMap);


        //Send req and res
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualData = " + actualDataMap);

        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));
        // assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("bookingdates"));
        assertEquals(bookingDatesMap.get("checkin"), ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map) actualDataMap.get("bookingdates")).get("checkout"));




        /*
        Object map=new HashMap<>();
        ((Map)map).get();

         */

    }

    @Test
    public void get02() {
        //Set url
        spec.pathParams("first", "booking", "second", 92);

        //Set expected data
        Map<String, String> bookingMap = new HerOkuAppTestData().bookingDateMapper("2018-01-01", "2019-01-01");
       Map<String,Object> expectedDataMap= new HerOkuAppTestData().expectedDataMapper("Josh", "Allen", 111, true, bookingMap, "midnight snack");

        //Send req and res
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualData = " + actualDataMap);

        assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        assertEquals(expectedDataMap.get("additionalneeds"), actualDataMap.get("additionalneeds"));
        // assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),((Map)actualDataMap.get("bookingdates")).get("bookingdates"));
        assertEquals(bookingMap.get("checkin"), ((Map) actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingMap.get("checkout"), ((Map) actualDataMap.get("bookingdates")).get("checkout"));


    }
}
