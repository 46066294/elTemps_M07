package sample;

import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;
import java.util.*;

import static java.lang.Math.round;

//http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=json&units=metric&cnt=7&appid=9d88ea129b65c04b75a6b62783fc73bb

/**MODEL
 * Connexió a la API openweathermap
 * amb les seguents dades:
 *  -ciutat
 *  -dies
 *  -(la resta d'atributs nomes les utilitza el programa)
 */
public class ConnectAPI {

    private String city = "Barcelona";
    private String units = "metric";//Modificar
    private String dies = "7";
    protected int intDies = Integer.parseInt(dies);
    private String cod = null;
    private String pais = null;
    private String min = null;
    private String max = null;
    protected String[] vectorTemperaturas;// = new String[Integer.parseInt(dies)];
    List<Double> mediaTemperaturasMinimas;
    List<Double> mediaTemperaturasMaximas;
    private double mediaTempMinima;
    private double mediaTempMaxima;

    Date fecha = new Date();
    int diaHoy = getDayOfTheWeek(fecha);

    public ConnectAPI(){
        //city = "Barcelona";
        //dies = "16";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("El Temps Desktop");
        alert.setHeaderText("Aquesta aplicació et mostrarà la informació\n" +
                "del temps a la ciutat que vulguis fins a 16 dies vista");
        alert.setContentText("Let's go!");

        alert.showAndWait();

        mainTemps();
    }

    /**
     * Procediment principal per a conectar-se
     * a la API
     */
    public void mainTemps(){

        String JsonTemps = "";
        //String JsonActors = "";
        String api_key = "9d88ea129b65c04b75a6b62783fc73bb";
        //city = "Barcelona";//Modificar
        //String units = "metric";//Modificar
        //String dies = "7";
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + city + "&mode=json&units=" + units + "&cnt=" + dies + "&appid=9d88ea129b65c04b75a6b62783fc73bb";

        System.out.println("TEMPS:");//http://api.openweathermap.org/data/2.5/forecast/daily?q=Cuenca&mode=json&units=metric&cnt=7&appid=9d88ea129b65c04b75a6b62783fc73bb

        try {
            JsonTemps = getHTML(url);//peticioActors   peticioPeli

            escriuTempsCiutat(JsonTemps);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("...error de conexion a API");
        }

    }

