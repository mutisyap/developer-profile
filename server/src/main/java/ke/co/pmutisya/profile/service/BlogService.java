package ke.co.pmutisya.profile.service;

import ke.co.pmutisya.profile.domain.Blog;
import ke.co.pmutisya.profile.repository.BlogRepository;
import ke.co.pmutisya.profile.service.dto.BlogDTO;
import ke.co.pmutisya.profile.service.mapper.BlogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final BlogMapper blogMapper;


    public BlogService(BlogRepository blogRepository, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
    }

    /**
     * Save a blog.
     *
     * @param blogDTO the blog to save.
     * @return the persisted blog.
     */
    public BlogDTO save(BlogDTO blogDTO) {
        log.debug("Request to save blog : {}", blogDTO);

        Blog blog = blogMapper.toEntity(blogDTO);
        blog = blogRepository.save(blog);

        return blogMapper.toDto(blog);
    }


    /**
     * Get all the blogs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BlogDTO> findAll() {
        log.debug("Request to get all blogs");
        return blogMapper.toDto(blogRepository.findAll());
    }

    /**
     * Get one blog by id.
     *
     * @param id the id of the blog.
     * @return the blog.
     */
    @Transactional(readOnly = true)
    public Optional<BlogDTO> findOne(Long id) {
        log.debug("Request to get blog : {}", id);
        return blogRepository.findById(id).map(blogMapper::toDto);
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

