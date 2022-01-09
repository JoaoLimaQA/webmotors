/*
 * Copyright 2014-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package Pages;

import Tests.AbstractBaseTests.Plataforma;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static Tests.AbstractBaseTests.GetTags.cenarioEAmbiente;
import static Tests.AbstractBaseTests.Plataforma.TOGGLE_VIDEO;
import static Tests.AbstractBaseTests.TestBase.getDriver;
import static Utils.ConfigFileReader.SCREENSHOT_DIR;
import static Utils.ConfigFileReader.runLocal;


public abstract class BasePage {
    private static Logger log = Logger.getLogger("QALogger");
    private static ByteArrayInputStream byteArrayInputStream = null;
    private static String imagem = null;
    private static String video;
    protected final AppiumDriver driver;

    protected BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }




    public void esperarLoadWebmotors() {
        while (verificarSeLoadEstaVisivelNaTela() == true) {
            esperarAnimacao(1);
        }
        manageTimeoutImplicitlyWait(35);
    }

    public boolean verificarSeLoadEstaVisivelNaTela() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 1);
        try {
            manageTimeoutImplicitlyWait(1);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("android.widget.ProgressBar")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verificarSeTextoEstaVisivelNaTela(String text ) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 1);
        try {
            manageTimeoutImplicitlyWait(1);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@text,'" +text+ "')]")));
            manageTimeoutImplicitlyWait(35);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void esperarTextSumir(String text) {
        while (verificarSeTextoEstaVisivelNaTela(text) == true) {
            esperarAnimacao(1);
        }
        manageTimeoutImplicitlyWait(35);
    }


    public void clickText(String text) {
        getDriver().findElementByXPath("//*[@text='"+text+"']").click();

    }






    public static void manageTimeoutImplicitlyWait(int timeOut) {
        getDriver().manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }




    public void esperarElementoVisivel(String Bynome, int time) {
        manageTimeoutImplicitlyWait(time);
        WebDriverWait wait = new WebDriverWait(getDriver(), time);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@text='" + Bynome + "']")));
        manageTimeoutImplicitlyWait(35);
    }

    public static void esperarTextVisivel(String contains, int time) {
       try {
           manageTimeoutImplicitlyWait(time);
           WebDriverWait wait = new WebDriverWait(getDriver(), time);
           wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@text,'" + contains + "')]")));

       }catch (Exception e){
           log.info("=====> Atenção: Texto '"+contains+"' não apareceu");

       }
        manageTimeoutImplicitlyWait(35);
    }

    public void esperarAnimacao(int Seconds) {

        try {
            TimeUnit.SECONDS.sleep(Seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void hideKeyboard() {
        try {
            manageTimeoutImplicitlyWait(1);
            driver.hideKeyboard();
        } catch (Exception e) {
            log.info("Não foi possível executar o comando driver.hideKeyboard(). Erro: " + e.getMessage());
        }
        manageTimeoutImplicitlyWait(35);
    }

////////////////////////////////////////////////////////////video and imagem aws/////////////////////////////////////////////////////////////////
    public static void add_screenshot_cucumber_report(final String cenario) throws IOException {

        //tirar screenshot
        imagem = cenario + ".png";
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("Evidencias/dashboard/cucumber-reports/screenshots/" + imagem));
        byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(screenshot));
        //Add ao cucumber report
        File destinationPath = new File(SCREENSHOT_DIR+ imagem);
        com.cucumber.listener.Reporter.addScreenCaptureFromPath(destinationPath.toString(), cenario);
    }




    public static void saveVideo() throws IOException {
        if (Plataforma.OS.contains("ANDROID")&&TOGGLE_VIDEO.equals("ligado")) {
            //Evidencia em video
            video = ((AndroidDriver) getDriver()).stopRecordingScreen();
            byte[] decode = Base64.getDecoder().decode(video);
            FileUtils.writeByteArrayToFile(new File("Evidencias/video/" + cenarioEAmbiente() + ".mp4"), decode);
        }else if (Plataforma.OS.contains("IOS")&&TOGGLE_VIDEO.equals("ligado")) {
          //  video = ((IOSDriver) getDriver()).stopRecordingScreen();
            byte[] decode = Base64.getDecoder().decode(video);
            FileUtils.writeByteArrayToFile(new File("Evidencias/video/" + cenarioEAmbiente() + ".mp4"), decode);
        }
    }


    public static void startVideo() {
        if (Plataforma.OS.contains("ANDROID")&&TOGGLE_VIDEO.equals("ligado")) {
            //   ((AndroidDriver) getDriver()).startRecordingScreen(new AndroidStartScreenRecordingOptions().enableForcedRestart().withTimeLimit(Duration.ofSeconds(180)));
            AndroidStartScreenRecordingOptions options = new AndroidStartScreenRecordingOptions();
            options.enableForcedRestart();
            if(runLocal()) {
                options.withTimeLimit(Duration.ofSeconds(1800));
            }else {
                options.withTimeLimit(Duration.ofSeconds(180));
            }
            ((CanRecordScreen) getDriver()).startRecordingScreen(options);
            log.info("inicio o video");


        }
        else if(Plataforma.OS.contains("IOS")&&TOGGLE_VIDEO.equals("ligado")) {
            IOSStartScreenRecordingOptions options = new IOSStartScreenRecordingOptions();
            options.withVideoType("mpeg4");
            options.withTimeLimit(Duration.ofSeconds(180));
            ((CanRecordScreen) getDriver()).startRecordingScreen(options);
        }


    }

    /////////////////////////////////////////////////////////////////Scroll´s and swipe´s///////////////////////////////////////////////////////////////////////////////
    public void scrollDown(String text) {
        if (Plataforma.OS.equals("ANDROID")) {
            try {
                manageTimeoutImplicitlyWait(1);
                ((AndroidDriver) getDriver()).findElementByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()" +
                                ".scrollIntoView(new UiSelector().text(\"" + text + "\"))");
                getDriver().findElement(By.xpath("//*[@text='" + text + "']")).click();
            } catch (Exception e) {

            }
        }
        manageTimeoutImplicitlyWait(35);
    }

    public void scrollDownHorizontal(String text) {
        if (Plataforma.OS.equals("ANDROID")) {
            try {
                manageTimeoutImplicitlyWait(1);
            MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList()" +
                            ".scrollIntoView(new UiSelector().text(\""+text+"\"))"));
                getDriver().findElement(By.xpath("//*[@text='" + text + "']")).click();
            } catch (Exception e) {

            }
        }
        manageTimeoutImplicitlyWait(35);
    }


    public void scrollVerticalId(String id) {
        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceIdMatches(\".*"+id+".*\"))"));


    }

    public void scrollVerticalText(String text) {
        MobileElement element = (MobileElement) getDriver().findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\""+text+"\"))"));



    }

    public void swipeVertical(String valor) {

        @SuppressWarnings("unused")
        MobileElement element = getDriver().findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + valor + "\"));"));
    }

    public void swipehorizontal(String valor) {
        MobileElement element = getDriver()
                .findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollIntoView("
                        + "new UiSelector().textContains(\"" + valor + "\"))"));
        //element.click();
    }

   public void clicarNoCentroDaTela() {
       Dimension size = getDriver().manage().window().getSize();
       int x = size.width / 2;
       int y = size.height / 2;
       TouchAction touchAction=new TouchAction(getDriver());
       touchAction.tap(PointOption.point(x, y)).perform();
    }



}
