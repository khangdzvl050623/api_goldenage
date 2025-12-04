package com.khang.goldenage.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Table(name = "notifications")
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private String type;
  @Column(columnDefinition = "TEXT")
  private String content;
  private LocalDateTime timestamp;
  private boolean isRead; // Đánh dấu đã đọc hay chưa


  public Notification(User user, String goldPrice, String notificationContent, LocalDateTime now, boolean b) {
  }
}
