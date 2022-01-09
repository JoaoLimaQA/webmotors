package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static Tests.AbstractBaseTests.TestBase.getDriver;
import static Utils.EvidenceReport.gerarRelatorio;
import static org.junit.Assert.fail;

public class BuscarVeiculosPage extends BasePage {

    public BuscarVeiculosPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    
    @iOSXCUITFindBy(xpath = "//*[@text='Estado']")
    @AndroidFindBy(xpath = "//*[@text='Estado']")
    public MobileElement selecionarEstado;

    @iOSXCUITFindBy(xpath = "//*[@text='Cidade']")
    @AndroidFindBy(xpath = "//*[@text='Cidade']")
    public MobileElement selecionarCidade;

    @iOSXCUITFindBy(xpath = "//*[@text='Marca']")
    @AndroidFindBy(xpath = "//*[@text='Marca']")
    public MobileElement selecionarMarca;

    @iOSXCUITFindBy(xpath = "//*[@text='Marca']")
    @AndroidFindBy(xpath = "//*[@text='Modelo']")
    public MobileElement selecionarModelo;

    @iOSXCUITFindBy(xpath = "//*[@text='Ver anúncios']")
    @AndroidFindBy(xpath = "//*[@text='Ver anúncios']")
    public MobileElement btn_verAnuncios;

    @iOSXCUITFindBy(id = "edtSearchFilter")
    @AndroidFindBy(id = "edtSearchFilter")
    public MobileElement input_pesquisa;

    private String cidade;



    public void realizarFiltroDePesquisaParaLocalidade(String estado, String city) {
        /**
         * selecionar Estado
        */
        gerarRelatorio("Filtros","Filtrar parametros desejados");
        selecionarEstado.click();
        esperarLoadWebmotors();
        input_pesquisa.sendKeys(estado);
        getDriver().findElementsByXPath("//*[contains(@text,'"+estado+"')]").get(1).click();

        /**
         * selecionar Cidade
         */

        selecionarCidade.click();
        esperarLoadWebmotors();
        input_pesquisa.sendKeys(city);
        String cidade = city.toLowerCase();
        String cidadee = cidade.substring(0, 1).toUpperCase() + cidade.substring(1);
        getDriver().findElementByXPath("//*[contains(@text,'"+cidadee+"')]").click();
        this.cidade = cidade;


    }


    public void realizarFiltroDePesquisaParaTipoDoVeiculo(String marca, String modelo) {
        /**
         * selecionar Marca do veiculo
         */
        selecionarMarca.click();
        esperarLoadWebmotors();
        esperarAnimacao(3);
        gerarRelatorio("Filtrar Marca","Seleciono a marca "+marca+"");
        input_pesquisa.sendKeys(marca);
        try {
            getDriver().findElementsByXPath("//*[contains(@text,'"+marca+"')]").get(1).click();
        }catch (Exception e){
            fail("Marca "+marca+" não encontrada, para a cidade de "+cidade+"");
        }


        /**
         * selecionar Modelo do veiculo
         */
        selecionarModelo.click();
        esperarLoadWebmotors();
        gerarRelatorio("Filtrar Modelo","Seleciono o modelo "+modelo+"");
        input_pesquisa.sendKeys(modelo);
        try {
            getDriver().findElementsByXPath("//*[contains(@text,'" + modelo + "')]").get(1).click();

        }catch (Exception e){
            fail("Modelo de veiculo "+modelo+" não encontrado, para a cidade de "+cidade+"");
        }
    }

    public void clicarEmVerAnunciosDisponives() {
        gerarRelatorio("Ver Anúmcios","clicar em Ver Anúncios");
        clickText("Ver anúncios");
        esperarTextVisivel("Ver Favoritos",5);
        fecharPopupMeusFavoritos();


    }



}
