package codingon.condongospringboot.controller._01_thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AgeController {
    @GetMapping("/age")
    public String getAge(Model model){
        model.addAttribute("age","19");
        model.addAttribute("age2","25");
        return "_01_thymeleaf/age";
    }



}


