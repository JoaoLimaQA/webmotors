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

package Tests.AbstractBaseTests;

import Hooks.AutomationUtils;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class TestBase extends AbstractTestNGCucumberTests {
    public static AppiumDriver<MobileElement> driver;
    private static Logger log = Logger.getLogger("QALogger");
    public static String ambiente;
    public static GetTags getTags;
    public static String versaoAPP = "Prod";


    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            setUpAppium();
        }
        return driver;
    }


   @BeforeSuite
    public static void setUpAppium() {

        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        ambiente = AutomationUtils.getEnvironment();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (driver == null) {
            try {
                getTags = new GetTags();
                if (Plataforma.OS.equals("ANDROID")) {

                    capabilities.setCapability("platformName", "Android");
                    capabilities.setCapability("deviceName", "emulator-5554");
                    capabilities.setCapability("autoGrantPermissions", true);
                    capabilities.setCapability("autoDismissAlerts", true);
                    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 50000);
                    //capabilities.setCapability("noReset", false);
                    //capabilities.setCapability("disableWindowAnimation", true);
                    //capabilities.setCapability("app", new File("aplicativos/webmotors.apk").getAbsolutePath());
                     capabilities.setCapability("appPackage", ambiente);
                    capabilities.setCapability("appActivity", "hands.android.webmotors.legacy.view.activity.SplashActivity");
                    driver = new AndroidDriver<>(new URL(URL_STRING), capabilities);
                    driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

                } else if (Plataforma.OS.equals("IOS")) {
                    capabilities.setCapability("platformName", "IOS");
                    capabilities.setCapability("platformVersion", "14.5");
                    capabilities.setCapability("deviceName", "iPhone 5s");
                    capabilities.setCapability("automationName", "XCUITest");
                    capabilities.setCapability("autoGrantPermissions", true);
                    capabilities.setCapability("autoAcceptAlerts", true);
                    capabilities.setCapability("fullReset", true);
                    capabilities.setCapability("noReset", true);
                    capabilities.setCapability("appPushTimeout", 60000);
                    //capabilities.setCapability("app", new File("aplicativos/webmotors.ipa").getAbsolutePath());
                    capabilities.setCapability("appPackage", ambiente);
                    capabilities.setCapability("appActivity", "hands.android.webmotors.legacy.view.activity.SplashActivity");
                    driver = new IOSDriver<>(new URL(URL_STRING), capabilities);
                    driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
                }
            } catch (MalformedURLException e) {
                log.info (" ==== AVISO : Por favor verifique a url que foi informada para executar os testes. ====");
                System.exit(1);
            }
        }
    }



    @AfterSuite
    public void tearDownAppium() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }




}