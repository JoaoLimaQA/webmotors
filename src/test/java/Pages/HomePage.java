package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static Tests.AbstractBaseTests.TestBase.getDriver;
import static Utils.EvidenceReport.capture;
import static Utils.EvidenceReport.gerarRelatorio;

public class HomePage extends BasePage {

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    @iOSXCUITFindBy(xpath = "//*[contains(@text,'comprar')]")
    @AndroidFindBy(xpath = "//*[@text='Quero\n"+"comprar']")
    public MobileElement pesquiosarVeiculo;

    @iOSXCUITFindBy(xpath = "//*[contains(@text,'Fechar')]")
    @AndroidFindBy(xpath = "//*[@text='Fechar']")
    public MobileElement fecharPopUP;

    @iOSXCUITFindBy(xpath = "//*[contains(@text,'AGORA NÃO')]")
    @AndroidFindBy(xpath = "//*[@text='AGORA NÃO']")
    public MobileElement naoSalvarSenhaNoGoogle;



    public void queroComprarUmVeiculo() {
        esperarTextVisivel("comprar",5);
        gerarRelatorio("Pesquisar Veiculo","Clique em Quero comprar");
        pesquiosarVeiculo.click();

    }

    public void fecharPopUPInformativo() {
        esperarLoadWebmotors();
        if (verificarSeTextoEstaVisivelNaTela("Fechar")){
            fecharPopUP.click();

        }


    }

    public void salvarSenhaMoGoogleNao() {
        if (verificarSeTextoEstaVisivelNaTela("Salvar senha")){
            naoSalvarSenhaNoGoogle.click();


        }
    }
}
