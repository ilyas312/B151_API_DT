package tekrar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class Get02a {
    /*
       1- Set the URL = URL'i tanımla
            2- Set the expected data = Beklenen dataları ayarla
            3- Send the request and get the response = İsteği gönder ve cevabı al
            4- Do assertion = Doğrulama yap
     */

    /*
      Given
          https://restful-booker.herokuapp.com/booking/0
      When
          Kullanıcı URL'e bir GET request gönderir
      Then
          HTTP Status code 404 olmalı
      And
          Status Line "HTTP/1.1 404 Not Found" olmalı
      And
          Response body "Not Found" içermeli
      And
          Response body "TechProEd" içermemeli
      And
          Server değeri "Cowboy" olmalı
  */

    @Test
    public void get02() {

        // 1- Set the URL = URL'i tanımla
        String url = "https://restful-booker.herokuapp.com/booking/0";


        //2- Set the expected data = Beklenen dataları ayarla
        //3- Send the request and get the response = İsteği gönder ve cevabı al
        Response response = given().when().get(url);

        //4- Do assertion = Doğrulama yap
        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found");

        assertTrue(response.asString().contains("Not Found"));//response 'u asString() methoduyla String'e cevirip
        // String Class methodlarini (contains vs.) kullanabiliriz
        assertFalse(response.asString().contains("TechProEd"));
        assertEquals(response.asString(), "Not Found");

        assertEquals("Cowboy", response.header("Server"));

        response.
                then()
                .body(containsString("Not Found"))
                .body(not(containsString("TechProEd")));


    }
}
