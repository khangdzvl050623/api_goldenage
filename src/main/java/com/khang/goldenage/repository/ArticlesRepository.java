package com.khang.goldenage.repository;

import com.khang.goldenage.modal.Articles;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
  List<Articles> findAllByOrderByDateTimeDesc();

  boolean existsByLink(String link);

  @Query(value = "SELECT * FROM articles WHERE UPPER(title) LIKE CONCAT('%', UPPER(:query), '%') OR UPPER(description) LIKE CONCAT('%', UPPER(:query), '%')", nativeQuery = true)
  List<Articles> findArticlesByKeyword(@Param("query") String query);

  @Query(value = "SELECT DISTINCT title FROM articles WHERE UPPER(title) LIKE CONCAT('%', UPPER(:query), '%') OR UPPER(description) LIKE CONCAT('%', UPPER(:query), '%')", nativeQuery = true)
  List<String> findTitlesForSearchSuggestions(@Param("query") String query, Pageable pageable);
}
