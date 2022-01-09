package Pages;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.FindAll;

import java.util.logging.Logger;

import static Tests.AbstractBaseTests.TestBase.getDriver;
import static Utils.EvidenceReport.capture;
import static Utils.EvidenceReport.gerarRelatorio;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.*;

public class DetalhesAnuncioPage extends BasePage {

    public DetalhesAnuncioPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    private static Logger log = Logger.getLogger("QALogger");


    @iOSXCUITFindBy(id = "tvTitle")
    @AndroidFindBy(id = "tvTitle")
    public MobileElement txtTitulo;

    @iOSXCUITFindBy(id = "txtNameAd")
    @AndroidFindBy(id = "txtNameAd")
    public MobileElement marcaVeiculo;

    @iOSXCUITFindBy(id = "txtNameAd")
    @AndroidFindBy(id = "txtNameAd")
    public MobileElement modeloVeiculo;

    @iOSXCUITFindBy(id = "txtPrice")
    @AndroidFindBy(id = "txtPrice")
    public MobileElement valorVeiculos;

    @iOSXCUITFindBy(id = "txtVesionAd")
    @AndroidFindBy(id = "txtVesionAd")
    public MobileElement versaoVeiculos;


    public void validarDetalhesDeUmVeiculosDaLista(String marca, String modelo) {
        esperarLoadWebmotors();
        log.info("Destalhes do veiculos");

        for (int i = 0; i < 7; i++) {

            String titulo = getDriver().findElementsById("tvTitle").get(i).getText();
            String informacao = getDriver().findElementsById("tvValue").get(i).getText();
            assertNotEquals("Algum titulo da informação do veiculo não foi apresentado!", titulo, isEmptyOrNullString());
            assertNotEquals("Alguma informação do veiculo não foi apresentado!", informacao, isEmptyOrNullString());
            if (!titulo.equals("Detalhes do anúncio")) {
                String title = getDriver().findElementsById("tvTitle").get(i).getText();
                String info = getDriver().findElementsById("tvValue").get(i-1).getText();
                System.out.println(title + ":" + info);
            }

        }
        esperarTextVisivel("Entendi", 15);
        if (verificarSeTextoEstaVisivelNaTela("Entendi")) {
            clickText("Entendi");
        }

        assertEquals("Titulo da tela incorreto", "Detalhes do anúncio", txtTitulo.getText());
        assertTrue("Modelo do veiculo esta divergente!", modeloVeiculo.getText().contains(modelo));
        assertTrue("Marca do veiculo esta divergente!", marcaVeiculo.getText().contains(marca));
        assertNotEquals("Valor do veiculo não foi informado!", valorVeiculos.getText(), isEmptyOrNullString());
        assertNotEquals("Versão do veiculo não foi informado!", versaoVeiculos.getText(), isEmptyOrNullString());
        gerarRelatorio("Detalhes do Veiculo", "Validar Detalhes do veiculo selecionao");


    }

}

