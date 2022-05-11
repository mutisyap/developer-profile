package ke.co.pmutisya.profile.service.dto;

import ke.co.pmutisya.profile.domain.enumeration.ArticleStatus;
import ke.co.pmutisya.profile.domain.enumeration.ArticleType;
import lombok.Data;

@Data
public class BlogDTO {
    private Long id;

    private String metaImageURL;

    private String title;

    private String brief;

    private String articleMd;

    private String author;

    private Integer views;

    private Integer claps;

    private ArticleType type;

    private ArticleStatus status;

    private String articleId;

    private String tags;

    private String createdOn;

    private String createdBy;

    private String lastUpdatedOn;

    private String lastUpdatedBy;

    // others
    private String securityCode;
}
