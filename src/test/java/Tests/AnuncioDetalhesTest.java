package Tests;

import Pages.*;
import Tests.AbstractBaseTests.TestBase;
import Utils.CustomListener;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.util.logging.Logger;

import static Pages.BasePage.*;
import static Tests.AbstractBaseTests.GetTags.cenarioEAmbiente;
import static Utils.ConfigFileReader.dashboardConfig;
import static Utils.EvidenceReport.report_failed;
import static io.restassured.RestAssured.given;


@CucumberOptions(
        features = "classpath:Features",
        glue = "Tests",
        plugin = {"pretty","com.cucumber.listener.ExtentCucumberFormatter:src/test/resources/cucumber-reports/report.html","json:target/maven-cucumber-report"

        })

@Listeners(CustomListener.class)
public class AnuncioDetalhesTest extends TestBase {


    private static Logger log = Logger.getLogger("QALogger");

    private LoginPage loginPage;
    private HomePage homePage;
    private BuscarVeiculosPage buscarVeiculosPage;
    private PesquisaPage pesquisaPage;
    private DetalhesAnuncioPage detalhesAnuncioPage;

    private String estado;
    private String cidade;
    private String marca;
    private String modelo;




    @Given("^que eu possua conta no app do webmotors$")
    public void queEuPossuaContaNoAppDoWebmotors() throws IOException {


    }


    public AnuncioDetalhesTest() throws IOException {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        buscarVeiculosPage = new BuscarVeiculosPage(driver);
        pesquisaPage = new PesquisaPage(driver);
        detalhesAnuncioPage = new DetalhesAnuncioPage(driver);

    }



    @When("^faco login no app com \"([^\"]*)\" e \"([^\"]*)\"$")
    public void facoLoginNoAppComCpfESenhaApp(String email ,String senhaApp) throws Throwable {
        loginPage.fazerLoginNoAppWebmotors(email,senhaApp);
        homePage.fecharPopUPInformativo();
        homePage.salvarSenhaMoGoogleNao();


    }

    @When("^clico em quero comprar um veiculo no app webmotors$")
    public void clicoEmQueroComprarUmVeiculoNoAppWebmotors() throws Throwable {
        homePage.queroComprarUmVeiculo();


    }

    @When("^realizo os filtros necessario para encontrar o veiculo \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void realizo_os_filtros_necessario_para_encontrar_o_veiculo(String estado, String cidade, String marca, String modelo) throws Throwable {

        buscarVeiculosPage.realizarFiltroDePesquisaParaLocalidade(estado,cidade);
        buscarVeiculosPage.realizarFiltroDePesquisaParaTipoDoVeiculo(marca,modelo);
        this.estado = estado;
        this.cidade = cidade;
        this.marca = marca;
        this.modelo = modelo;


    }

    @When("^clico em ver anuncios disponiveis$")
    public void clicoEmVerAnunciosDisponiveis() throws Throwable {
        buscarVeiculosPage.clicarEmVerAnunciosDisponives();



    }

    @When("^devo ver a lista de veiculos disponiveis$")
    public void devoVerAListaDeVeiculosDisponiveis() throws Throwable {
        pesquisaPage.validarListaEDetalhesVeiculos(estado,cidade,marca,modelo );





    }

    @When("^seleciono o veiculo do meu interesse$")
    public void selecionoOVeiculoDoMeuInteresse() throws Throwable {
        pesquisaPage.selecionaVeiculoDaListaDePesquisa();


    }

    @When("^valido as informacoes detalhadas do veiculo selecionado$")
    public void validoAsInformacoesDetalhadasVeiculoSelecionado() throws Throwable {

        detalhesAnuncioPage.validarDetalhesDeUmVeiculosDaLista(marca,modelo);


    }



    @After(order = 1)
    public void afterScenario(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            try {

                saveVideo();
                //salvar video da execucao
                dashboardConfig();
                //personalizando dashboard
                add_screenshot_cucumber_report(cenarioEAmbiente());
                //add evidencia da falha no dasshboard
                report_failed();
                //add evidencia da falha no relatorio
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            saveVideo();
            dashboardConfig();
        }
    }


    @After(order = 0)
    public void resetAp() {
        getDriver().resetApp();

    }


}