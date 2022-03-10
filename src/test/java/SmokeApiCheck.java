import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;


public class SmokeApiCheck extends TestBase {

    String token = getAuthToken();

    @Test
    public void mainPageCheck() {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .header("X-user", 13106611)
                .when()
                .get("/segments/rahmetbiz/main")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void aclRolesCheck() {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .header("X-User", 13106611)
                .when()
                .get("/acl/staff/roles")
                .then()
                .statusCode(200)
                .body("message", equalTo("Список ролей доступных сотруднику"))
                .log().body();
    }

    @Test
    public void aclProxyCheck() {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .header("X-User", 13106611)
                .queryParam("proxy_path", "reports/merchant/transactions_indexes")
                .queryParam("filials", 1833)
                .log().all()
                .when()
                .get("acl/proxy")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void aclAppSectionsDynMenu() {
        given()
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .header("X-user", 13106611)
                .log().all()
                .when()
                .get("/acl/v1/app_sections")
                .then()
                .statusCode(200)
                .log().body();
    }
}
