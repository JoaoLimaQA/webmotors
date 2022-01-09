package Utils;

import Tests.AbstractBaseTests.Plataforma;
import com.cucumber.listener.Reporter;
import java.io.File;
import java.util.logging.Logger;

import static Pages.BasePage.manageTimeoutImplicitlyWait;
import static Tests.AbstractBaseTests.TestBase.getDriver;
import static Tests.AbstractBaseTests.TestBase.versaoAPP;


public class ConfigFileReader {
    private static Logger log = Logger.getLogger("QALogger");
    public static String REPORT_CONFIG_PATH = System.getProperty("user.dir")+"/src/test/resources/extend-config.xml";
    public static String SCREENSHOT_DIR = System.getProperty("user.dir")+"/Evidencias/dashboard/cucumber-reports/screenshots/";


    public static boolean runLocal (){
      String user =  System.getProperty("user.name");
        if (user.contains("farm")) {
            return false;
        }else {
            return true;
        }
    }




    public static void dashboardConfig(){
        try {
            manageTimeoutImplicitlyWait(1);
            Reporter.loadXMLConfig(new File(REPORT_CONFIG_PATH));
            Reporter.assignAuthor("Equipe - Software_Quality_Engineer");
            Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
            Reporter.setSystemInfo("Aplicativo", "Webmotors");
            Reporter.setSystemInfo("Versao Aplicativo", versaoAPP);
            Reporter.setSystemInfo("Marca", sMarca());
            Reporter.setSystemInfo("Modelo", sModelo());
            Reporter.setSystemInfo("Plataforma", sPlataforma());
            Reporter.setSystemInfo("Versao "+ Plataforma.OS.toLowerCase() +"", sVersionDevice());

        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }
    public static String sMarca() {
        return getDriver().getCapabilities().getCapability("deviceManufacturer").toString();
    }

    public static String sVersionDevice() {
        return getDriver().getCapabilities().getCapability("platformVersion").toString();
    }

    public static String sModelo() {
        return getDriver().getCapabilities().getCapability("deviceModel").toString();
    }

    public static String sPlataforma() {
        return getDriver().getCapabilities().getCapability("platformName").toString();
    }



}

