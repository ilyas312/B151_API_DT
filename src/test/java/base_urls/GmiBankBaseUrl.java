package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GmiBankBaseUrl {
    protected RequestSpecification spec;
    @Before
    public void Setup(){
        spec=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJrX3R3YWluIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9NQU5BR0VSIiwiZXhwIjoxNjkyODcyNDk4fQ.LT4PcltiDzRbwY8EJPNZ4-uix7t5ANIFKFJrItY9H_Hh5BtGtbGjREz24NAN8St59T-3rVMKT5odTRWBHaG0AA")
                .setBaseUri("https://gmibank.com").build();
    }
    // tekrarlı methodlarda kullanılan değerler burade yazılır.
}
