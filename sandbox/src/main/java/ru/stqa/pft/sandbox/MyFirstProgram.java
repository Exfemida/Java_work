package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {
		System.out.println("Hello, Im Luntik!");

		Point p1=new Point();
		p1.x=2;
		p1.y=2;

		Point p2=new Point();
		p1.x=4;
		p2.y=4;

		System.out.println(distance(p1,p2));
	}
	public static double distance(Point p1, Point p2) {
		double l;
		l=Math.sqrt((p2.x- p1.x)*(p2.x- p1.x)+(p2.y- p1.y)*(p2.y- p1.y));
		return l;
	}
}