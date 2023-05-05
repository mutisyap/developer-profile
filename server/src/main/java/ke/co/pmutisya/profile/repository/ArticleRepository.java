package ke.co.pmutisya.profile.repository;

import ke.co.pmutisya.profile.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    Page<Article> findByTagContainingIgnoreCase(String tag, Pageable pageable);
}
