package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;

//http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=json&units=metric&cnt=7&appid=9d88ea129b65c04b75a6b62783fc73bb

public class ConnectAPI {

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

    public static void main(String[] args){
        String JsonTemps = "";
        //String JsonActors = "";
        String api_key = "9d88ea129b65c04b75a6b62783fc73bb";
        String city = "Cuenca";//Modificar
        String units = "metric";//Modificar
        String dies = "7";
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + city + "&mode=json&" + units + "=metric&cnt=" + dies + "&appid=9d88ea129b65c04b75a6b62783fc73bb";

        System.out.println("TEMPS:");//http://api.openweathermap.org/data/2.5/forecast/daily?q=Cuenca&mode=json&units=metric&cnt=7&appid=9d88ea129b65c04b75a6b62783fc73bb

        try {
            JsonTemps = getHTML(url);//peticioActors   peticioPeli

            SJS(JsonTemps);

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
     * Escriu el temps d'una ciutat
     * @param cadena
     */
    public static void SJS (String cadena){//para tiempo
        JSONObject obj02 = (JSONObject) JSONValue.parse(cadena);


        JSONObject arra02 = obj02;
        JSONObject arra03 = obj02;

        String out = arra02.toJSONString();

        System.out.println(out);
        System.out.println();
        //System.out.println(arra02.get("city"));

        //ciudad json
        JSONObject ciudad = (JSONObject) arra02.get("city");
        System.out.println(ciudad.get("name"));
        String cod = (String) arra02.get("cod");
        System.out.println(cod);

        //cod json
        //JSONObject cod = (JSONObject) arra03.get("cod");
        System.out.println(arra02.get("cod"));

        //dia json
        JSONArray test = (JSONArray) arra02.get("list");
        System.out.println(test.size());

        for (int i = 0; i < test.size(); i++) {
            //JSONArray arrayMin = (JSONArray) test.get();
            //Object obj = test.get(i);
            //JSONObject jobj = (JSONObject)obj;
            JSONObject weatherJsonObject = (JSONObject) test.get(i);
            Double rain = (Double) weatherJsonObject.get("rain");
            System.out.println(rain);

            /*for(int j = 0; j < arrayMin.size(); j++){
                //JSONObject min = (JSONObject) arrayMin.get(j);
                //System.out.println(min.get("min"));
                System.out.println(arrayMin.size());
            }*/

            //System.out.println(test.get(i));
        }
        //System.out.println(dias.get("temp"));

        //System.out.println("\nTitol: " + arra02.   arra02.get("name"));
        //System.out.println("Data de publicació: " + arra02.get("release_date"));

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

{
   "city":{
      "id":3658666,
      "name":"Cuenca",
      "coord":{
         "lon":-78.98333,
         "lat":-2.88333
      },
      "country":"EC",
      "population":0
   },
   "cod":"200",
   "message":0.0079,
   "cnt":7,
   "list":[
      0{
         "dt":1447347600,
         "temp":{
            "day":25.06,
            "min":15.4,
            "max":26.39,
            "night":15.4,
            "eve":19.64,
            "morn":20.12
         },
         "pressure":802.02,
         "humidity":74,
         "weather":[
            {
               "id":501,
               "main":"Rain",
               "description":"moderate rain",
               "icon":"10d"
            }
         ],
         "speed":1.51,
         "deg":95,
         "clouds":64,
         "rain":3.13
      },
      1 {
         "dt":1447434000,
         "temp":{
            "day":22.24,
            "min":13.07,
            "max":23.67,
            "night":13.07,
            "eve":17.79,
            "morn":16.61
         },
         "pressure":801.77,
         "humidity":76,
         "weather":[
            {
               "id":500,
               "main":"Rain",
               "description":"light rain",
               "icon":"10d"
            }
         ],
         "speed":1.55,
         "deg":84,
         "clouds":80,
         "rain":1.32
      },
      {
         "dt":1447520400,
         "temp":{
            "day":20.39,
            "min":9.47,
            "max":20.39,
            "night":9.47,
            "eve":15.13,
            "morn":14.07
         },
         "pressure":802.58,
         "humidity":73,
         "weather":[
            {
               "id":500,
               "main":"Rain",
               "description":"light rain",
               "icon":"10d"
            }
         ],
         "speed":1.51,
         "deg":94,
         "clouds":36,
         "rain":1.48
      },
      {
         "dt":1447606800,
         "temp":{
            "day":20.72,
            "min":11.5,
            "max":20.73,
            "night":11.5,
            "eve":15.58,
            "morn":13.75
         },
         "pressure":802.17,
         "humidity":68,
         "weather":[
            {
               "id":500,
               "main":"Rain",
               "description":"light rain",
               "icon":"10d"
            }
         ],
         "speed":1.92,
         "deg":95,
         "clouds":48,
         "rain":2.78
      },
      {
         "dt":1447693200,
         "temp":{
            "day":20.3,
            "min":12.36,
            "max":20.3,
            "night":12.36,
            "eve":14.89,
            "morn":13.35
         },
         "pressure":802.08,
         "humidity":0,
         "weather":[
            {
               "id":501,
               "main":"Rain",
               "description":"moderate rain",
               "icon":"10d"
            }
         ],
         "speed":0.85,
         "deg":148,
         "clouds":34,
         "rain":5.66
      },
      {
         "dt":1447779600,
         "temp":{
            "day":19.15,
            "min":12.57,
            "max":19.15,
            "night":12.57,
            "eve":14.21,
            "morn":13.2
         },
         "pressure":801.75,
         "humidity":0,
         "weather":[
            {
               "id":501,
               "main":"Rain",
               "description":"moderate rain",
               "icon":"10d"
            }
         ],
         "speed":0.37,
         "deg":180,
         "clouds":32,
         "rain":9.77
      },
      {
         "dt":1447866000,
         "temp":{
            "day":18.42,
            "min":12.66,
            "max":18.42,
            "night":13.33,
            "eve":14.53,
            "morn":12.66
         },
         "pressure":801.63,
         "humidity":0,
         "weather":[
            {
               "id":501,
               "main":"Rain",
               "description":"moderate rain",
               "icon":"10d"
            }
         ],
         "speed":0.57,
         "deg":173,
         "clouds":24,
         "rain":11.53
      }
   ]
}

*/