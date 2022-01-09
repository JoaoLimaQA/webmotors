package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static Utils.EvidenceReport.gerarRelatorio;

public class LoginPage extends BasePage {

    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    @iOSXCUITFindBy(id = "etEmail")
    @AndroidFindBy(id = "etEmail")
    public MobileElement input_email;

    @iOSXCUITFindBy(id = "etPassword")
    @AndroidFindBy(id = "etPassword")
    public MobileElement input_senha;

    @iOSXCUITFindBy(id = "btLogin")
    @AndroidFindBy(id = "btLogin")
    public MobileElement bt_Entrar;


    public void fazerLoginNoAppWebmotors(String email, String senha) {
        esperarTextVisivel("Entre com seu login",5);
        input_email.sendKeys(email);
        input_senha.sendKeys(senha);
        gerarRelatorio("Realizar Login","Digitar login e senha válidos");
        bt_Entrar.click();


    }

}
