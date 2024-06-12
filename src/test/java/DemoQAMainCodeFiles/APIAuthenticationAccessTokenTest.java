package DemoQAMainCodeFiles;


import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.GetCoursesPojo;
import pojo.webAutomation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;


public class APIAuthenticationAccessTokenTest {

    @Test(groups = {"API_Test_Suite"})
    public void pojoAuthenticationAPI(){


        String[] a = {"Selenium Webdriver Java","Cypress","Protractor"};

        String response = given()
                .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust").
                when()
                .log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println("firstRequestResponse "+response);

        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println(accessToken);

        GetCoursesPojo getCourseObject = given()
                .queryParams("access_token", accessToken)
                .when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCoursesPojo.class);
        System.out.println(getCourseObject.getUrl());

        ArrayList<String> actualCourses = new ArrayList<String>();
        List<webAutomation> webAutoCourses = getCourseObject.getCourses().getWebAutomation();
        for(int i=0;i<webAutoCourses.size();i++){
            actualCourses.add(webAutoCourses.get(i).getCourseTitle());
        }
        List<String> expected= Arrays.asList(a);

        Assert.assertEquals(actualCourses, expected);
        System.out.println("Yes");


    }


}
