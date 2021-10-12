package ru.stqa.pft;

import java.io.*;

public class Jedi {
  public static void main(String[] args) throws Exception {
    int array[]=new int[20];
    BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
    for (int i = 0; i < 20; i++)
      array[i] = Integer.parseInt(buff.readLine());
    int array_s1[]=new int[10];
    int array_s2[]=new int[10];
    for (int i = 0; i < 10; i++) {
      array_s1[i] = array[i];
      array_s2[i] =array[i+10];
      System.out.println(i+" = "+array_s2[i]);
    }

  }
}
