package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=json&units=metric&cnt=7&appid=9d88ea129b65c04b75a6b62783fc73bb

public class ConnectAPI {
/*
    public static void main(String[] args){
        mainTemps();
    }
*/
    public static void mainTemps(){
        String JsonTemps = "";
        //String JsonActors = "";
        String api_key = "9d88ea129b65c04b75a6b62783fc73bb";
        String city = "Barcelona";//Modificar
        String units = "metric";//Modificar
        String dies = "7";
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + city + "&mode=json&units=" + units + "&cnt=" + dies + "&appid=9d88ea129b65c04b75a6b62783fc73bb";

        System.out.println("TEMPS:");//http://api.openweathermap.org/data/2.5/forecast/daily?q=Cuenca&mode=json&units=metric&cnt=7&appid=9d88ea129b65c04b75a6b62783fc73bb

        try {
            JsonTemps = getHTML(url);//peticioActors   peticioPeli

            escriuTempsCiutat(JsonTemps);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("...error de conexion a API");
        }

        //SJS(url);

        /*
        for (int i = 0; i < 10; i++) {//http://openweathermap.org/city/3128760
            int peliId = 620 + i;
            String film = String.valueOf(peliId);
            String peticioTempsBCN = "http://openweathermap.org/city/3128760";
            //String peticioActors = "https://api.themoviedb.org/3/movie/"+film+"/credits?api_key="+api_key;
            //String peticioPeli = "https://api.themoviedb.org/3/movie/"+film+"?api_key="+api_key;

            try {
                JsonPelis = getHTML(peticioPeli);//peticioActors   peticioPeli
                //System.out.println("PELICULAS:");
                //System.out.println(JsonPelis);
                SJS(JsonPelis);

                JsonActors = getHTML(peticioActors);
                System.out.println("\tActors:");
                //System.out.println("\t" + JsonActors);
                SJCpersonaje(JsonActors);

            } catch (Exception e) {
                System.out.println("La peli "+film+" no existeix");
            }
        }*/
    }

    /**
     * Retorna el JSON del temps
     * @param urlToRead
     * @return
     * @throws Exception
     */
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();//retorna un JSON
    }

    /**
     * Escriu el temps d'una ciutat
     * @param cadena
     */
    public static void escriuTempsCiutat (String cadena){//para tiempo
        Calendar fecha = new GregorianCalendar();
        String dia, mes, annio;
        dia = Integer.toString(fecha.get(Calendar.DATE));
        mes = Integer.toString(fecha.get(Calendar.MONTH));
        annio = Integer.toString(fecha.get(Calendar.YEAR));

        JSONObject arra02 = (JSONObject) JSONValue.parse(cadena);

        String out = arra02.toJSONString();

        System.out.println(out);
        System.out.println();

        JSONArray test = (JSONArray) arra02.get("list");
        //System.out.println(test.size());

        //json parser
        JSONObject ciudad = (JSONObject) arra02.get("city");
        System.out.println("Ciudad: " + ciudad.get("name"));
        String cod = (String) arra02.get("cod");
        System.out.println("Codigo ciudad: " + cod);
        String pais = (String) ciudad.get("country");
        System.out.println("Pais: " + pais);
        System.out.println("Prevision meteorologica de " + test.size() + " dias\n");
        System.out.println("\t-----TIEMPO-----");

        for (int i = 0; i < test.size(); i++) {
            //System.out.println(dia + " ");
            JSONObject weatherJsonObject = (JSONObject) test.get(i);

            JSONObject temp = (JSONObject) weatherJsonObject.get("temp");
            String min = temp.get("min").toString();
            String max = temp.get("max").toString();
            System.out.println("\nTemperatura minima: " + min);
            System.out.println("Temperatura maxima: " + max);

        }

    }

    /**
     * Escriu tots els personatges
     * @param cadena
     */
    public static void SJCpersonaje (String cadena){//para personajes
        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        JSONArray arra03 = (JSONArray)arra02.get("cast");

        for (int i = 0; i < arra03.size(); i++) {
            JSONObject jb= (JSONObject)arra03.get(i);
            System.out.println("\t" + (i+1) + " - " + jb.get("character")+"<-->"+jb.get("name"));

        }

    }

}
/*


#4
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