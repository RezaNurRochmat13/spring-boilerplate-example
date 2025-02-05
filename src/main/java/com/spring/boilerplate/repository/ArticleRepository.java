package com.spring.boilerplate.repository;

import com.spring.boilerplate.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("select a from Article a where a.deletedAt is null")
    List<Article> findAllActiveArticles();
}
