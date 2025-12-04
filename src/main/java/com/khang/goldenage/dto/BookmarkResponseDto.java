package com.khang.goldenage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkResponseDto {
  private Long id;
  private Long userId;
  private Long articleId;
  private LocalDateTime bookmarkDate;
  private String articleTitle;
  private String articleMediaUrl;
  private String articleMediaType;

}
