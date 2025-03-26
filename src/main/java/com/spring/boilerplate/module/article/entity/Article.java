package com.spring.boilerplate.module.article.entity;

import com.spring.boilerplate.utils.entity.Auditing;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
public class Article extends Auditing implements Serializable {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;
}
