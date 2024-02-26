package codingon.codingonspringbootmybatis.mapper;

import codingon.codingonspringbootmybatis.domain.Post;
import codingon.codingonspringbootmybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostMapper {
    @Select("select * from post")
    void selectPost(Post post);

    @Insert("insert into post(title, content, writer) values(#{title}, #{content}, #{writer})")
    void createPost(Post post);
}
