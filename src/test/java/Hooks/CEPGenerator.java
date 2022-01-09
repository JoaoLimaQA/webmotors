package Hooks;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class CEPGenerator {
    public static String gerarCepAleatorio() {
        String cep;
        try {
            cep = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("https://geradornv.com.br/wp-json/api/cep/random-by-states?state=SP")
                    .then()
                    .statusCode(200)
                    .extract().path("cep");
            return cep;
        }catch (Exception e){
            return "61991975";
        }
    }
}
