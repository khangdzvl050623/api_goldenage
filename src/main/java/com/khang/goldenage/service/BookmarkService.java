package com.khang.goldenage.service;

import com.khang.goldenage.dto.BookmarkRequestDto;
import com.khang.goldenage.dto.BookmarkResponseDto;
import com.khang.goldenage.modal.Bookmark;
import com.khang.goldenage.repository.ArticlesRepository;
import com.khang.goldenage.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookmarkService {


  @Autowired
  private BookmarkRepository bookmarkRepository;

  @Autowired
  private ArticlesRepository articlesRepository;

  public List<BookmarkResponseDto> getBookmarksByUserId(Long userId) {
    List<Bookmark> bookmarks = bookmarkRepository.findByUserIdOrderByBookmarkDateDesc(userId);
    return bookmarks.stream()
      .map(this::convertToDto) // Ánh xạ từ Bookmark Entity sang DTO
      .collect(Collectors.toList());
  }

  public boolean isBookmarked(Long userId, Long articleId) {
    return bookmarkRepository.existsByUserIdAndArticleId(userId, articleId);
  }

  public Bookmark addBookmark(BookmarkRequestDto bookmarkDTO) {
    // Kiểm tra xem bookmark đã tồn tại chưa để tránh trùng lặp
    if (!isBookmarked(bookmarkDTO.getUserId(), bookmarkDTO.getArticleId())) {
      Bookmark bookmark = new Bookmark();
      bookmark.setUserId(bookmarkDTO.getUserId());
      bookmark.setArticleId(bookmarkDTO.getArticleId());
      bookmark.setBookmarkDate(LocalDateTime.now());
      bookmark.setArticleMediaUrl(bookmarkDTO.getArticleMediaUrl());
      bookmark.setArticleMediaType(bookmarkDTO.getArticleMediaType());
      return bookmarkRepository.save(bookmark);
    }
    return null;
  }

  @Transactional
  public void deleteBookmark(Long userId, Long articleId) {
    bookmarkRepository.deleteByUserIdAndArticleId(userId, articleId);
  }

  // Phương thức chuyển đổi từ Entity sang DTO

  private BookmarkResponseDto convertToDto(Bookmark bookmark) {
    BookmarkResponseDto dto = new BookmarkResponseDto();
    dto.setId(bookmark.getId());
    dto.setUserId(bookmark.getUserId());
    dto.setArticleId(bookmark.getArticleId());
    dto.setBookmarkDate(bookmark.getBookmarkDate());

    // Lấy thông tin từ Article trước
    articlesRepository.findById(bookmark.getArticleId()).ifPresent(article -> {
      dto.setArticleTitle(article.getTitle());
      dto.setArticleMediaUrl(article.getMediaUrl());
      dto.setArticleMediaType(article.getMediaType());
    });

    return dto;
  }
}
