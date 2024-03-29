package codingon.codingonspringbootmybatis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostDTO {
    private String no;
    private int id;
    private String title;
    private String content;
    private String writer;
    private String registered;
}
