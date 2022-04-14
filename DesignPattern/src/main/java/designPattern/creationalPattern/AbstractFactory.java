package designPattern.creationalPattern;

//Main
public class AbstractFactory {

	public static void main(String[] args) {
		String platform = "ciao";
		
		AbstractWindowWidgetFactory factory = AbstractWindowWidgetFactory.getFactory(platform);;
		
		factory.createWindow();
		factory.craeteWidget();
	}
	
}

///////////////////////////////////////////////
//Abstract Product
interface Window {
	public void setTitle(String text);
	public void repaint();
}

interface Widget {
	public void setTitle(String text);
}

//Concrete Products
class MSWindow implements Window {
	public void setTitle(String text) {  System.out.println("MSWindow setTitle() : " + text); }
	public void repaint() { System.out.println("MSWindow repaint()");}
}

class MacWindow implements Window {
	public void setTitle(String text) { System.out.println("MacWindow setTitle() : " + text); }
	public void repaint() { System.out.println("MacWindow repaint()"); }
}

class MSWidget implements Widget {
	public void setTitle(String text) { System.out.println("MSWidget setTitle(): " + text); }
}

class MacWidget implements Widget {
	public void setTitle(String text) { System.out.println("MacWidget setTitle(): " + text); }
}

////////////////////////////////////////////
//Abstract Factory
interface AbstractWindowWidgetFactory {

	public static AbstractWindowWidgetFactory getFactory(String platform) {

		AbstractWindowWidgetFactory factory = null;
		if(platform =="MACOSX") {
			factory = new MacWindowWidgetFactory();
		} else {
			factory = new MSWindowWidgetFactory();
		}
		return factory;
	}

	public Window createWindow();
	public Widget craeteWidget();
}


//Concrete Factory
class MSWindowWidgetFactory implements AbstractWindowWidgetFactory {
	public Window createWindow() { return new MSWindow(); }
	public Widget craeteWidget() { return new MSWidget(); }
}

class MacWindowWidgetFactory implements AbstractWindowWidgetFactory {
	public Window createWindow() { return new MacWindow(); }
	public Widget craeteWidget() { return new MacWidget(); }
}
