package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationHerOku.generateToken;

public class HerOkuAppBaseUrl {
    protected RequestSpecification spec;
    @Before
    public void Setup(){
        spec=new RequestSpecBuilder().addHeader("Cookie","token="+generateToken())
                .setContentType(ContentType.JSON)
                .setBaseUri("https://restful-booker.herokuapp.com").build();
    }
    // tekrarlı methodlarda kullanılan değerler burade yazılır.
}