    /**
     * Retorna el JSON del temps
     * @param urlToRead
     * @return
     * @throws Exception
     */
    public String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {//IMPORTANT
            result.append(line);
        }
        rd.close();
        return result.toString();//retorna un JSON
    }

    /**
     * Escriu el temps d'una ciutat a partir d'un JSON
     * @param cadena
     */
    public void escriuTempsCiutat (String cadena){//para tiempo

        vectorTemperaturas = new String[Integer.parseInt(this.getDies())];

        mediaTemperaturasMinimas = new ArrayList<Double>();
        mediaTemperaturasMaximas = new ArrayList<Double>();

        JSONObject arra02 = (JSONObject) JSONValue.parse(cadena);

        String out = arra02.toJSONString();

        System.out.println(out);
        System.out.println();

        JSONArray test = (JSONArray) arra02.get("list");
        //System.out.println(test.size());

        //json parser
        JSONObject ciudad = (JSONObject) arra02.get("city");
        System.out.println("Ciudad: " + ciudad.get("name"));
        cod = (String) arra02.get("cod");
        System.out.println("Codigo ciudad: " + cod);
        pais = (String) ciudad.get("country");
        System.out.println("Pais: " + pais);
        System.out.println("Prevision meteorologica de " + test.size() + " dias\n");
        System.out.println("\t-----TIEMPO-----");

        //muestra info
        for (int i = 0; i < test.size(); i++) {
            JSONObject weatherJsonObject = (JSONObject) test.get(i);

            JSONObject temp = (JSONObject) weatherJsonObject.get("temp");
            min = temp.get("min").toString();
            max = temp.get("max").toString();

            System.out.println("\nTemperatura minima: " + min);
            System.out.println("Temperatura maxima: " + max);
            mediaTemperaturasMinimas.add(Double.valueOf(min));
            mediaTemperaturasMaximas.add(Double.valueOf(max));
            vectorTemperaturas[i] = metodoDia() + "\nminima: " + min + " grados C " + "maxima: " + max + " graus C";
            diaHoy++;

            if(diaHoy == 8)diaHoy = 1;

            System.out.println(vectorTemperaturas[i]);
        }

        double tempMinima = 0;
        double tempMaxima = 0;
        for(int i = 0; i < mediaTemperaturasMinimas.size(); i++){
            tempMinima = tempMinima + mediaTemperaturasMinimas.get(i);
            tempMaxima = tempMaxima + mediaTemperaturasMaximas.get(i);
        }
        mediaTempMinima = tempMinima / mediaTemperaturasMinimas.size();
        mediaTempMaxima = tempMinima / mediaTemperaturasMaximas.size();

        diaHoy = getDayOfTheWeek(fecha);
    }

    /**
     * Funcio per a saber el nom del dia
     * @return String amb el nom del dia
     */
    private String metodoDia() {

        if(diaHoy == 1){
            return "DOMINGO:";
        }
        else if(diaHoy == 2){
            return "LUNES:";
        }
        else if(diaHoy == 3){
            return "MARTES:";
        }
        else if(diaHoy == 4){
            return "MIERCOLES:";
        }
        else if(diaHoy == 5){
            return "JUEVES:";
        }
        else if(diaHoy == 6){
            return "VIERNES:";
        }
        else if(diaHoy == 7){
            return "SABADO:";
        }

        return null;
    }

    /**
     * Neteja el ListView que mostra la informació
     * per a rebre noves dades
     */
    public void limpiaListView(){
        //vectorTemperaturas = new String[Integer.parseInt(dies)];
        vectorTemperaturas = null;
    }

    /**
     * Funció que retorna un enter per a saber el
     * dia en el que estem:
     * 1=diumenge
     * 2=dilluns
     * ...
     * @param d Date
     * @return enter que simbolitza el dia de la setmana
     */
    public static int getDayOfTheWeek(Date d){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    //getters
    public String getMediaTempMinima() {
        String minima = String.valueOf(round(mediaTempMinima));
        return minima;
    }

    public String getMediaTempMaxima() {
        String maxima = String.valueOf(round(mediaTempMaxima));
        return maxima;
    }

    public String getCity() {
        return city;
    }

    public String getUnits() {
        return units;
    }

    public String getDies() {
        return dies;
    }

    public String getCod() {
        return cod;
    }

    public String getPais() {
        return pais;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public String[] getVectorTemperaturas() {
        return vectorTemperaturas;
    }

    //setters
    public void setCity(String text) {
        this.city = text;
    }

    public void setDies(String dies) {
        this.dies = dies;
    }

}
/*

EXEMPLE DE JSON

November 13th 2015, 4:37:45 pm
Valid JSON (RFC 4627)
Formatted JSON Data


{
   "city":{
      "id":3128760,
      "name":"Barcelona",
      "coord":{
         "lon":2.15899,
         "lat":41.38879
      },
      "country":"ES",
      "population":0
   },
   "cod":"200",
   "message":0.0082,
   "cnt":7,
   "list":[
      {
         "dt":1447412400,
         "temp":{
            "day":16.59,
            "min":14.71,
            "max":16.85,
            "night":14.71,
            "eve":15.46,
            "morn":16.59
         },
         "pressure":1032.43,
         "humidity":100,
         "weather":[
            {
               "id":802,
               "main":"Clouds",
               "description":"scattered clouds",
               "icon":"03d"
            }
         ],
         "speed":1.11,
         "deg":76,
         "clouds":36
      },
      {
         "dt":1447498800,
         "temp":{
            "day":18.04,
            "min":12.9,
            "max":18.18,
            "night":14.46,
            "eve":16.5,
            "morn":12.9
         },
         "pressure":1033.33,
         "humidity":92,
         "weather":[
            {
               "id":800,
               "main":"Clear",
               "description":"sky is clear",
               "icon":"01d"
            }
         ],
         "speed":1.94,
         "deg":264,
         "clouds":0
      },
      {
         "dt":1447585200,
         "temp":{
            "day":17.2,
            "min":12.8,
            "max":18.16,
            "night":15.08,
            "eve":16.19,
            "morn":12.8
         },
         "pressure":1027.02,
         "humidity":96,
         "weather":[
            {
               "id":800,
               "main":"Clear",
               "description":"sky is clear",
               "icon":"01d"
            }
         ],
         "speed":2.66,
         "deg":249,
         "clouds":0
      },
      {
         "dt":1447671600,
         "temp":{
            "day":19.33,
            "min":14.03,
            "max":19.41,
            "night":15.17,
            "eve":16.71,
            "morn":14.03
         },
         "pressure":1024.86,
         "humidity":83,
         "weather":[
            {
               "id":800,
               "main":"Clear",
               "description":"sky is clear",
               "icon":"01d"
            }
         ],
         "speed":0.81,
         "deg":318,
         "clouds":0
      },
      {
         "dt":1447758000,
         "temp":{
            "day":18.67,
            "min":12.91,
            "max":18.88,
            "night":14.72,
            "eve":16.06,
            "morn":12.91
         },
         "pressure":1025.85,
         "humidity":86,
         "weather":[
            {
               "id":800,
               "main":"Clear",
               "description":"sky is clear",
               "icon":"01d"
            }
         ],
         "speed":1.26,
         "deg":315,
         "clouds":0
      },
      {
         "dt":1447844400,
         "temp":{
            "day":17.87,
            "min":13.95,
            "max":17.87,
            "night":14.83,
            "eve":16.12,
            "morn":13.95
         },
         "pressure":1023.94,
         "humidity":0,
         "weather":[
            {
               "id":800,
               "main":"Clear",
               "description":"sky is clear",
               "icon":"01d"
            }
         ],
         "speed":3.07,
         "deg":254,
         "clouds":0
      },
      {
         "dt":1447930800,
         "temp":{
            "day":16.93,
            "min":13.31,
            "max":16.93,
            "night":14.25,
            "eve":15.85,
            "morn":13.31
         },
         "pressure":1024.37,
         "humidity":0,
         "weather":[
            {
               "id":800,
               "main":"Clear",
               "description":"sky is clear",
               "icon":"01d"
            }
         ],
         "speed":2.69,
         "deg":277,
         "clouds":0
      }
   ]
}

*/