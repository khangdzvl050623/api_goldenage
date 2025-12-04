package com.khang.goldenage.controller;

import com.khang.goldenage.modal.Articles;
import com.khang.goldenage.modal.ExchangeRate;
import com.khang.goldenage.service.ArticlesService;
import com.khang.goldenage.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scrape")
public class ArticlesController {
  @Autowired
  private ArticlesService articlesService;


  @GetMapping("/history/{id}")
  public ResponseEntity<Articles> getArticlesHistoryById(@PathVariable("id") Long id) {
    Optional<Articles> articles = articlesService.getArticlesById(id);
    if (articles.isPresent()) {
      return ResponseEntity.ok(articles.get());
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @GetMapping("/history")
  public ResponseEntity<List<Articles>> getArticlesHistory() {
    return ResponseEntity.ok(articlesService.getArticles());
  }

  // Ví dụ: GET http://localhost:8383/api/scrape/search?q=keyword
  @GetMapping("search")
  public ResponseEntity<List<Articles>> getArticlesSearch(@RequestParam(value = "q", required = false) String keyword) {
    List<Articles> foundArticles = articlesService.searchArticle(keyword);
    return ResponseEntity.ok(foundArticles);
  }

  @GetMapping("/suggestions")
  public ResponseEntity<List<String>> getSuggestions(@RequestParam("q") String query) {
    List<String> suggestions = articlesService.getSuggestions(query);
    return ResponseEntity.ok(suggestions);

  }

}
