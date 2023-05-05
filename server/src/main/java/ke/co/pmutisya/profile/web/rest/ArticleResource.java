package ke.co.pmutisya.profile.web.rest;

import ke.co.pmutisya.profile.domain.Article;
import ke.co.pmutisya.profile.domain.enumeration.ArticleStatus;
import ke.co.pmutisya.profile.domain.enumeration.ArticleType;
import ke.co.pmutisya.profile.service.ArticleService;
import ke.co.pmutisya.profile.service.dto.ArticleDTO;
import ke.co.pmutisya.profile.util.FakeSecurityUtil;
import ke.co.pmutisya.profile.web.errors.UnauthorizedException;
import ke.co.pmutisya.profile.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArticleResource {
    private final Logger logger = LoggerFactory.getLogger(ArticleResource.class);

    private final ArticleService articleService;

    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDTO article) {
        logger.debug("REST request to save article : {}", article);

        String author = FakeSecurityUtil.resolveAuthor(article.getSecurityCode()).orElseThrow(() -> new UnauthorizedException("Invalid security code"));
        article.setAuthor(author);
        article.setCreatedOn(LocalDate.now().toString());

        Article result = articleService.save(article);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article, @PathVariable String id) {
        logger.debug("REST request to update article : {}", article);
        Article result = articleService.partialUpdate(article, id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        logger.debug("REST request to get all articles");
        return articleService.findAll();
    }

    @GetMapping("/articles/tag/{tag}")
    public ResponseEntity<List<Article>> getArticlesByTag(@PathVariable String tag,
                                                          @RequestParam(required = false) ArticleType type,
                                                          @RequestParam(required = false) ArticleStatus status,
                                                          Pageable pageable) {
        logger.debug("REST request to get all articles with tag : {}, type : {}, and status : {}", tag, type, status);
        Page<Article> page = articleService.findByTag(tag, type, status, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequestUri(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable String id) {
        logger.debug("REST request to get article : {}", id);
        Optional<Article> articleOptional = articleService.findOne(id);
        return articleOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
