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

    public String getConsensus(Element e, int k) {
        ArrayList<Element> list = getKNN(e, k);
        ArrayList<LabelScore> labels = new ArrayList<>();

        for (Element el : list) {
            LabelScore cls = null;

            for (LabelScore ls : labels) {
                if (ls.getLabel().equals(el.getLabel())) {
                    cls = ls;
                    break;
                }
            }

            if (cls == null) {
                cls = new LabelScore(el.getLabel());
                labels.add(cls);
            }

            cls.increment();
        }

        LabelScore max = labels.get(0);
        for (LabelScore ls : labels) {
            if (ls.getScore() > max.getScore()) {
                max = ls;
            }
        }

        return max.getLabel();
    }

    public ArrayList<Element> getKNN(Element e, int k) {
        ArrayList<Element> knn = new ArrayList<>();
        PriorityQueue<ElementDistance> q = new PriorityQueue<>();

        for (Element el : elements) {
            q.add(new ElementDistance(el, el.getManhattanDistance(e)));
        }

        for (int i = 0; i < Math.min(q.size(), k); i++) {
            knn.add(q.poll().getElement());
        }

        return knn;
    }
}
