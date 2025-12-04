package com.khang.goldenage.dto;

import lombok.Data;

@Data
public class BookmarkRequestDto {
  private Long userId;
  private Long articleId;

  // Thông tin article
  private String articleTitle;
  private String articleMediaUrl;
  private String articleMediaType;
}
