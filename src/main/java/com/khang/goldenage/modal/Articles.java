package com.khang.goldenage.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "articles")
public class Articles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title", nullable = false, length = 500)
  private String title;
  private String link;
  @Lob // Nếu description là một trường văn bản dài
  @Column(name = "description",columnDefinition = "TEXT") // Để đảm bảo kiểu dữ liệu trong DB đủ lớn
  private String description;
  private String mediaUrl;
  private String time;
  private LocalDateTime dateTime;
  private String mediaType;

  // Constructor rỗng
  public Articles() {
    this.dateTime = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "Articles{" +
      "title='" + title + '\'' +
      ", link='" + link + '\'' +
      ", description='" + description + '\'' +
      ", mediaUrl='" + mediaUrl + '\'' +
      ", time='" + time + '\'' +
      ", dateTime='" + dateTime + '\'' +
      ", mediaType='" + mediaType + '\'' +
      '}';
  }
}
