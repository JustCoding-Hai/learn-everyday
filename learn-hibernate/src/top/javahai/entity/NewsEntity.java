package top.javahai.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @author Hai
 * @date 2020/7/12 - 0:10
 */
@Entity
public class NewsEntity {
  private int id;
  private String title;
  private String author;
  private Date date;

  @Id
  @Column(name = "ID")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "TITLE")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Basic
  @Column(name = "AUTHOR")
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Basic
  @Column(name = "DATE")
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewsEntity that = (NewsEntity) o;
    return id == that.id &&
            Objects.equals(title, that.title) &&
            Objects.equals(author, that.author) &&
            Objects.equals(date, that.date);
  }

  public NewsEntity(String title, String author, Date date) {
    this.title = title;
    this.author = author;
    this.date = date;
  }

  public NewsEntity() {
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, date);
  }
}
