package com.khang.goldenage.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "bookmarks", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"user_id", "article_id"})// Đảm bảo không trùng bookmark cho cùng user + article
})
public class Bookmark {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "article_id", nullable = false)
  private Long articleId;

  @Column(name = "article_media_url", length = 2048)
  private String articleMediaUrl;

  @Column(name = "article_media_type", length = 50)
  private String articleMediaType;


  @Column(name = "bookmark_date", nullable = false)
  private LocalDateTime bookmarkDate;

  @Column(name = "category", length = 100)
  private String category;

  @Column(name = "is_read", nullable = false)
  private boolean isRead = false;

  @Column(name = "reading_progress")
  private Integer readingProgress = 0; // % đã đọc (0-100)


}
