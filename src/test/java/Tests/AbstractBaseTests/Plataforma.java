package Tests.AbstractBaseTests;
import Hooks.AutomationUtils;

public class Plataforma {


  public static final String OS = platform();
  public static final String TOGGLE_VIDEO = "ligado" ;

  public static final String platform(){
    switch (AutomationUtils.getProperty("platform")){
      case "IOS":
        return "IOS";
      default:
        return "ANDROID";

    }
  }




}

