package codingon.condongospringboot.controller._01_thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PersonController {
    @GetMapping("/people")
    public String getPeople(Model model){

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person(20, "김철수"));
        personList.add(new Person(25, "김탁구"));
        personList.add(new Person(18, "김주원"));
        personList.add(new Person(35, "황시목"));
        personList.add(new Person(43 ,"한여진"));
        

        model.addAttribute("personList",personList);
        return "_01_thymeleaf/people";

    }


}

@Getter
@Setter
@AllArgsConstructor // 모든 필드 값을 매개변수로 받는 생성자
class Person{
    private int age;
    private String name;
}
