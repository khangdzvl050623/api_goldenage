package com.khang.goldenage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {
  private Long id;
  private String type;
  private String content;
  private LocalDateTime timestamp;
  private boolean isRead;

}
