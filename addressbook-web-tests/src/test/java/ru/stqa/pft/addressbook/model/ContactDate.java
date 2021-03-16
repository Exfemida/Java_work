package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="addressbook")
public class ContactDate {

  @Id
  @Column(name="id")
  private int id=Integer.MAX_VALUE;;

  @Column(name="firstname")
  @Expose private String firstname;

  @Column(name="middlename")
  @Expose private String middlename;

  @Column(name="lastname")
  @Expose private String lastname;

  @Column(name="nickname")
  @Expose private String nickname;

  @Column(name="title")
  @Expose private String title;

  @Column(name="company")
  @Expose private String company;

  @Column(name="address")
  @Type (type="text")
  @Expose private String address;

  @Column(name="home")
  @Type (type="text")
  @Expose private String homePhone;

  @Column(name="mobile")
  @Type (type="text")
  @Expose private String mobilePhone;

  @Column(name="work")
  @Type (type="text")
  @Expose private String workPhone;

  @Column(name="fax")
  @Type (type="text")
  @Expose private String fax;

  @Column(name="email")
  @Type (type="text")
  @Expose private String email;

  @Column(name="email2")
  @Type (type="text")
  @Expose private String email2;

  @Column(name="email3")
  @Type (type="text")
  @Expose private String email3;

  @Column(name="homepage")
  @Type (type="text")
  @Expose private String homepage;

  @Column(name="bday", columnDefinition = "TINYINT")
  @Expose private int bday;

  @Column(name="bmonth")
  @Expose private String bmonth;

  @Column(name="byear")
  @Expose private String byear;

  @Column(name="aday", columnDefinition = "TINYINT")
  @Expose private int aday;

  @Column(name="amonth")
  @Expose private String amonth;

  @Column(name="ayear")
  @Expose private String ayear;

  @Column(name="address2")
  @Type (type="text")
  @Expose private String address2;

  @Column(name="phone2")
  @Type (type="text")
  @Expose private String phone2;

  @Column(name="notes")
  @Type (type="text")
  @Expose private String notes;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Column(name="photo")
  @Type (type="text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="address_in_groups",
          joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="group_id"))
  private Set<GroupDate> groups = new HashSet<GroupDate>();

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

  public int getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public int getAday() {
    return aday;
  }

  public String getAmonth() {
    return amonth;
  }

  public String getAyear() {
    return ayear;
  }

  public Groups getGroups() {
    return new Groups(groups);
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
    return new File (photo);
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
  public ContactDate withBday(String bday) {     //    пока отдаем String!!!!
    this.bday=Integer.parseInt(bday);
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
  public ContactDate withAday(String aday) {    //    пока отдаем String!!!!
    this.aday=Integer.parseInt(aday);
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
    this.photo = photo.getPath();
    return this;

  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactDate that = (ContactDate) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(middlename, that.middlename) && Objects.equals(lastname, that.lastname) && Objects.equals(nickname, that.nickname) && Objects.equals(title, that.title) && Objects.equals(company, that.company) && Objects.equals(address, that.address) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(fax, that.fax) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(homepage, that.homepage) && Objects.equals(address2, that.address2) && Objects.equals(phone2, that.phone2) && Objects.equals(notes, that.notes) && Objects.equals(allPhones, that.allPhones) && Objects.equals(allEmails, that.allEmails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, nickname, title, company, address, homePhone, mobilePhone, workPhone, fax, email, email2, email3, homepage, address2, phone2, notes, allPhones, allEmails);
  }

  @Override
  public String toString() {
    return "Contacts{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }


  public ContactDate inGroup(GroupDate group) {
    groups.add(group);
    return this;
  }
}

