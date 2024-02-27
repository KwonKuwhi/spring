package codingon.codingonspringbootmybatis.controller;

import codingon.codingonspringbootmybatis.domain.Post;
import codingon.codingonspringbootmybatis.dto.PostDTO;
import codingon.codingonspringbootmybatis.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/post")
    public String getPost(Model model){
        // 1. 전부 select
        List<PostDTO> list = postService.getAll();
        model.addAttribute("list",list);
        return "post";
    }
    @PostMapping("/post")
    @ResponseBody
    public void postBoard(@RequestBody Post post){
        // 2. 게시글 작성 - insert
        postService.insertPost(post);
    }

    @PatchMapping("/post")
    @ResponseBody
    public void patchPost(@RequestBody Post post){
        // 3. 게시글 수정 - update
        postService.patchPost(post);
    }

    @DeleteMapping("/post")
    @ResponseBody
    public void deletePost(@RequestParam int id){
        // 4. 게시글 삭제 - delete
        postService.deletePost(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public int searchPost(@RequestParam String word){
        // 5. 게시글 검색 - get
        return postService.searchPost(word);
    }
}
