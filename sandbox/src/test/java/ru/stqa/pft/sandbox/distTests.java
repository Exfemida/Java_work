package ru.stqa.pft.sandbox;

public class distTests {
  public void testDistance(){
    Point point1=new Point(2,2);
    Point point2=new Point(4,4);
    assert point1.distance(point2)==0;
  }
}
