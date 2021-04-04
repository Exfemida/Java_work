package ru.stqa.pft.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("bugtracker")
@Entity
@Table(name="mantis_user_table")
public class UsersDate {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id=Integer.MAX_VALUE;

  @Column(name = "username")
  @Type(type="text")
  private  String username;

  @Column(name = "email")
  @Type(type="email")
  private  String email;

  @Column(name = "password")
  @Type(type="password")
  private  String password;

  public UsersDate withId(int id) {
    this.id = id;
    return this;
  }

  public UsersDate withUsername(String username) {
    this.username = username;
    return this;
  }

  public UsersDate withEmail(String email) {
    this.email = email;
    return this;
  }

  public UsersDate withPassword(String password) {
    this.password = password;
    return this;
  }
}
