package designPattern.structuralPattern;

import java.util.ArrayList;
import java.util.List;

public class Composite {
	public static void main(String[] args) {
        Ellisse ellisse1 = new Ellisse();
        Ellisse ellisse2 = new Ellisse();
        Ellisse ellisse3 = new Ellisse();
        Ellisse ellisse4 = new Ellisse();

        CompositeGraphic graphic = new CompositeGraphic();
        CompositeGraphic graphic1 = new CompositeGraphic();
        CompositeGraphic graphic2 = new CompositeGraphic();

        graphic1.add(ellisse1);
        graphic1.add(ellisse2);
        graphic1.add(ellisse3);

        graphic2.add(ellisse4);

        graphic.add(graphic1);
        graphic.add(graphic2);

        graphic.print();
    }
}

//Component
interface Graphic {
	
	public void print();
}

//Composite
class CompositeGraphic implements Graphic {
	
	private List<Graphic> children = new ArrayList<Graphic>();
	
	public void print() {
		for(Graphic graphic : children) {
			graphic.print();
		}
	}
	
	public void add(Graphic graphic) {
		children.add(graphic);
	}
	
	public void remove(Graphic graphic) {
		children.remove(graphic);
	}
}

//Leaf
class Ellisse implements Graphic {
	
	public void print() {
		System.out.println("Ellisse");
	}
}