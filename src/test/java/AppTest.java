import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest  {

    @Test
    public void assertJ_assertThat() {

        int rs = 10 + 20;

        assertThat(rs).isEqualTo(30);
    }

    @Test
    public void ObjectMapper__articleDtoToJsonStr() {

        ArticleDto articleDto = new ArticleDto(1, "제목", "내용");

        String jsonStr = Ut.json.toStr(articleDto, "");

        assertThat(jsonStr).isNotBlank();

        assertThat(jsonStr).isEqualTo("""
                {"id":1,"title":"제목","body":"내용"}
                """.trim());

    }

    @Test
    public void ObjectMapper__articleDtoListToJsonStr() {

        ArticleDto articleDto1 = new ArticleDto(1, "제목1", "내용1");
        ArticleDto articleDto2 = new ArticleDto(2, "제목2", "내용2");

        List<ArticleDto> articleDtoList = new ArrayList<>();
        articleDtoList.add(articleDto1);
        articleDtoList.add(articleDto2);

        String jsonStr = Ut.json.toStr(articleDtoList, "");

        assertThat(jsonStr).isEqualTo("""
                [{"id":1,"title":"제목1","body":"내용1"},{"id":2,"title":"제목2","body":"내용2"}]
                """.trim());

    }

    @Test
    public void ObjectMapper__articleDtoMapToJsonStr() {

        ArticleDto articleDto1 = new ArticleDto(1, "제목1", "내용1");
        ArticleDto articleDto2 = new ArticleDto(2, "제목2", "내용2");

        Map<String, ArticleDto> articleDtoMap = new HashMap();
        articleDtoMap.put("가장오래된", articleDto1);
        articleDtoMap.put("최신", articleDto2);

        String jsonStr = Ut.json.toStr(articleDtoMap, "");

        System.out.println("jsonStr = " + jsonStr);
        assertThat(jsonStr).isEqualTo("""
                {"가장오래된":{"id":1,"title":"제목1","body":"내용1"},"최신":{"id":2,"title":"제목2","body":"내용2"}}
                """.trim());

    }

    @Test
    public void ObjectMapper__jsonStrToObj() {

        ArticleDto articleDtoOrigin = new ArticleDto(1, "제목", "내용");

        String jsonStr = Ut.json.toStr(articleDtoOrigin, "");

        ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);

        assertThat(articleDtoFromJson).isEqualTo(articleDtoOrigin);

    }

    @Test
    void ObjectMapper__jsonStrToArticleDtoList() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        articleDtos.add(new ArticleDto(1, "제목1", "내용1"));
        articleDtos.add(new ArticleDto(2, "제목2", "내용2"));

        String jsonStr = Ut.json.toStr(articleDtos, "");

        List<ArticleDto> articleDtosFromJson = Ut.json.toObj(jsonStr, new TypeReference<>() {
        }, null);

        assertThat(articleDtosFromJson).isEqualTo(articleDtos);
    }

    @Test
    public void ObjectMapper__jsonStrToArticleDtoMap() {

        ArticleDto articleDto1 = new ArticleDto(1, "제목1", "내용1");
        ArticleDto articleDto2 = new ArticleDto(2, "제목2", "내용2");

        Map<String, ArticleDto> articleDtoMap = new HashMap();
        articleDtoMap.put("가장오래된", articleDto1);
        articleDtoMap.put("최신", articleDto2);

        String jsonStr = Ut.json.toStr(articleDtoMap, "");

        Map<String, ArticleDto> articleDtoMapFromJson = Ut.json.toObj(jsonStr, new TypeReference<>() {
        }, null);

        assertThat(articleDtoMapFromJson).isEqualTo(articleDtoMap);

    }

    @Test
    void mapOf() {
        Map<String, Object> map = Ut.mapOf("age", 11, "name", "Paul");

        assertThat(map.get("age")).isEqualTo(11);
        assertThat(map.get("name")).isEqualTo("Paul");
    }
}
