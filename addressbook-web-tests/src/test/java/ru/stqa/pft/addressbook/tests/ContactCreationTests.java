package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json="";
    String line = reader.readLine();
    while (line!=null) {
      json+=line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactDate> contacts = gson.fromJson(json, new TypeToken<List<ContactDate>>(){}.getType());
    return contacts.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
  }

    @BeforeMethod
  public void ensurePreconditions() {
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupDate().withName("test1"));
      }
  }

  @Test (dataProvider = "validContactsFromJson") //(enabled = false)
  public void testCreateNewContact(ContactDate cont) throws Exception {
    //достаем группы из базы
    Groups groups =app.db().groups();

    Contacts before = app.db().contacts();
    app.goTo().AddContactPage();
    File photo = new File("src/test/resources/stru.png");
    cont.withPhoto(photo);
    cont.inGroup(groups.iterator().next());
//    cont.withNewGroup(groups.stream().map((g) ->g.getName()).findAny().get()); //подставляем любую из имеющихся групп
    app.contact().create(cont, true);
    Contacts after = app.db().contacts();

    MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size()+1));
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(
            before.withAdded(cont.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt()))));
  }


}
