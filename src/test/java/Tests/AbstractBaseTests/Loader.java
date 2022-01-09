package Tests.AbstractBaseTests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;



public abstract class Loader {
    private static String cpf;
    private static String nome;
    private static String email;
    private static String cep;
    private static String password;
    private static String phone;
    private static String codigoSms;
    private static String phoneOrigin;


    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Loader.nome = nome;
    }

    public static String getCpf() {
        return cpf;
    }

    public static void setCpf(String cpf) {
        Loader.cpf = cpf;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Loader.email = email;
    }

    public static String getCep() {
        return cep;
    }

    public static void setCep(String cep) {
        Loader.cep = cep;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Loader.password = password;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        Loader.phone = phone;
    }

    public static String getCodigoSms() {
        return codigoSms;
    }

    public static void setCodigoSms(String codigoSms) {
        Loader.codigoSms = codigoSms;
    }

    public static String getPhoneOrigin() {
        return phoneOrigin;
    }

    public static void setPhoneOrigin(String phoneOrigin) {
        Loader.phoneOrigin = phoneOrigin;
    }




    public static JSONObject getJsonDataObject(String jsonFileName, String dataGroup)
            throws org.json.simple.parser.ParseException {
        Object jsonFileObject;
        BufferedReader reader;
        // criando um parses para utilizamos no momento da leitura do arquivo json
        JSONParser parser = new JSONParser();

        // criando um objeto json que vai receber os dados do arquivo json
        JSONObject jsonDataObject = null;
        try {
            // inserindo o arquivo json em um objeto
            reader = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(TestBase.class.getResourceAsStream("/" + jsonFileName + ".json"))));


            jsonFileObject = parser.parse(reader);
            // convertendo o objeto com o arquivo json para o formato de objeto json
            jsonDataObject = (JSONObject) jsonFileObject;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // garantindo que o objeto json n?o est? nulo
        assert jsonDataObject != null;

        // retornando o objeto json, filtrando pelo grupo de dados desejado
        return (JSONObject) jsonDataObject.get(dataGroup);
    }

}
