package Hooks;

import Tests.AbstractBaseTests.Plataforma;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.jsoup.helper.StringUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static Hooks.CEPGenerator.gerarCepAleatorio;
import static Tests.AbstractBaseTests.Loader.getJsonDataObject;


public class AutomationUtils {

    private static final String PHONE_NUMBER_KEY = "phoneNumber";
    private static final String POM_PROPERTIES_FILENAME = "properties-from-pom.properties";
    private static final String DEFAULT_ENVIRONMENT = "environment";
    private static final String DEFAULT_EMAIL = "email";
    private static final String DEFAULT_CPF = "cpf";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_SMS_CODE = "codigoSms";
    private static final String DEFAULT_POSTAL_CODE = "cep";


    public static String getRandomEmail(String cenario) throws ParseException {
        String emailGerado = "";

        if (!StringUtil.isBlank(getProperty(DEFAULT_EMAIL))) {
            emailGerado = getProperty(DEFAULT_EMAIL);
        } else {
            try {
                emailGerado = getJsonPorOSECenarioEVariavel(cenario, "email");
            } catch (NullPointerException ne) {
                System.out.println("Não há massa para esse cenário no JSON. Erro: " + ne.getMessage());
            }
            if (StringUtils.isBlank(emailGerado)) {
                Faker faker = new Faker();
                emailGerado = faker.internet().emailAddress();
            }
        }

        System.out.println("# email > " + emailGerado);
        return emailGerado;
    }

    public static String getCPF(String cenario)  {
           String cpfGerado = "";

           if (!StringUtil.isBlank(getProperty(DEFAULT_CPF))) {
               cpfGerado = getProperty(DEFAULT_CPF);
           } else {
               try {
                   cpfGerado = getJsonPorOSECenarioEVariavel(cenario, "cpf");
               } catch (NullPointerException ne) {
                   System.out.println("Não há massa para esse cenário no JSON. Erro: " + ne.getMessage());
               } catch (ParseException e) {
                   e.printStackTrace();
               }
               if (StringUtils.isBlank(cpfGerado)) {
                   CPFGenerator cpfGenerator = new CPFGenerator();
                   cpfGerado = cpfGenerator.geraCPFFinal();
               }
           }

           System.out.println("# cpf > " + cpfGerado);
           return cpfGerado;

    }

    public static String getPostalCode(String cenario) {
        String cepGerado = "";

        if (!StringUtil.isBlank(getProperty(DEFAULT_POSTAL_CODE))) {
            cepGerado = getProperty(DEFAULT_POSTAL_CODE);
        } else {
            try {
                cepGerado = getJsonPorOSECenarioEVariavel(cenario, "cep");
            } catch (NullPointerException ne) {
                System.out.println("Não há massa para esse cenário no JSON. Erro: " + ne.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (StringUtils.isBlank(cepGerado)) {
                return   gerarCepAleatorio();
            }
        }

        System.out.println("# cep > " + cepGerado);
        return cepGerado;
    }

    public static String getPassword(String cenario) throws ParseException {
        String senhaGerada = "";

        if (!StringUtil.isBlank(getProperty(DEFAULT_PASSWORD))) {
            senhaGerada = getProperty(DEFAULT_PASSWORD);
        } else {
            try {
                senhaGerada = getJsonPorOSECenarioEVariavel(cenario, "password");
            } catch (NullPointerException ne) {
                System.out.println("Não há massa para esse cenário no JSON. Erro: " + ne.getMessage());
            }
            if (StringUtils.isBlank(senhaGerada)) {
                return "senha(1)";
            }
        }

        System.out.println("# senha > " + senhaGerada);
        return senhaGerada;
    }

    public static String getSmsCode(String cenario) throws ParseException {
        String codigoSmsGerado = "";

        if (!StringUtil.isBlank(getProperty(DEFAULT_SMS_CODE))) {
            codigoSmsGerado = getProperty(DEFAULT_SMS_CODE);
        } else {
            try {
                codigoSmsGerado = getJsonPorOSECenarioEVariavel(cenario, "codigoSms");
            } catch (NullPointerException ne) {
                System.out.println("Não há massa para esse cenário no JSON. Erro: " + ne.getMessage());
            }
            if (StringUtils.isBlank(codigoSmsGerado)) {
                return "123654";
            }
        }

        System.out.println("# codigoSms > " + codigoSmsGerado);
        return codigoSmsGerado;
    }

    public static String getPhoneNumber(String cenario) throws ParseException {
        String telefoneGerado;

        if (StringUtils.isBlank(getProperty(PHONE_NUMBER_KEY))) {
            telefoneGerado = getJsonPorOSECenarioEVariavel(cenario, "phone");
        } else {
            telefoneGerado = getProperty(PHONE_NUMBER_KEY);
        }

        System.out.println("# telefone > " + telefoneGerado);
        return telefoneGerado;
    }

    public static String getTwoPhoneNumbersNet(String cenario, String variavelPhone) throws ParseException {
        String telefoneGerado;

        if (StringUtils.isBlank(getProperty(PHONE_NUMBER_KEY))) {
            telefoneGerado = getJsonPorOSECenarioEVariavel(cenario, variavelPhone);
        } else {
            telefoneGerado = getProperty(PHONE_NUMBER_KEY);
        }

        System.out.println("# telefone > " + variavelPhone + " > " + telefoneGerado);
        return telefoneGerado;

    }

    public static String getName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    public static String getAddressNumber() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }

    public static String getEnvironment() {

        switch (AutomationUtils.getProperty(DEFAULT_ENVIRONMENT)) {
            case "HLG":
                return "hands.android.webmotors.hlg";

            default:
                return "hands.android.webmotors";

        }
    }
    public static String getProperty(String key) {
        InputStream is = Plataforma.class.getClassLoader()
                .getResourceAsStream(POM_PROPERTIES_FILENAME);
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    private static String getJsonPorOSECenarioEVariavel(String cenario, String variavel) throws ParseException {
        if (Plataforma.OS.equals("ANDROID")) {
            return getJsonDataObject("DataBaseAndroid", cenario).get(variavel).toString();
        } else {
            return getJsonDataObject("DataBaseIOS", cenario).get(variavel).toString();
        }
    }

}