package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactDate {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String home;
  private final String mobile;
  private final String work;
  private final String fax;
  private final String email;
  private final String email2;
  private final String email3;
  private final String homepage;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String aday;
  private final String amonth;
  private final String ayear;
  private final String newGroup;
  private final String address2;
  private final String phone2;
  private final String notes;


  public ContactDate(String firstname, String middlename, String lastname, String nickname, String title, String company, String address, String home, String mobile, String work, String fax, String email, String email2, String email3, String homepage, String bday, String bmonth, String byear, String aday, String amonth, String ayear, String newGroup, String address2, String phone2, String notes) {
    this.id=Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.email2 = email2;
    this.email3 = email3;
    this.homepage = homepage;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.aday = aday;
    this.amonth = amonth;
    this.ayear = ayear;
    this.newGroup = newGroup;
    this.address2 = address2;
    this.phone2 = phone2;
    this.notes = notes;
  }

  public ContactDate(int id, String lastname, String firstname) {
    this.id = id;
    this.lastname = lastname;
    this.middlename = null;
    this.firstname = firstname;
    this.nickname = null;
    this.title = null;
    this.company = null;
    this.address = null;
    this.home = null;
    this.mobile = null;
    this.work = null;
    this.fax = null;
    this.email = null;
    this.email2 = null;
    this.email3 = null;
    this.homepage = null;
    this.bday = null;
    this.bmonth = null;
    this.byear = null;
    this.aday = null;
    this.amonth = null;
    this.ayear = null;
    this.newGroup = null;
    this.address2 = null;
    this.phone2 = null;
    this.notes = null;

  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getAday() {
    return aday;
  }

  public String getAmonth() {
    return amonth;
  }

  public String getAyear() {
    return ayear;
  }

  public String getNewGroup() {
    return newGroup;
  }

  public String getAddress2() {
    return address2;
  }

  public String getPhone2() {
    return phone2;
  }

  public String getNotes() {
    return notes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactDate contacts = (ContactDate) o;
    return id == contacts.id && Objects.equals(firstname, contacts.firstname) && Objects.equals(lastname, contacts.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "Contacts{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}

