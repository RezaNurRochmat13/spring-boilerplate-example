package com.spring.boilerplate.module.article.service;

import com.spring.boilerplate.module.article.entity.Article;
import com.spring.boilerplate.utils.exception.ResourceNotFound;
import com.spring.boilerplate.module.article.repository.ArticleRepository;
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
        if (article.getTitle() == null || article.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        if (article.getAuthor() == null || article.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author must not be empty");
        }
        if (article.getTitle().length() > 255) {
            throw new IllegalArgumentException("Title must not exceed 255 characters");
        }

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
