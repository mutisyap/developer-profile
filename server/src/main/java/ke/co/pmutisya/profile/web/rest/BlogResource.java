package ke.co.pmutisya.profile.web.rest;

import ke.co.pmutisya.profile.service.dto.BlogDTO;
import ke.co.pmutisya.profile.util.FakeSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ke.co.pmutisya.profile.domain.Blog;
import ke.co.pmutisya.profile.service.BlogService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ke.co.pmutisya.profile.domain.Blog}.
 */
@RestController
@RequestMapping("/api")
public class BlogResource {

    private final Logger log = LoggerFactory.getLogger(BlogResource.class);
     
    private final BlogService blogService;

    public BlogResource(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blogs")
    public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO)
            throws URISyntaxException {
        log.debug("REST request to save blog : {}", blogDTO);

        Optional<String> authorOptional = FakeSecurityUtil.resolveAuthor(blogDTO.getSecurityCode());
        authorOptional.ifPresent(blogDTO::setAuthor);

        if (authorOptional.isPresent()) {
            BlogDTO result = blogService.save(blogDTO);
            return ResponseEntity
                    .created(new URI("/api/blogs/" + result.getId()))
                    .body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<BlogDTO> updateBlog(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody BlogDTO blogDTO
    ) throws URISyntaxException {
        log.debug("REST request to update blog : {}, {}", id, blogDTO);

        Optional<String> authorOptional = FakeSecurityUtil.resolveAuthor(blogDTO.getSecurityCode());
//        authorOptional.ifPresent(blogDTO::setAuthor);
        if (authorOptional.isPresent()) {
            BlogDTO result = blogService.save(blogDTO);
            return ResponseEntity
                    .ok()
                    .body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * {@code GET  /blogs} : get all the blog.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blog in body.
     */
    @GetMapping("/blogs")
    public List<BlogDTO> getAllBlogs() {
        log.debug("REST request to get all Blogs");
        return blogService.findAll();
    }

    /**
     * {@code GET  /blogs/:id} : get the "id" blog.
     *
     * @param id the id of the blog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blogs/{id}")
    public ResponseEntity<BlogDTO> getBlog(@PathVariable Long id) {
        log.debug("REST request to get blog : {}", id);
        Optional<BlogDTO> blogOptional = blogService.findOne(id);
        return blogOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * {@code DELETE  /blogs/:id} : delete the "id" blog.
     *
     * @param id the id of the blog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        log.debug("REST request to delete blog : {}", id);
        blogService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
