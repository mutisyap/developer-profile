package ke.co.pmutisya.profile.service.dto;

import ke.co.pmutisya.profile.domain.enumeration.ArticleStatus;
import ke.co.pmutisya.profile.domain.enumeration.ArticleType;
import lombok.Data;

@Data
public class ArticleDTO {
    private String id;
    private String title;
    private String brief;
    private String markdown;
    private String author;
    private String imageURL;
    private ArticleType type;
    private ArticleStatus status;
    private Integer order;
    private String tag;
    private String createdOn;
    // others
    private String securityCode;

    public void patch(ArticleDTO otherArticle) {
        if (otherArticle.getTitle() != null) {
            this.title = otherArticle.getTitle();
        }

        if (otherArticle.getBrief() != null) {
            this.brief = otherArticle.getBrief();
        }

        if (otherArticle.getMarkdown() != null) {
            this.markdown = otherArticle.getMarkdown();
        }

        if (otherArticle.getAuthor() != null) {
            this.author = otherArticle.getAuthor();
        }

        if (otherArticle.getImageURL() != null) {
            this.imageURL = otherArticle.getImageURL();
        }

        if (otherArticle.getType() != null) {
            this.type = otherArticle.getType();
        }

        if (otherArticle.getStatus() != null) {
            this.status = otherArticle.getStatus();
        }

        if (otherArticle.getOrder() != null) {
            this.order = otherArticle.getOrder();
        }

        if (otherArticle.getTag() != null) {
            this.tag = otherArticle.getTag();
        }
    }
}
