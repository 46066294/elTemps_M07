package sample;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Parsea documento forecast.xml
 * Tratamiento de datos segun enunciado
 */
public class ParseXml {
    public static void main(String[] args) {//C:\Users\Mat\Downloads\forecast.xml

        Scanner input = new Scanner(System.in);

        String text = "";
        String textEscritura = text;
        ArrayList<String> items = new ArrayList<>();

        System.out.print("\nEntra path asta archivo .xml:\n");
        String path = input.nextLine();
        File ruta = new File(path);//"C:\Users\Mat\Downloads\forecast.xml"

        if(ruta.getPath().endsWith("forecast.xml") && ruta.isFile()){

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(ruta);

                Element element = doc.getDocumentElement();//tag <weatherdata>

                NodeList nodeList = element.getChildNodes();//general

                //nom ciutat
                NodeList nodeListLocation = nodeList.item(0).getChildNodes();
                Node nodeCity = nodeListLocation.item(0);
                String ciudad = nodeCity.getTextContent();

                System.out.println("Ciudad: " + ciudad + "\n");

                NodeList forecastNodes = nodeList.item(4).getChildNodes();//tag <time>

                //per cada iteracio tracta les dades de cada node
                for(int i = 0; i < forecastNodes.getLength(); i++){
                    Node tiempo = forecastNodes.item(i);

                    String date = tiempo.getAttributes().item(0).getTextContent();
                    String temperaturaCelsius = tiempo.getChildNodes().item(4).getAttributes().item(3).getTextContent();
                    String clouds = tiempo.getChildNodes().item(7).getAttributes().item(2).getTextContent();
                    String wind = tiempo.getChildNodes().item(3).getAttributes().item(0).getTextContent();

                    //traduccio del camp clouds
                    if(clouds.contains("scattered clouds")){
                        clouds = "Poco nublado";
                    }
                    else if(clouds.contains("broken clouds")){
                        clouds = "Nublado";
                    }
                    else if(clouds.contains("light rain") ){
                        clouds = "LLuvia suave";
                    }
                    else if(clouds.contains("overcast clouds") ){
                        clouds = "Muy nublado";
                    }
                    else if(clouds.contains("few clouds") ){
                        clouds = "Poco nublado";
                    }
                    else if(clouds.contains("clear sky") ){
                        clouds = "Despejado";
                    }

                    double doubleWind = Double.parseDouble(wind);
                    doubleWind = Math.round(doubleWind * 3.62);

                    //resum de dades que es guarden a un ArrayList
                    String resumenItem = "\nFecha y hora: " + date + "\n" +
                            "Temperatura: " + temperaturaCelsius + " Celsius\n" +
                            "Tiempo: " + clouds + "\n" +
                            "Velocidad del viento: " + doubleWind + " km/h";
                    System.out.println(resumenItem);
                    items.add(resumenItem);
                    /*System.out.println(date);
                    System.out.println(temperaturaCelsius);
                    System.out.println(clouds);
                    System.out.println(wind);*/

                    escrituraXml(ciudad, items, date, temperaturaCelsius, clouds, doubleWind, path);
                }


            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.print("\n...el archivo no es forecast.xml\n");
        }
    }

    /**
     * Genera archivo xml con el contenido de la clase
     * @param ciudad
     * @param items
     * @param date
     * @param temperaturaCelsius
     * @param clouds
     * @param doubleWind
     * @param path
     * @throws ParserConfigurationException
     */
    public static void escrituraXml(String ciudad, ArrayList items, String date, String temperaturaCelsius, String clouds, double doubleWind, String path) throws ParserConfigurationException {
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element elmn = doc.createElement("TiempoGeneral");
            doc.appendChild(elmn);

            Element ciudadElemento = doc.createElement("Ciudad");
            Text ciudadText = doc.createTextNode(ciudad);
            ciudadElemento.appendChild(ciudadText);
            elmn.appendChild(ciudadElemento);

            Element Tiempos = doc.createElement("Meteorologia");
            elmn.appendChild(Tiempos);

            for (int i = 0; i < items.size(); i++){

                Element fechaElemento = doc.createElement("Tiempo");
                fechaElemento.setAttribute("fechaHora",date);
                Tiempos.appendChild(fechaElemento);

                Element estadoElemento = doc.createElement ("Estado");
                estadoElemento.setAttribute("nombre",clouds);
                fechaElemento.appendChild(estadoElemento);

                Element vientoElemento = doc.createElement ("Viento");
                vientoElemento.setAttribute("kph", String.valueOf(doubleWind));
                fechaElemento.appendChild(vientoElemento);

                Element temperaturaElemento = doc.createElement ("Temperatura");
                temperaturaElemento.setAttribute("Celsius",temperaturaCelsius);
                fechaElemento.appendChild(temperaturaElemento);
            }
            File file1 = new File(path);
            File file2 = new File(file1.getParent().concat("\\forecastNuevo.xml"));
            OutputFormat of = new OutputFormat(doc);
            of.setIndenting(true);
            FileOutputStream fos = new FileOutputStream(file2);
            XMLSerializer serializer = new XMLSerializer(fos,of);
            serializer.serialize(doc);

        }catch(DOMException e){
            System.out.println("...error de escritura\n" + e);
        }catch (IOException e) {
            System.out.println("...error de escritura\n" + e);
        }
    }
}