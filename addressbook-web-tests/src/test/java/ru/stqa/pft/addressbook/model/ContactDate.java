package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class ContactDate {
  private int id=Integer.MAX_VALUE;;
  @Expose private String firstname;
  @Expose private String middlename;
  @Expose private String lastname;
  @Expose private String nickname;
  @Expose private String title;
  @Expose private String company;
  @Expose private String address;
  @Expose private String homePhone;
  @Expose private String mobilePhone;
  @Expose private String workPhone;
  @Expose private String fax;
  @Expose private String email;
  @Expose private String email2;
  @Expose private String email3;
  @Expose private String homepage;
  @Expose private String bday;
  @Expose private String bmonth;
  @Expose private String byear;
  @Expose private String aday;
  @Expose private String amonth;
  @Expose private String ayear;
  @Expose private String newGroup;
  @Expose private String address2;
  @Expose private String phone2;
  @Expose private String notes;
  private String allPhones;
  private String allEmails;
  private File photo;

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

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
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

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    return photo;
  }


  public ContactDate withId(int id) {
    this.id = id;
    return this;
  }
  public ContactDate withFirstname(String firstname) {
    this.firstname=firstname;
    return this;
  }
  public ContactDate withMiddlename(String middlename) {
    this.middlename=middlename;
    return this;
  }
  public ContactDate withLastname(String lastname) {
    this.lastname=lastname;
    return this;
  }
  public ContactDate withNickname(String nickname) {
    this.nickname=nickname;
    return this;
  }
  public ContactDate withTitle(String title) {
    this.title=title;
    return this;
  }
  public ContactDate withCompany(String company) {
    this.company=company;
    return this;
  }
  public ContactDate withAddress(String address) {
    this.address=address;
    return this;
  }
  public ContactDate withHomePhone(String home) {
    this.homePhone=home;
    return this;
  }
  public ContactDate withMobilePhone(String mobile) {
    this.mobilePhone =mobile;
    return this;
  }
  public ContactDate withWorkPhone(String work) {
    this.workPhone =work;
    return this;
  }
  public ContactDate withFax(String fax) {
    this.fax=fax;
    return this;
  }
  public ContactDate withEmail(String email) {
    this.email=email;
    return this;
  }
  public ContactDate withEmail2(String email2) {
    this.email2=email2;
    return this;
  }
  public ContactDate withEmail3(String email3) {
    this.email3=email3;
    return this;
  }
  public ContactDate withHomepage(String homepage) {
    this.homepage=homepage;
    return this;
  }
  public ContactDate withBday(String bday) {
    this.bday=bday;
    return this;
  }
  public ContactDate withBmonth(String bmonth) {
    this.bmonth=bmonth;
    return this;
  }
  public ContactDate withByear(String byear) {
    this.byear=byear;
    return this;
  }
  public ContactDate withAday(String aday) {
    this.aday=aday;
    return this;
  }
  public ContactDate withAmonth(String amonth) {
    this.amonth=amonth;
    return this;
  }
  public ContactDate withAyear(String ayear) {
    this.ayear=ayear;
    return this;
  }
  public ContactDate withNewGroup(String newGroup) {
    this.newGroup=newGroup;
    return this;
  }
  public ContactDate withAddress2(String address2) {
    this.address2=address2;
    return this;
  }
  public ContactDate withPhone2(String phone2) {
    this.phone2=phone2;
    return this;
  }
  public ContactDate withNotes(String notes) {
    this.notes=notes;
    return this;
  }
  public ContactDate withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactDate withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactDate withPhoto(File photo) {
    this.photo = photo;
    return this;

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

