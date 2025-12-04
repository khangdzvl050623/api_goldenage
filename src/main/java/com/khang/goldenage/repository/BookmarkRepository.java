package com.khang.goldenage.repository;

import com.khang.goldenage.modal.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
  // Tìm tất cả bookmark của một user, sắp xếp theo ngày mới nhất
  List<Bookmark> findByUserIdOrderByBookmarkDateDesc(Long userId);

  // Tìm bookmark chưa đọc của user
  List<Bookmark> findByUserIdAndIsReadFalse(Long userId);

//  // Tìm bookmark theo category
//  List<Bookmark> findByUserIdAndCategory(Long userId, String category);

  // Kiểm tra đã bookmark chưa
  boolean existsByUserIdAndArticleId(Long userId, Long articleId);

//  // Tìm bookmark cụ thể của user cho article
//  Optional<Bookmark> findByUserIdAndArticleId(Long userId, Long articleId);

//  // Đếm số bookmark của user
//  long countByUserId(Long userId);

//  // Đếm số bookmark chưa đọc của user
//  long countByUserIdAndIsReadFalse(Long userId);

  // Tìm bookmark trong khoảng thời gian
//  List<Bookmark> findByUserIdAndBookmarkDateBetween(
//    Long userId,
//    LocalDateTime startDate,
//    LocalDateTime endDate
//  );

  // JOIN với Articles để tìm bookmark theo title
//  @Query(value ="SELECT b FROM Bookmark b JOIN Articles a ON b.articleId = a.id " +
//    "WHERE b.userId = :userId AND a.title LIKE %:title%")
//  List<Bookmark> findBookmarksByUserAndArticleTitle(
//    @Param("userId") Long userId,
//    @Param("title") String title
//  );

//  // Tìm các category mà user đã sử dụng
//  @Query(value ="SELECT DISTINCT b.category FROM Bookmark b WHERE b.userId = :userId AND b.category IS NOT NULL")
//  List<String> findDistinctCategoriesByUserId(@Param("userId") Long userId);

  // Xóa tất cả bookmark của user cho một article (cleanup)
  void deleteByUserIdAndArticleId(Long userId, Long articleId);
}
