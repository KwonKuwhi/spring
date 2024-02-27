package codingon.codingonspringbootmybatis.service;

import codingon.codingonspringbootmybatis.domain.Post;
import codingon.codingonspringbootmybatis.dto.PostDTO;
import codingon.codingonspringbootmybatis.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostService {
    @Autowired
    PostMapper postMapper;

    public List<PostDTO> getAll(){
        //getAll() 메소드는 "select * from post" sql을 실행하는 함수
        List<Post> result =  postMapper.getAll();
        List<PostDTO> list = new ArrayList<>();

        for (Post post : result){
            PostDTO postDTO = PostDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                            .writer(post.getWriter())
                    .registered(post.getRegistered())
                    .no(post.getWriter() + post.getId())
                    .build();

            list.add(postDTO);
        }
        return list;
    }

    public void insertPost(Post post){
        postMapper.insertPost(post);
    }

    public void patchPost(Post post){
        postMapper.patchPost(post);
    }

    public void deletePost(int id){
        postMapper.deletePost(id);
    }

    public int searchPost(String word){
        // select 문 자체를 count로 동작시킬 수도 있고,
        // List로 받아와서 그에 대한 길이를 전달할 수도 있다
        List<Post> result = postMapper.searchPost(word);
        return result.size();
    }
}
