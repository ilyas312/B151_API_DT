package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuuappBaseUrl {
    protected RequestSpecification spec;
    @Before
    public void Setup(){
        spec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
    // tekrarlı methodlarda kullanılan değerler burade yazılır.
}
