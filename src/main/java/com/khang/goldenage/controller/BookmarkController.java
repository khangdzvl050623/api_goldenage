package com.khang.goldenage.controller;

import com.khang.goldenage.dto.BookmarkRequestDto;
import com.khang.goldenage.dto.BookmarkResponseDto;
import com.khang.goldenage.modal.Bookmark;
import com.khang.goldenage.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/bookmarks")
public class BookmarkController {
  @Autowired
  private BookmarkService bookmarkService;

  @GetMapping("/{userId}")
  public ResponseEntity<List<BookmarkResponseDto>> getBookmarksByUserId(@PathVariable Long userId) {
    List<BookmarkResponseDto> bookmarks = bookmarkService.getBookmarksByUserId(userId);
    return ResponseEntity.ok(bookmarks);
  }

  // Kiểm tra xem một bài báo đã được bookmark hay chưa
  @GetMapping("/isBookmarked/{userId}/{articleId}")
  public ResponseEntity<Boolean> isBookmarked(@PathVariable Long userId, @PathVariable Long articleId) {
    boolean isBookmarked = bookmarkService.isBookmarked(userId, articleId);
    return ResponseEntity.ok(isBookmarked);
  }

  // Thêm bookmark
  @PostMapping
  public ResponseEntity<Bookmark> addBookmark(@RequestBody BookmarkRequestDto bookmarkDto) {
    Bookmark newBookmark = bookmarkService.addBookmark(bookmarkDto);
    if (newBookmark != null) {
      return ResponseEntity.ok(newBookmark);
    }
    return ResponseEntity.badRequest().build();
  }

  // Xóa bookmark
  @DeleteMapping("/{userId}/{articleId}")
  public ResponseEntity<Void> deleteBookmark(@PathVariable Long userId, @PathVariable Long articleId) {
    bookmarkService.deleteBookmark(userId, articleId);
    return ResponseEntity.ok().build();
  }

}
