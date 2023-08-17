package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public Map<String,String> bookingDateMapper(String checkin,String checkout ){
        Map<String,String> bookingDatesMap=new HashMap<>();
        bookingDatesMap.put("checkin",checkin);
        bookingDatesMap.put("checkout",checkout);
        return bookingDatesMap;
    }

    public Map<String, Object> expectedDataMapper(String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingdates,String additionalneeds){
        Map<String, Object>expectedDataMap=new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);
        if (additionalneeds!=null){
            expectedDataMap.put("additionalneeds",additionalneeds);
        }
       return expectedDataMap;
    }
/* Bu method da kullanılabilir
    public Map<String, Object> expectedDataMapper(String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingdates){
        Map<String, Object>expectedDataMap=new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);

        return expectedDataMap;
    }

 */



}
