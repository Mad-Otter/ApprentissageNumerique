package data;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.*;
import java.io.*;

public class Iris extends Data {
    public Iris() {
        super();
    }

    public Iris(String url) {
        super();

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url))));
            String line;

            while ((line = br.readLine()) != null) {
                Element e = new Element(line);

                if (!e.isEmpty()) {
                    elements.add(e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + url + " est introuvable.");
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
