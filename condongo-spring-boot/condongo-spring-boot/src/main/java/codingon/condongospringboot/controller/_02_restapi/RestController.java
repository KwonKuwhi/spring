package codingon.condongospringboot.controller._02_restapi;

import codingon.condongospringboot.dto.UserDTO;
import codingon.condongospringboot.vo.User2VO;
import codingon.condongospringboot.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class RestController {
    // ================= Template 렌더링 ==================
    @GetMapping("/")
    public String getReq() {
        return "_02_restapi/req";

    }

    // ============= Get 요청 ==============
    // 매개변수 넘겨받는 방법
    // 1. /test?id=123
    // 2. /test/123

    @GetMapping("/get/res1")
    public String getRes1(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age, Model model) {
        //@RequestParam 어노테이션
        // -  string query 중에서 name key에 대한 value를 String name에 매핑(?key=value)
        // - required=true 가 기본 값으므로 요청 URL에서 name key를 필수로 보내야 함
        // ex. GET /get/res1?name=someone
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/res";
    }

    @GetMapping("/get/res2")
    public String getRes2(@RequestParam(value = "name", required = false) String name, Model model) {
        // required = false 옵션
        // - query string에서 특정 key를 optional하게 받아야 하는 경우
        // ex. 검색어(필수), 해시태그 (선택)
        // - ?search = 바나나
        // - ?search = 바나나/&hashtag=과일
        model.addAttribute("name", name);
        return "_02_restapi/res";
    }

    @GetMapping("/get/res3/{param1}/{param2}")
    public String getRes3(@PathVariable String param1, @PathVariable(value = "param2") int age, Model model) {
        // @PathVariable 어노테이션
        // - test/{id} 형식의 URL 경로로 넘어오는 값을 변수로 받을 때 사용
        // - 기본적으로 경로 변수는 값을 가져야 함 (값이 없는 경우는 404 에러 발생)
        // 참고, uri에 기입한 변수명과 다른 매개변수 이름을 사용하고 싶다면
        // - @PathVariable int age
        // -> @PathVariable(value="param2") int age 이런 식으로 사용해야함 (uri랑 동일한 매개변수 사용한다면 생략)

        model.addAttribute("name", param1);
        model.addAttribute("age", age);

        return "_02_restapi/res";
    }

    @GetMapping({"/get/res4/{name}", "/get/res4/{name}/{age}"}) // 선택적으로 받아오는 PathVariable이 있다면 경로를 여러개 설쩡해야함
    public String getRes4(@PathVariable String name, @PathVariable(required = false) Integer age, Model model) {
        // required = false 옵션
        // - name (필수), age (선택)
        // - optional한 parameter 가 있다면 맨 뒤에 오도록 설정
        // 참고. Integer age 라고 한 이유
        // - age는 optional한 값. 즉, null 이 될 수도 있기 때문에 primitive type 이 아닌 refernece type 인 래퍼 객체 사용
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/res";
    }


    @GetMapping("/introduce/{name}")
    public String getIntroduce(@PathVariable String name , Model model) {
        model.addAttribute("name", name);
        return "_02_restapi/introduce";
    }


    @GetMapping("/introduce2")
    public String getIntroduce2(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age, Model model) {

        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "_02_restapi/introduce2";
    }

// ============= Post 요청 ==============
    // - Post 로 전달할 때 Controller 에서 받는 방법은 @RequestParam
    @PostMapping("/post/res1")
    public String postRes1(@RequestParam String name, @RequestParam int age, Model model){
        model.addAttribute("name",name);
        model.addAttribute("age",age);
        return "_02_restapi/res";
    }

    @PostMapping("/post/res2")
    public String postRes2(@RequestParam(required = false) String name,  Model model){
        model.addAttribute("name",name);
        return "_02_restapi/res";
    }


    // 여기까지의 코드는 return이 항상 Template View! 하지만 API 에서 데이터 자체를 응답하고 싶다면?
    // => @ResponseBody 어노테이션 사용!

    @PostMapping("/post/res3")
    @ResponseBody
    public String postRes3(@RequestParam String name, @RequestParam int age,  Model model){
        //@ResponseBody 어노테이션
         // - 응답시 객체를 JSON으로 리턴할 때 사용(직렬화, serialize)
        //  - 즉, 응답 객체를 전달(express의 res.send와 유사)
        model.addAttribute("name",name);
        model.addAttribute("age",age);
        //return "_02_restapi/res"; // 템플릿 엔진 리턴이 아니라 문자열 그 자체를 응답
        return  name + " " + age;
    }


    @GetMapping("/postPrac")
    public String getPostPractice() {
        return "_02_restapi/postPrac";
    }


    @PostMapping("/post/prac")
    public String postPrac(@RequestParam String name, @RequestParam String gender, @RequestParam String year,@RequestParam String month,@RequestParam String date ,@RequestParam String[] interest, Model model){
        model.addAttribute("name",name);
        model.addAttribute("gender",gender);
        model.addAttribute("year",year);
        model.addAttribute("month",month);
        model.addAttribute("date",date);
        model.addAttribute("interest", interest);
        return "_02_restapi/formResult";
    }


    // ================== DTO 이용 ==================
    // 1. Get 요청
    @GetMapping("/dto/res1")
    @ResponseBody
    public String dtoRes1(@ModelAttribute UserDTO userDTO){
        // 변수로 값을 하나씩 가져오는 것이 아니라 DTO 객체에 값을 담아서 가져오기
        // @ModelAttribute : HTML 폼 데이터를 컨트롤러로 전달할 때 객체 매핑하는 어노테이션
        // -> 매핑: setter 함수 실행
        // ex. ?name=홍길동&age=20 -> setName(), setAge() 실행
        // Lombok plugin 설치해야 에러 안남
        // - 왜 에러? 롬복은 애플리케이션 실행 후에 getter, setter를 생성해 줌.
        // 즉, 이 시점에는 getter 가 없다고 판단해서 에러가 남

        return userDTO.getName() + " " + userDTO.getAge();

    }

    // 2. Post 요청
    @PostMapping("/dto/res2")
    @ResponseBody
    public String dtoRes2(UserDTO userDTO){
        // @ModelAttribute 어노테이션이 없을 때에는 자동 추가됨 (생략 가능)
        return userDTO.getName() + " " + userDTO.getAge();
    }


    @PostMapping("/dto/res3")
    @ResponseBody
    public String dtoRes3(@RequestBody UserDTO userDTO){
        // @RequestBody 어노테이션
        // - 요청의 본문에 있는 데이터 (req.body)를 읽어와서 객체에 매핑
        // - 여기서 매핑? 필드 값에 값을 주입
        // - 전달 받은 요청의 형식이 json 또는 xml 일때만 실행 가능
        // POST / dto/res3  요청의 경우 "일반 폼 전송" 임 (222-x-form-urlendcoded).
        // => @RequestBody 어노테이션 사용 시 오류 발생함


        return userDTO.getName() + " " + userDTO.getAge();
    }// x : // error (type=Unsupported Media Type, status=415).

    // ============== VO 이용 ==============

    @GetMapping("/vo/res1")
    @ResponseBody
    public String voRes1(@ModelAttribute UserVO userVO){
        System.out.println(userVO.hashCode()); //961
        // @ModelAttribute 를 이용하면 객체의 set 함수를 이용해 값을 넣어줌
        return userVO.getName() + " " + userVO.getAge();
    } // o (null, null)


    @PostMapping("/vo/res2")
    @ResponseBody
    public String voRes2(UserVO userVO){
//        return userVO.getName() + " " + userVO.getAge();

        String msg = "이름 : " + userVO.getName() + " 나이 : " + userVO.getAge();
        return msg;
    } // o (null, null)

    @PostMapping("/vo/res3")
    @ResponseBody
    public String voRes3(@RequestBody UserVO userVO){
        return userVO.getName() + " " + userVO.getAge();
    } //  x : // error (type=Unsupported Media Type, status=415).

    // ============ DTO 이용 with axios =============
    @GetMapping("/axios/res1")
    @ResponseBody
    public String axiosRes1(@RequestParam String name, @RequestParam String age){
        return "이름 : " + name + ", 나이: " + age;
    } // o

    @GetMapping("/axios/res2")
    @ResponseBody
    public String axiosRes2(UserDTO userDTO){
        return "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    } // o

    @PostMapping("/axios/res3")
    @ResponseBody
    public String axiosRes3(@RequestParam String name, @RequestParam String age){
        // @RequestParam required 기본값이 true
        // axios로 값을 전달하게 될 경우 파라미터로 값이 들어오지 않는다. (Post로 보냈을 때)
        // 값이 들어오지 않는데 기본값이 true이기 떄문에 오류

        return "이름 : " + name + ", 나이: " + age;
    } // x (error 400)

    @PostMapping("/axios/res4")
    @ResponseBody
    public String axiosRes4(UserDTO userDTO) {
        return "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    } // 0 (null)

    @PostMapping("/axios/res5")
    @ResponseBody
    public String axiosRes5(@RequestBody UserDTO userDTO) {
        return "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    } // 0


    // ============ VO 이용 with axios =============
    @GetMapping("/axios/vo/res1")
    @ResponseBody
    public String axiosVORes1(@RequestParam String name, @RequestParam String age){
        return "이름 : " + name + ", 나이: " + age;
    } // o

    @GetMapping("/axios/vo/res2")
    @ResponseBody
    public String axiosVORes2(UserVO userVO ){
       //@ModelAttribute 생략
        // setter 함수를 실행해서 값을 넣어주기 떄문에 null
        // UserVO에는 setter 없음
        return "이름 : " + userVO.getName() + ", 나이: " + userVO.getAge();
    } // o (null)

    @PostMapping("/axios/vo/res3")
    @ResponseBody
    public String axiosVORes3(@RequestParam String name, @RequestParam String age){
        // @RequestParam required 기본값이 true
        // axios로 값을 전달하게 될 경우 파라미터로 값이 들어오지 않는다. (Post로 보냈을 때)
        // 값이 들어오지 않는데 기본값이 true이기 떄문에 오류

        return "이름 : " + name + ", 나이: " + age;
    } // x (error 400)

    @PostMapping("/axios/vo/res4")
    @ResponseBody
    public String axiosVORes4(UserVO userVO) {
        return "이름 : " + userVO.getName() + ", 나이: " + userVO.getAge();
    } // 0 (null)

    @PostMapping("/axios/vo/res5")
    @ResponseBody
    public String axiosVORes5(@RequestBody UserVO userVO) {
        // @RequestBody 로 값을 전달할 때 userVO에 setter 함수가 없어도 값이 들어간다.
        // setter 함수 실행이 아니라 각각의 필드(변수)에 직접적으로 값을 주입하면서 매핑
        // @ModelAttribute 가 setter 함수를 실행해 값을 넣어준다면,
        // @RequestBody 는 각각의 필드에 직접 주입
        return "이름 : " + userVO.getName() + ", 나이: " + userVO.getAge();
    } // 0
    ///////////////////////////실습//////////////////////////////////


        @GetMapping("/register")
        public String getRegister() {
            return "_02_restapi/voPrac";

        }



    @PostMapping("/axios/vo/register")
    @ResponseBody
    public String Register(@RequestBody User2VO user2VO) {
        // @RequestBody 로 값을 전달할 때 userVO에 setter 함수가 없어도 값이 들어간다.
        // setter 함수 실행이 아니라 각각의 필드(변수)에 직접적으로 값을 주입하면서 매핑
        // @ModelAttribute 가 setter 함수를 실행해 값을 넣어준다면,
        // @RequestBody 는 각각의 필드에 직접 주입
        return user2VO.getName() + "님 회원가입 성공!" ;
    } // 0
}
