package data;

import java.util.ArrayList;

public abstract class Data {
	protected ArrayList<Element> elements;

	public Data() {
		elements = new ArrayList<>();
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
            res.add(new Iris());
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

    public void appendData(Data d) {
        for (int i = 0; i < d.size(); i++) {
            addElement(d.getElement(i));
        }
    }

    public abstract ArrayList<String> getLabels();

    public Data getTrainData(int noSections, int noTestSection) {
        ArrayList<Data> sections = divide(noSections);
        Data res = new Iris();

        for (int i = 0; i < noSections; i++) {
            if (i != noTestSection) {
                res.appendData(sections.get(i));
            }
        }

        return res;
    }

    public Data getTestData(int noSections, int noTestSection) {
        ArrayList<Data> sections = divide(noSections);
        
        return sections.get(noTestSection);
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof Data && ((Data)o).elements.equals(this.elements));
    }

    public Element getRandomElement() {
        return elements.get((int)Math.random()*elements.size());
    }
}
