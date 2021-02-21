package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args){
    String[] langs={"Java","C#","Python","PHP"}; //инициализация массива

    List<String> languages= Arrays.asList("Java","C#","Python","PHP"); //преобразование списка в массив и иинциализация

    for (String l:languages){
      System.out.println("Я хочу выучить "+l);
    }
  //  languages.add("Java"); //заполнение элементов списка если не преобразовывать в массив
  //  languages.add("C#");
  //  languages.add("Python");
  //  languages.add("PHP");

  }
}
