import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;




// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


// Program for print data in JSON format.
public class ReadJson {
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        pull();

    }


    public static void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://app.ticketmaster.com/discovery/v2/attractions?apikey=nNRV8kCwsCwhOhUO8PbwFiDHb78KAJ5A&locale=*");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            JSONObject embedded = (JSONObject) jsonObject.get("_embedded");
            System.out.println(embedded);

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) embedded.get("attractions");
            //JSONObject Url = (JSONObject) jsonObject.get("url");
            // System.out.println(Url);

            int n = msg.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) msg.get(i);
                String Name=(String) test.get("name");
                System.out.println(Name);
                // System.out.println(test);
                org.json.simple.JSONArray images = (org.json.simple.JSONArray) test.get("images");
                //System.out.println(images);
                for(int l=0; l< images.size(); l++){
                    JSONObject Image = (JSONObject) images.get(l);
                    //  System.out.println(Image);
                    String Url = (String) Image.get("url");
                    System.out.println(Url);
                }

                // System.out.println(person.getInt("key"));
            }
            // String height= (String)jsonObject.get("height");
            // System.out.println(embedded);

        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }

}



