package ke.co.pmutisya.profile.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ke.co.pmutisya.profile.domain.Blog;

/**
 * Spring Data SQL repository for the Notification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {}
