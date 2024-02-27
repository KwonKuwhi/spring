package codingon.codingonspringbootmybatis.mapper;

import codingon.codingonspringbootmybatis.domain.Post;
import codingon.codingonspringbootmybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> getAll();
    void insertPost(Post post);
    void patchPost(Post post);
    void deletePost(int id);

    List<Post> searchPost(String word);
}
