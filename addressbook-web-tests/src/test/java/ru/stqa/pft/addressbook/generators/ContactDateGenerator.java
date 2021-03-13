package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactDate;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDateGenerator {

  @Parameter(names="-c", description="Contact count")
  public int count;

  @Parameter(names="-f", description = "Target file")
  public String file;

  @Parameter(names="-d", description = "Data format")
  public String format;

  public static void main (String [] args) throws IOException {
    ContactDateGenerator generator=new ContactDateGenerator();
    JCommander jCommander=new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactDate> contacts = generateContacts (count);
    if (format.equals("json")) {
      saveAsJson(contacts,new File (file));
    } else {
      System.out.println("Ребята, я не умею работать с форматом " + format);
    }

  }

  private void saveAsJson(List<ContactDate> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();

  }

  private List <ContactDate> generateContacts(int count) {
    List <ContactDate> contacts = new ArrayList<ContactDate>();
    for (int i=0; i<count; i++){
      contacts.add(new ContactDate()
      //        .withId(i)
              .withFirstname(String.format("Firstname %s",i))
              .withMiddlename(String.format("Middlename %s",i))
              .withLastname(String.format("Lastname %s",i))
              .withNickname(String.format("Nickname %s",i))
              .withTitle(String.format("Title %s",i))
              .withCompany(String.format("Company %s",i))
              .withAddress(String.format("Address %s",i))
              .withHomePhone(String.format("HomePhone %s",i))
              .withMobilePhone(String.format("MobilePhone %s",i))
              .withWorkPhone(String.format("WorkPhone %s",i))
              .withFax(String.format("Fax %s",i))
              .withEmail(String.format("Email %s",i))
              .withEmail2(String.format("Email2 %s",i))
              .withEmail3(String.format("Email3 %s",i))
              .withHomepage(String.format("Homepage %s",i))
              .withBday(String.format("Bday %s",i))
              .withBmonth(String.format("Bmonth %s",i))
              .withByear(String.format("Byear %s",i))
              .withAday(String.format("Aday %s",i))
              .withAmonth(String.format("Amonth %s",i))
              .withAyear(String.format("Ayear %s",i))
              .withNewGroup(String.format("NewGroup %s",i))
              .withAddress2(String.format("Address2 %s",i))
              .withPhone2(String.format("Phone2 %s",i))
              .withNotes(String.format("Notes %s",i))
      );
    }
    return contacts;
  }
}

