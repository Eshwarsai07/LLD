package com.LLD.practice.LiskovSubstitution;

public class LiskovExample {
	
	public static class MenuItem{
		int price;
		public String name;
		public MenuItem(int price,String name) {
			this.price = price;
			this.name = name;
		}
		
		public int getPrice() {
			return this.price;
		}
	}
	
	public static class BeverageItem extends MenuItem{
		public BeverageItem(int price,String name) {
			super(price,name);
		}
		
		public int getPrice() {
			return (int) (this.price - (this.price*getDiscount()));
		}
		
		private double getDiscount() {
			
			return 0.3;
		}

		public double getPriceWithDiscount() {
			return this.price - (this.price*0.3);
		}
		
	}
	
	public static void printItemPrice(MenuItem item) {
		
//		this will voilate liskov priciple as we need to check if item is 
//		an instance of menuitem or beverageItem and call item.getPrice or 
//		item.getPriceWithDiscount .
//		This check is violating the priciple
		
		if(item instanceof BeverageItem) {
			System.out.println("Beverage Item price is  ");
			System.out.println(((BeverageItem) item).getPriceWithDiscount());
		}else {
			System.out.println("Menu Item price is "+item.getPrice());
		}
		
	}
	
	public static void printItemPrice2(MenuItem item) {
//		This follows Liskov priciple as we dont need any check as of to print the price
//		all the objects can call the particualr method interchagebly
//		even if we have another class like DesertItem we can override the 
//		getprice method and apply the discount 
		System.out.println("Price of item "+item.getPrice());
	}
	
	public static void main(String[] args) {
		MenuItem item = new BeverageItem(24, "Pizza");
		printItemPrice(item);
		printItemPrice2(item);
		
	}

}
