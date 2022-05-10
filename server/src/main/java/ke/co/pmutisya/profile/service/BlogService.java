package ke.co.pmutisya.profile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ke.co.pmutisya.profile.domain.Blog;
import ke.co.pmutisya.profile.repository.BlogRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Blog}.
 */
@Service
@Transactional
public class BlogService {

    private final Logger log = LoggerFactory.getLogger(BlogService.class);

    private final BlogRepository blogRepository;


    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /**
     * Save a blog.
     *
     * @param blog the blog to save.
     * @return the persisted blog.
     */
    public Blog save(Blog blog) {
        log.debug("Request to save blog : {}", blog);
        return blogRepository.save(blog);
    }


    /**
     * Get all the blogs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Blog> findAll() {
        log.debug("Request to get all blogs");
        return blogRepository.findAll();
    }

    /**
     * Get one blog by id.
     *
     * @param id the id of the blog.
     * @return the blog.
     */
    @Transactional(readOnly = true)
    public Optional<Blog> findOne(Long id) {
        log.debug("Request to get blog : {}", id);
        return blogRepository.findById(id);
    }

    /**
     * Delete the blog by id.
     *
     * @param id the id of the blog.
     */
    public void delete(Long id) {
        log.debug("Request to delete blog : {}", id);
        blogRepository.deleteById(id);
    }
}

