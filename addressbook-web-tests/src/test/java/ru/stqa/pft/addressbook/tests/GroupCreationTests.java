package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupDate> before=app.group().all();
    GroupDate group = new GroupDate().withName("test2");
    app.group().create(group);
    Set<GroupDate> after=app.group().all();
    Assert.assertEquals(after.size(),before.size()+1);

    //первый вариант вычисления элемента с мах id
 //   int max=0;
  //  for (GroupDate g:after){
   //   if(g.getId() > max){
    //    max=g.getId();
   //   }
  //  }

    //второй вариант вычисления элемента с мах id
   // group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    group.withId(after.stream().mapToInt((g) ->g.getId()).max().getAsInt());
    before.add (group);

 //   Comparator<? super GroupDate> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
  //  before.sort(byId);
  //  after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
