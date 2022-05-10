package ke.co.pmutisya.profile.domain;

import ke.co.pmutisya.profile.domain.enumeration.ArticleStatus;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tbl_blogs")
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String metaImageURL;

    @Column(length = 100)
    private String title;

    @Column(length = 500)
    private String brief;

    @Column(length = 15000)
    private String articleMd;

    @Column(length = 50)
    private String author;

    private Integer views;

    private Integer claps;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    private String articleId;

    @Column(length = 35)
    private String createdOn;

    @Column(length = 30)
    private String createdBy;

    @Column(length = 30)
    private String lastUpdatedOn;

    @Column(length = 30)
    private String lastUpdatedBy;

}
