package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13ObjectMapperMap extends JsonPlaceHolderBaseUrl {

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
    public void Get13() {
        //Set url
        spec.pathParams("first","todos","second",198);

        //Set expected data
        String body="{\n" +
                " \"userId\": 10,\n" +
                " \"id\": 198,\n" +
                " \"title\": \"quis eius est sint explicabo\",\n" +
                " \"completed\": true\n" +
                " }";
       Map<String,Object> expectedDataMap = ObjectMapperUtils.convertJsonToJava(body, HashMap.class);
        System.out.println("expectedDataMap = " + expectedDataMap);

        //Send request and responce
       Response response = given(spec).when().get("{first}/{second}");
       response.prettyPrint();

       //Do assertion
       Map<String,Object> actualDataMap = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);

       assertEquals(200,response.statusCode());
       assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
       assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
       assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));



    }
}
