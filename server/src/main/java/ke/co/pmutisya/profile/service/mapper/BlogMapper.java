package ke.co.pmutisya.profile.service.mapper;

import ke.co.pmutisya.profile.domain.Blog;
import ke.co.pmutisya.profile.service.dto.BlogDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogMapper extends EntityMapper<BlogDTO, Blog>{

}
