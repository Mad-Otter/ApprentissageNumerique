package data;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.awt.*;
import java.io.*;

public class SonarConverter extends Data {
    public SonarConverter() {
        super();
    }

    @Override
    public ArrayList<String> getLabels() {
        return null;
    }

    public SonarConverter(String url, String startSymbol) {
        elements = new ArrayList<>();

        try {
            boolean on = false;
            String endSymbol = "   =>  ";

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url))));
            String line;

            float[] currentVector = new float[60];
            int currentPos = 0;

            while ((line = br.readLine()) != null) {
                if (on) {
                    if (line.equals("$TEST")) {
                        break;
                    }

                    if (line.contains(endSymbol)) {
                        String[] mainSplit = line.split(endSymbol);
                        String[] splitted = mainSplit[0].split(", ");
                        for (String elm : splitted) {
                            if (elm.length() > 0) {
                                currentVector[currentPos] = Float.parseFloat(elm);
                                currentPos++;
                            }
                        }

                        Element newElement = new Element(currentVector, mainSplit[1].replace(";", ""));
                        elements.add(newElement);

                        currentVector = new float[60];
                        currentPos = 0;
                    } else {
                        String[] splitted = line.split(", ");
                        for (String elm : splitted) {
                            if (elm.length() > 0) {
                                currentVector[currentPos] = Float.parseFloat(elm);
                                currentPos++;
                            }
                        }
                    }
                } else if(line.equals(startSymbol)) {
                    on = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier " + url + " est introuvable.");
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void exportGinnet(String url) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(url));

            for (Element e : elements) {
                printWriter.println(e.toGinnetLine());
            }

            printWriter.close();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
