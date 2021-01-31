package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		Point p=new Point(2,2,4,4);


		System.out.println("Расстояние между точками с координатами: ");
		System.out.println("x="+p.x1+" ,y="+p.y1);
		System.out.println("x="+p.x2+" ,y="+p.y2);
		System.out.println("составляет "+p.distance());
	}
}