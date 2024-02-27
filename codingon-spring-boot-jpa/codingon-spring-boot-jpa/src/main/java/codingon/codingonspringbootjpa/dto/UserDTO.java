package codingon.codingonspringbootjpa.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Builder
@Data
public class UserDTO {
    private int id;
    private  String name;
    private  String nickname;
    private int no;


}
