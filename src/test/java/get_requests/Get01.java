package get_requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

    /*
 Given
     https://restful-booker.herokuapp.com/booking/1
 When
     Kullanıcı URL'e bir GET request gönderir
 Then
     HTTP Status Code 200 olmalı
 And
     Content Type "application/json" olmalı
 And
     Status Line "HTTP/1.1 200 OK" olmalı
*/
    @Test
    public void get01(){

        // 1- Set the URL (URl'i tanımla)
        String url = "https://restful-booker.herokuapp.com/booking/1";

        // 2- Set the expected data(Beklenen dataları ayarla)
        // 3- Send the request and get the response(İsteği gönder ve cevabı al)
        Response response = given().when().get();
        response.prettyPrint();

        // 4- Do assertion(Doğrulama yap)
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .statusLine("HTTP/1.1 200 OK");

        // Birinci Yöntem
       // String url = "https://restful-booker.herokuapp.com/booking/1";

        // İkinci Yöntem
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.basePath = "/booking/1";




    }
}


