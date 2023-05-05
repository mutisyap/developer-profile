package ke.co.pmutisya.profile.service;

import ke.co.pmutisya.profile.domain.Article;
import ke.co.pmutisya.profile.domain.enumeration.ArticleStatus;
import ke.co.pmutisya.profile.domain.enumeration.ArticleType;
import ke.co.pmutisya.profile.repository.ArticleRepository;
import ke.co.pmutisya.profile.service.dto.ArticleDTO;
import ke.co.pmutisya.profile.service.mapper.ArticleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    private final Logger log = LoggerFactory.getLogger(ArticleService.class);

    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    // save
    public Article save(Article article) {
        log.debug("Request to save article : {}", article);
        if (article.getId() == null) {
            article.setCreatedOn(LocalDate.now().toString());
            article.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        return articleRepository.save(article);
    }

    public Article save(ArticleDTO articleDTO) {
        log.debug("Request to save article : {}", articleDTO);
        if (articleDTO.getId() == null) {
            setArticleId(articleDTO, 0);
            articleDTO.setCreatedOn(LocalDate.now().toString());
        }
        Article article = articleMapper.toEntity(articleDTO);
        return articleRepository.save(article);
    }

    private void setArticleId(ArticleDTO articleDTO, Integer trials){
        if (trials > 5) {
            throw new RuntimeException("Failed to generate article id");
        }
        // random number between 30 and 40
        int stringLength = (int) (Math.random() * 10 + 30);
        String articleTitle = articleDTO.getTitle().replaceAll(" ", "-").toLowerCase();
        int random = Math.min(stringLength, articleTitle.length());
        String articleId = articleTitle.substring(0, random);
        articleDTO.setId(articleId);

        articleRepository.findById(articleId).ifPresent(
            article -> {
                setArticleId(articleDTO, trials + 1);
            }
        );
    }

    // partial update
    public Article partialUpdate(Article article, String id) {
        log.debug("Request to partially update article : {}", article);
        Article dbArticle = findOne(id).orElseThrow(() -> new EntityNotFoundException("Article with id " + id + " does not exist"));

        dbArticle.patch(article);

        return articleRepository.save(dbArticle);
    }

    // findAll
    public List<Article> findAll() {
        log.debug("Request to get all articles");
        return articleRepository.findAll();
    }

    public List<Article> filter() {
        log.debug("Request to get all articles");
        return articleRepository.findAll();
    }

    // findOne
    public Optional<Article> findOne(String id) {
        log.debug("Request to get article : {}", id);
        return articleRepository.findById(id);
    }

    // delete
    public void delete(String id) {
        log.debug("Request to delete article : {}", id);
        articleRepository.deleteById(id);
    }

    // findAll
    public Page<Article> findByTag(String tag, ArticleType type, ArticleStatus status, Pageable pageable) {
        log.debug("Request to get all articles for tag : {}, type : {}, status : {}", tag, type, status);

        Article article = new Article();
        article.setTag(tag);
        article.setType(type);
        article.setStatus(status);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return articleRepository.findAll(Example.of(article, matcher), pageable);
    }
}
