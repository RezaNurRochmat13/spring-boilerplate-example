package com.spring.boilerplate.service;

import com.spring.boilerplate.entity.Article;
import com.spring.boilerplate.exception.ResourceNotFound;
import com.spring.boilerplate.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findAllArticles() {
        return articleRepository.findAllActiveArticles();
    }

    @Override
    public Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Article not found with id " + id));
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Long id, Article article) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Article not found with id " + id));

        existingArticle.setTitle(article.getTitle());
        existingArticle.setDescription(article.getDescription());
        existingArticle.setContent(article.getContent());
        existingArticle.setAuthor(article.getAuthor());

        return articleRepository.save(existingArticle);
    }

    @Override
    public void deleteArticle(Long id) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Article not found with id " + id));

        existingArticle.setDeletedAt(LocalDateTime.now());
        articleRepository.save(existingArticle);
    }
}
