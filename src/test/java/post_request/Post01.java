package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
           1) https://jsonplaceholder.typicode.com/todos
            2)  {
        "userId": 55,
                "title": "Tidy your room",
                "completed": false
    }
    When
    I send POST Request to the Url
            Then
    Status code is 201
    And
    response body is like {
        "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201

     */

    @Test
    public void post() {
        //1 Set url
        spec.pathParam("first","todos");

        //2 Set expected data
        String payload="{\n" +
                " \"userId\": 55,\n" +
                " \"title\": \"Tidy your room\",\n" +
                " \"completed\": false\n" +
                " }";


        //3. Send req and res

        Response response=given(spec).body(payload).when().post("{first");
        response.prettyPrint();

        //4. Do assertion
        JsonPath json=response.jsonPath();
        assertEquals(55, json.getInt("userId"));
        assertEquals("Tidy your room", json.getString("title"));
        assertFalse(json.getBoolean("completed"));
        assertEquals(201, json.getInt("id"));



    }

    //Dinamik kod
    @Test
    public void post01Map() {
        //1 Set url
        spec.pathParam("first","todos");

        //2 Set expected data
        Map<String, Object>expectedData=new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);

        //3. Send req and res
        Response response=given(spec).body(expectedData).when().post("{first}");
        //Burada Serialization yapılıyor. Java object ---> json objesine çeviriyor.
        //Serialization = Java objesini , json objesine çevirme işlemidir.

        response.prettyPrint();

        //4. Do assertion
        Map<String,Object>actualData=response.as(HashMap.class); //Deserialization yapıldı
        //Deserialization=json objesini, Java objesine çevirme işlemidir.

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(201,actualData.get("id"));






    }
}

