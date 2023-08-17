package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
    1) https://jsonplaceholder.typicode.com/todos/198
    2) {
          "title": "Wash the dishes"
        }
When
  I send PATCH Request to the Url
Then
      Status code is 200
      And response body is like   {
                        "userId": 10,
                        "title": "Wash the dishes",
                        "completed": true,
                        "id": 198
                       }
     */

    @Test
    public void patch() {
        //Set url
        spec.pathParams("first", "todos", "second", 198);

        //Expected data
        Map<String, Object> payload = new JsonPlaceHolderTestData()
                .expectedDataMapper(null, "Wash the dishes", null);

        Map<String, Object> expectedDataMap = new JsonPlaceHolderTestData()
                .expectedDataMapper(10, "Wash the dishes", true);
        expectedDataMap.put("id",198);

        System.out.println("payload = " + payload);
        System.out.println("expectedDataMap = " + expectedDataMap);

        //Send req and get res
        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(payload.get("title"),actualDataMap.get("title"));
        //body nin tamamı assert edilecekse
        // 1. expectedDataMap isminde yeni bir map oluşturup karşılaştırma yapılır
        //2.payload map ine response aldıkran sonra güncelleme yapar beklediğimiz verileri verir.


        //tüm alanların doğrulanması
        assertEquals(200,response.statusCode());
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));

    }
}
