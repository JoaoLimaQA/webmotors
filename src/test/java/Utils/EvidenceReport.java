package Utils;


import Tests.AbstractBaseTests.Plataforma;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Tests.AbstractBaseTests.GetTags.*;
import static Tests.AbstractBaseTests.TestBase.getDriver;
import static Tests.AbstractBaseTests.TestBase.versaoAPP;
import static Utils.ConfigFileReader.*;

public class EvidenceReport {

    static WebDriver driver;
    public static String fileReport;
    private static String imagem;
    private static boolean ultimoPasso = false;
    public static String status = "PASSED";
    public static int passo ;
    public static String responsavel = "Equipe de Automação";
    public static String path = "Evidencias/relatorio/";
    public static String APLICATIVO = "APP WebMotors - ";
    private static String tempo = timeStamp();
    private static LocalDate date = LocalDate.now();
    private static String dataAtual = date.format(DateTimeFormatter.ofPattern("yyyMMdd"));
    public static String timeStamp() {
        return new SimpleDateFormat("yyyyMMddhh").format(new java.util.Date());
    }
    public static String timeStampEvd() { return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date()); }




    public static String capture(AppiumDriver driver) {
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot;
    }

    public static void report_failed() throws Exception {

        status = "FAILED";
        gerarRelatorio("WARNING", "Your test needs to be analyzed");

    }



    public static void gerarRelatorio( String titulo, String ACAO)  {
        imagem = capture(getDriver());
        passo += 1;

        File rootsPath = new File(path +"/"+nomeFrente()+ "/" + dataAtual);
        if (!rootsPath.exists())
            rootsPath.mkdirs();

        fileReport = rootsPath.toString() + "/" + cenarioEAmbiente() + ".html";

        try {
            if (passo <= 1) {
                PrintWriter writer = new PrintWriter(fileReport, "utf-8");
                writer.println("<html>");
                writer.println("<head> <link rel='shortcut icon' href='imagens/iconAndroid.png' />  <link rel='icon' href='imagens/iconAndroid.png' type='image/x-icon' />  <title>" + APLICATIVO+getAmbienteAPP()+ "</title>");
                writer.println("<meta http-equiv=\"Content-Type\"content=\"text/html;charset=utf-8\">");
                writer.println("<style>");
                writer.println(".evidencia{");
                writer.println("border-collapse:collapse;");
                writer.println("border: 1px solid black;");
                writer.println("}");
                writer.println(".Passed{");
                writer.println("color: #39ff14;");
                writer.println("background-color: #000000;");
                writer.println("}");
                writer.println(".Warning{");
                writer.println("color: #FFFF00;");
                writer.println("background-color: #000000;");
                writer.println("}");
                writer.println(".Failed{");
                writer.println("color:#FF0000;");
                writer.println("background-color: #000000;");
                writer.println("}");
                writer.println(".Error{");
                writer.println("color:#FFA500;");
                writer.println("}");
                writer.println("</style>");
                writer.println("</head>");
                writer.println("<body>");
                writer.println("<center><h1><font color='#003366'>Relatório de Execução do Teste:</font><center><br>");
                writer.println("<table>");
                writer.println("<tr>");
                writer.println("<td><img src='data:image/png;base64,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'/></td>");
                writer.println("<td>");
                writer.println("<center><h2>" +APLICATIVO+"<font color='#FA8072'>"+getAmbienteAPP()+"</font></h2><center><br>");
                writer.println("</td>");
                //TODO: imagem
                //  writer.println("<td><img src='data:image/png;base64,'/></td>");
                writer.println("<td><img src='data:image/png;base64,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'/></td>");
                writer.println("</tr>");
                writer.println("</table>");
                writer.println("</p>");
                writer.println("<center><h5>Cenário: <font color='#00AA00'>" +objetivoCenario()+ "</font><center><br>"); //Nova Linha

                // INICIO BLOCO CUSTOMIZAÇÃO PARA AMBIENTE MOBILE(06/02/2020)
                writer.println("<table border=5 colspan=5 >");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=4 ><b><center>AMBIENTE</center></b></td></tr><tr>");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=3  > PLATAFORMA: </td><td class='evidencia' style='width:370px' >" + sPlataforma() + "</td></tr><tr>");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=3 > MARCA: </td><td class='evidencia' >" + sMarca() + "</td></tr><tr>");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=3 > MODELO: </td><td class='evidencia' >" + sModelo() + "</td></tr><tr>");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=3 > VERSÃO "+Plataforma.OS+": </td><td class='evidencia' >" + sVersionDevice() + "</td></tr><tr>");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=3 > VERSÃO APLICATIVO: </td>");
                writer.println("<td class='evidencia' colspan=3 >" +versaoAPP+ "</td></tr><tr></table></p>");
                // FIM BLOCO CUSTOMIZAÇÃO PARA AMBIENTE MOBILE


                writer.println("<table class='evidencia' >");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=4 ><b><center>" + titulo + "</center></b></td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td class='evidencia' > PASSO: </td><td class='evidencia' >" + passo + "</td>");
                writer.println("<td class='evidencia' > DATA/HORA EXECUÇÃO: </td><td class='evidencia' >"
                        + timeStampEvd() + "</td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td class='evidencia' > AÇÃO: </td><td class='evidencia' colspan=3 >"
                        + ACAO + "</td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td class='evidencia' > STATUS: </td><td class='evidencia' ><b class='" + status + "' >"
                        + status + "</b></td>");
                writer.println(
                        "<td class='evidencia' > Responsável: </td><td class='evidencia' >" + responsavel + "</td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td colspan=4 class='evidencia' ><center>EVIDÊNCIA</center></td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td colspan=4 class='evidencia' ><img src='" + imagem
                        + "' style='width:530px;height:690px;' /></td>");
                writer.println("</tr>");
                writer.println("</table>");
                writer.println("</p>");
                writer.close();
            }else {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileReport, true)));
                writer.println("</p>");
                writer.println("<table class='evidencia' >");
                writer.println("<tr class='evidencia'>");
                writer.println("<td class='evidencia' colspan=4 > <center><b class='" + titulo + "' >"
                        + titulo + "</center></b></td>");
                // writer.println("<td class='evidencia' colspan=4 ><b><center>" + titulo + "</center></b></td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td class='evidencia' > PASSO: </td><td class='evidencia' >" + passo + "</td>");

                writer.println("<td class='evidencia' > DATA/HORA EXECUÇÃO: </td><td class='evidencia' >"
                        + timeStampEvd() + "</td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td class='evidencia' > AÇÃO: </td><td class='evidencia' colspan=3 >"
                        + ACAO + "</td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td class='evidencia' > STATUS: </td><td class='evidencia' ><b class='" + status + "' >"
                        + status + "</b></td>");
                writer.println(
                        "<td class='evidencia' > Responsável: </td><td class='evidencia' >" + responsavel + "</td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td colspan=4 class='evidencia' ><center>EVIDÊNCIA</center></td>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td colspan=4 class='evidencia' ><img src='" + imagem
                        + "' style='width:540px;height:680px;' /></td>");
                writer.println("</tr>");
                writer.println("</table>");
                writer.println("</p>");
                writer.close();
            }
            if (ultimoPasso == true) {
                PrintWriter writer = new PrintWriter(
                        new BufferedWriter(new FileWriter(fileReport, true)));
                writer.println("</BODY>");
                writer.println("</HTML>");
                writer.close();
                tempo = timeStamp();
            }
        } catch (Exception ex) {
            ex.printStackTrace();


        }

        status = "PASSED";
    }

}
