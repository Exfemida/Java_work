package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		Point point1=new Point(2,2);
		Point point2=new Point(4,4);


		System.out.println("Расстояние между точками с координатами: ");
		System.out.println("x="+point1.x+" ,y="+point1.y);
		System.out.println("x="+point2.x+" ,y="+point2.y);
		System.out.println("составляет "+point1.distance(point2));
	}
}