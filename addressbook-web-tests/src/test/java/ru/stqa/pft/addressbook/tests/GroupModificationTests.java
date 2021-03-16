package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupDate().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before=app.db().groups();
    GroupDate modifiedGroup = before.iterator().next();
    GroupDate group = new GroupDate()
            .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test4");
    app.goTo().groupPage();
    app.group().modify(group);

    MatcherAssert.assertThat(app.group().count(),CoreMatchers.equalTo(before.size()));
    Groups after=app.db().groups();
    //без сокращения статических методов
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));

    verifyGroupListInUI();
  }




}
