package ke.co.pmutisya.profile.domain;

import ke.co.pmutisya.profile.domain.enumeration.ArticleStatus;
import ke.co.pmutisya.profile.domain.enumeration.ArticleType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "articles")
public class Article {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "brief")
    private String brief;

    @Column(name = "markdown")
    private String markdown;

    @Column(name = "author")
    private String author;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "article_type")
    @Enumerated(EnumType.STRING)
    private ArticleType type;

    @Column(name = "article_status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column(name = "article_order")
    private Integer order;

    @Column(name = "tag")
    private String tag;

    @Column(name = "created_on")
    private String createdOn;

    public void patch(Article otherArticle){
        if(otherArticle.getTitle() != null){
            this.title = otherArticle.getTitle();
        }

        if(otherArticle.getBrief() != null){
            this.brief = otherArticle.getBrief();
        }

        if(otherArticle.getMarkdown() != null){
            this.markdown = otherArticle.getMarkdown();
        }

        if(otherArticle.getAuthor() != null){
            this.author = otherArticle.getAuthor();
        }

        if(otherArticle.getImageURL() != null){
            this.imageURL = otherArticle.getImageURL();
        }

        if(otherArticle.getType() != null){
            this.type = otherArticle.getType();
        }

        if(otherArticle.getStatus() != null){
            this.status = otherArticle.getStatus();
        }

        if(otherArticle.getOrder() != null){
            this.order = otherArticle.getOrder();
        }

        if(otherArticle.getTag() != null){
            this.tag = otherArticle.getTag();
        }
    }
}
