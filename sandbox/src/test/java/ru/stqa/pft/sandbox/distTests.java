package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class distTests {
  @Test
  public void testDistancePositive(){ //положительные
    Point point1=new Point(2,2);
    Point point2=new Point(4,4);
    Assert.assertEquals(point1.distance(point2),2.83);
  }
  @Test
  public void testDistanceNegative(){ //отрицательные
    Point point1=new Point(-2,-2);
    Point point2=new Point(-3,-3);
    Assert.assertEquals(point1.distance(point2),1.41);
  }
  @Test
  public void testDistanceZero(){ //ноль
    Point point1=new Point(-2,-2);
    Point point2=new Point(-2,-2);
    Assert.assertEquals(point1.distance(point2),0);
  }
}
