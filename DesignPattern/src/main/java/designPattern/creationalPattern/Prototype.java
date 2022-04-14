package designPattern.creationalPattern;

public class Prototype {

	public static void main(String args[]) {
		Item oldItem = new Book("book", 10.5, 150);
		Item newItem = null;
		
		oldItem.info();
		
		ItemCreator creator = new ItemCreator(oldItem);
		newItem = creator.makeAnItem();
		
		newItem.info();
	}
}

/////////////////////////////////////////
//Prototype
abstract class Item implements Cloneable{
	
	private String title;
	private double price;
	
	public Item clone() {
		Item cloneItem = null;
		try {
			cloneItem = (Item) super.clone();
			cloneItem.price = price;
			cloneItem.title = title;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cloneItem;
	}
	
	public void info() {
		System.out.println("Item with title:" + title + " price: " + price);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}

//Concrete Prototype
class Book extends Item {
	
	private int pages;
	
	public Book(String title, double price, int pages) {
		this.setTitle(title);
		this.setPrice(price);
		this.pages = pages;
	}
	
	public Book clone() {
		
		Book cloneBook = (Book) super.clone();
		cloneBook.pages = pages;
		
		return cloneBook;
	}
	
	public void info() {
		super.info();
		System.out.println("Is a book with " + pages + " pages" );
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	
}


//Client
class ItemCreator {
	
	private Item item;
	
	public ItemCreator(Item item) {
		this.item = item;
	}
	
	public Item makeAnItem() {
		return (Item) item.clone();
	}
	
	
}

