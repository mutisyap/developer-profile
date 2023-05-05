package ke.co.pmutisya.profile.service.mapper;

import ke.co.pmutisya.profile.domain.Article;
import ke.co.pmutisya.profile.service.dto.ArticleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

}
