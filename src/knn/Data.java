import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Data {
    private ArrayList<Element> elements;

    public Data() {
        elements = new ArrayList<>();
    }

    public Data(String url) {
        elements = new ArrayList<>();

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

    public void addElement(Element e) {
        elements.add(e);
    }

    public Element getElement(int i) {
        return elements.get(i);
    }

    public ArrayList<Data> divide(int no) {
        ArrayList<Data> res = new ArrayList<>();

        for (int i = 0; i < no; i++) {
            res.add(new Data());
        }

        for (int i = 0; i < elements.size(); i++) {
            res.get(i % no).addElement(elements.get(i));
        }

        return res;
    }

    public int size() {
        return elements.size();
    }

    public void trace() {
        System.out.println("Base de " + size() + " elements");
        for (Element e : elements) {
            System.out.println(e);
        }
    }
}
