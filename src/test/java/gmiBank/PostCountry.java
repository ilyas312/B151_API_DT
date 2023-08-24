package gmiBank;

import base_urls.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPojo;
import pojos.gmiBankPojos.StatePojos;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class PostCountry extends GmiBankBaseUrl {
    /*
    https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1 dokümanını
     kullanarak en az 3 "state" içeren bir "country" oluşturan
     bir otomasyon testi yazınız.
Not : Autherization için headers'a "Authorization" = ""Bearer abc123"  şeklinde
      Bearer token giriniz.
     */

    /*
  Given
        https://gmibank.com/api/tp-countries
    And
        Body:
            {
              "name": "Banana",
              "states": [
                {
                  "id": 1,
                  "name": "Apple"
                },
                {
                  "id": 2,
                  "name": "Orange"
                },
                {
                  "id": 3,
                  "name": "Pear"
                }
              ]
             }
    When
        send posr request
    Then
        Statuscode 201
    And
        body :
                 {
    "id": 191587,
    "name": "Banana",
    "states": [
        {
            "id": 1,
            "name": "Apple",
            "tpcountry": null
        },
        {
            "id": 2,
            "name": "Orange",
            "tpcountry": null
        },
        {
            "id": 3,
            "name": "Pear",
            "tpcountry": null
        }
    ]
}
     */

    @Test
    public void post() {
        //Set url
        spec.pathParams("first", "api", "second", "tp-countries");

        //Set Expected Data
        StatePojos state1 = new StatePojos(1, "Apple");
        StatePojos state2 = new StatePojos(2, "Orange");
        StatePojos state3 = new StatePojos(3, "Pear");
        List<StatePojos> states = new ArrayList<>();
        states.add(state1);
        states.add(state2);
        states.add(state3);
        CountryPojo expectedData = new CountryPojo("Banana Republic", states);

        //Send  req and res
        Response response = given(spec).body(expectedData).when().post("{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        CountryPojo actualData = convertJsonToJava(response.asString(), CountryPojo.class);

        assertEquals(201, response.statusCode());
        assertEquals(state1.getName(), actualData.getStates().get(0).getName());
        assertEquals(state1.getId(), actualData.getStates().get(0).getId());
        assertEquals(state2.getName(), actualData.getStates().get(1).getName());
        assertEquals(state2.getId(), actualData.getStates().get(1).getId());
        assertEquals(state3.getName(), actualData.getStates().get(2).getName());
        assertEquals(state3.getId(), actualData.getStates().get(2).getId());




    }
}
