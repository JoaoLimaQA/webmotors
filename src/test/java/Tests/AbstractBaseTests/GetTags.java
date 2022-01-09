package Tests.AbstractBaseTests;

import Hooks.AutomationUtils;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

import static Pages.BasePage.startVideo;
import static Tests.AbstractBaseTests.TestBase.getTags;



public class GetTags {
    private static Collection<String> tagNames;
    private static String nomeDoCenario;
    private static String nomeDaFeature;
    private static String ambienteAPP;


    @Before(order = 0)
    public void getScenarioInfo(Scenario scenario) throws Exception {

        nomeDoCenario = scenario.getName();
        tagNames = scenario.getSourceTagNames();
        nomeDaFeature = scenario.getId().split(";")[0].replace("-", " ");
        ambienteAPP = getAmbienteAPP();

    }

   @Before(order = 1)
    public void iniciarVideo() {
        startVideo();



    }


    public String getNomeDoCenario() {
        return nomeDoCenario;
    }

    public Collection<String> getTagNames() {
        return tagNames;
    }

    public static String objetivoCenario() {
        String objTest = getTags.getNomeDoCenario();
        String cenario = objTest.replace("(", "").replaceAll("(.*)\\) - ", "");
        return cenario;
    }

    public static String cenarioEAmbiente() {
        String nome = getTags.getNomeDoCenario();
        String cenario = StringUtils.substringBetween(nome, "@", ")");

        if (AutomationUtils.getProperty("environment").contains("HLH")) {
            cenario += "_hlg";
        } else {
            cenario += "_prod";
        }

        return cenario;
    }
    public static String cenario() {
        String nome = getTags.getNomeDoCenario();
        String cenario = StringUtils.substringBetween(nome, "@", ")");
        return cenario;
    }

    public static String nomeFrente() {
        String featureName = "Feature ";
        String feature = featureName + nomeDaFeature.substring(0, 1).toUpperCase() + nomeDaFeature.substring(1);
        String frente = StringUtils.substringBetween(feature, "@", ")");
        return frente.replace(" ", "_");
    }


    public static String getAmbienteAPP() {

        if (AutomationUtils.getProperty("environment").contains("HLG")) {
            ambienteAPP = "HLG";
        } else {
            ambienteAPP = "PROD";
        }

        return ambienteAPP;
    }

}