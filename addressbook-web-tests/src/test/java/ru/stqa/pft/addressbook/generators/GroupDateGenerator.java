package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GroupDateGenerator {

  public static void main (String [] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List <GroupDate> groups = generateGroups (count);
    save(groups,file);

  }

  private static void save(List <GroupDate> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupDate group : groups) {
      writer.write(String.format("%s;%s;%s;%s\n",group.getId(), group.getName(), group.getHeader(), group.getFooter()));
    }
    writer.close();
  }

  private static List <GroupDate> generateGroups(int count) {
    List <GroupDate> groups = new ArrayList<GroupDate>();
    for (int i=0; i<count; i++){
      groups.add(new GroupDate().withId(i).withName(String.format("test %s",i))
              .withHeader(String.format("header %s",i)).withFooter(String.format("footer %s",i)));
    }
    return groups;
  }
}
