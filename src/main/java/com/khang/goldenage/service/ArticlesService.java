package com.khang.goldenage.service;

import com.khang.goldenage.modal.Articles;
import com.khang.goldenage.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class ArticlesService {
  @Autowired
  private ArticlesRepository articlesRepository;

  @Transactional(readOnly = true)
  public List<Articles> getArticles() {
    return articlesRepository.findAllByOrderByDateTimeDesc();
  }

  public Optional<Articles> getArticlesById(Long id) {
    return articlesRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Articles> searchArticle(String keyword) {
    if (keyword == null || keyword.trim().isEmpty()) {
      return articlesRepository.findAllByOrderByDateTimeDesc();
    }
    // CHUYỂN KEYWORD SANG CHỮ HOA TRƯỚC KHI TRUYỀN VÀO REPOSITORY
    return articlesRepository.findArticlesByKeyword(keyword);
  }

  @Transactional(readOnly = true)
  public List<String> getSuggestions(String query) {
    if (query == null || query.trim().isEmpty() || query.length() < 2) {
      return Collections.emptyList();
    }
    Pageable topSuggestions = PageRequest.of(0, 10);

    // CHUYỂN QUERY SANG CHỮ HOA TRƯỚC KHI TRUYỀN VÀO REPOSITORY
    return articlesRepository.findTitlesForSearchSuggestions(query, topSuggestions);
  }

}
