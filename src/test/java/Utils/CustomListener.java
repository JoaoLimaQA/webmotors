package Utils;


import Tests.AbstractBaseTests.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.Logger;



public class CustomListener extends TestBase implements ITestListener {

    private static Logger log = Logger.getLogger("QALogger");
    public static int totalFail ;
    public static int totalPassed ;
    public static String msg ="Ola Pessoal, Tudo bem? Segue o anexo do Dashboard referente automacao dos testes,";



    @Override
    public void onTestStart(ITestResult result) {


    }

    @Override
    public void onTestSuccess(ITestResult result) {
/*        if (result.isSuccess()) {
            try {
                saveVideo();
            } catch (IOException e) {
                e.printStackTrace();
            }
            getDriver().resetApp();
            log.info(Loader.getLogCompleto());
        }*/
    }

    @Override
    public void onTestFailure(ITestResult result) {

       /* if (!result.isSuccess()){
            try {
                TimeUnit.MINUTES.sleep(1);
                screenshot_devicefarm(nomeCenario());
                add_screenshot_cucumber_report(nomeCenario());
                //add evidencia da falha no relatorio
                report_failed();
                saveVideo();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getDriver().resetApp();
        log.info(Loader.getLogCompleto());*/
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {




    }

    @Override
    public void onFinish(ITestContext context) {

        try {
            totalFail = context.getFailedTests().size();
            totalPassed = context.getPassedTests().size();
          //mover pasta do report para o diretorio do aws
            //mover_report_dir_devicefarm();
            //TODO: Envio de email ficara em standby
/*      EmailAttachment anexosMetrics = new EmailAttachment();
        Date endDate = context.getEndDate();
        if (endDate!=null){

            anexosMetrics = new EmailAttachment();
            anexosMetrics.setPath(reportPath);
            anexosMetrics.setName("Dashboard.html");
            email.addPart( "<b>"+msg+"<br>Abaixo o resumo da Execucao <br><br><u>Resumo<br><font color='green'>PASSED </font>"+totalPassed+"<br><font color='red'>FAILED </font> "+totalFail+"</b></u>", "text/html; charset=UTF-8" );
            email.attach(anexosMetrics);
            email.send();
            log.info("E-mail enviado com sucesso");
            }
            */
        }catch (Exception e){

        }

    }


    public String getName() {
        return null;
    }
}

