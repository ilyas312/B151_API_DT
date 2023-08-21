package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13ObjectMapperPojo extends JsonPlaceHolderBaseUrl {
    /*

    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        I send Get Request to the Url
    Then
        Status code is 200
    And
        response body is like {
                                "userId": 10,
                                "id": 198,
                                "title": "quis eius est sint explicabo",
                                "completed": true
                               }

     */

    @Test
    public void get() {
        //Set url
        spec.pathParams("first", "todos", "second", 198);

        //Set expected data
       /* String body = "{\n" +
                " \"userId\": 10,\n" +
                " \"id\": 198,\n" +
                " \"title\": \"quis eius est sint explicabo\",\n" +
                " \"completed\": true\n" +
                " }";

        */
        String body = JsonPlaceHolderTestData.convertJsonToString(
                10,
                "quis eius est sint explicabo",
                true);

        //JsonPlaceHolderPojo exp2 = new JsonPlaceHolderPojo(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedDataPojo = ObjectMapperUtils.convertJsonToJava(body, JsonPlaceHolderPojo.class);
        System.out.println("expectedDataPojo = " + expectedDataPojo);

        //Send req and res
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        JsonPlaceHolderPojo actualDataPojo2 = response.as(JsonPlaceHolderPojo.class);
        JsonPlaceHolderPojo actualDataPojo = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedDataPojo.getUserId(), actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());

    }
}
