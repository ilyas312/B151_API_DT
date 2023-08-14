package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonplaceholderBaseUrl {
    protected RequestSpecification spec;
    @Before
    public void Setup(){
        spec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    // tekrarlı methodlarda kullanılan değerler burade yazılır.
}
