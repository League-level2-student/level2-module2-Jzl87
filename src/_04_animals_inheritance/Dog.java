package _04_animals_inheritance;

public class Dog {
	String name;
	String color;
	boolean sex;
	
	public Dog(String name, String color, boolean sex) {
		this.name = name;
		this.color = color;
		this.sex = sex;
	}
	
	public void printName () {
		System.out.println(name);
	}
	
	public void play () {
		System.out.println("playing");
	}
	
	public void sleep () {
		System.out.println("Sleeping");
	}
	
	public void eat () {
		System.out.println("Eating");
	}

}
