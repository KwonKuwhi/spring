package codingon.condongospringboot.vo;


import lombok.Getter;

import java.util.Objects;

@Getter
public class User2VO {
    private String name;
    private String gender;
    private String year;
    private String month;
    private String date;
    private String interest;





    @Override
    public int hashCode() {
        // 필드들을 이용한 해시코드 생성
        return Objects.hash(name, gender);
    }
}
