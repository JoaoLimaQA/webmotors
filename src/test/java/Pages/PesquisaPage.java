package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.util.logging.Logger;

import static Utils.EvidenceReport.gerarRelatorio;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PesquisaPage extends BasePage {

    public PesquisaPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    private static Logger log = Logger.getLogger("QALogger");


    @iOSXCUITFindBy(id = "txtModelo")
    @AndroidFindBy(id = "txtModelo")
    public MobileElement modeloVeiculo;

    @iOSXCUITFindBy(id = "txtModelo")
    @AndroidFindBy(id = "txtModelo")
    public MobileElement marcaVeiculo;

    @iOSXCUITFindBy(id = "txtLocalizacao")
    @AndroidFindBy(id = "txtLocalizacao")
    public MobileElement localizacaoVeiculo;

    @iOSXCUITFindBy(id = "txtQuantVeich")
    @AndroidFindBy(id = "txtQuantVeich")
    public MobileElement qtsVeiculos;

    @iOSXCUITFindBy(id = "txtPreco")
    @AndroidFindBy(id = "txtPreco")
    public MobileElement vlrVeiculos;

    @iOSXCUITFindBy(id = "txtVersao")
    @AndroidFindBy(id = "txtVersao")
    public MobileElement versaoVeiculos;

    @iOSXCUITFindBy(id = "imgCar")
    @AndroidFindBy(id = "imgCar")
    public MobileElement verDetalhesDoVeiculo;


    public void validarListaEDetalhesVeiculos(String estado , String cidade, String marca, String modelo) {
        assertTrue("Marca do veiculo listado esta divergente do filtro!",marcaVeiculo.getText().contains(marca));
        assertTrue("Modelo do veiculo listado esta divergente do filtro!",modeloVeiculo.getText().contains(modelo));
        assertTrue("Estado do veiculo listado esta divergente do filtro!",localizacaoVeiculo.getText().contains(estado));
        assertTrue("Cidade do veiculo listado esta divergente do filtro!",localizacaoVeiculo.getText().toLowerCase().contains(cidade.toLowerCase()));
        assertNotEquals("Valor do veiculo listado não foi apresentado!",vlrVeiculos.getText(),isEmptyOrNullString());
        assertNotEquals("Versão do veiculo listado não foi apresentado!",versaoVeiculos.getText(),isEmptyOrNullString());
        assertTrue(qtsVeiculos.getText().contains("carros encontrados"));
        assertNotEquals("Quantidades de veiculos nãoo foi exibido!",qtsVeiculos.getText().charAt(0),isEmptyOrNullString());
        gerarRelatorio("Lista de Veiculos","Validar listar do resultado da busca");



    }
    public void selecionaVeiculoDaListaDePesquisa() {

        verDetalhesDoVeiculo.click();




    }

}
