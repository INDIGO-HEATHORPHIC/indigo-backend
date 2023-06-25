package indigo.indigoproject.controller;

import indigo.indigoproject.domain.Member;
import indigo.indigoproject.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    private final MemberService memberservice = new MemberService();

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    @GetMapping("create-user")
    @ResponseBody
    public UserInfo createUserApi(@RequestParam("id") String id, @RequestParam("password") String password){
        UserInfo response = new UserInfo();
        Member member = new Member();

        member.setId(id);
        member.setPassword(password);

        response.setToken(memberservice.join(member));
        response.setSuccess(true);

        return response;
    }

    static class UserInfo{
        private boolean success;
        private String token;

        UserInfo(){}

        UserInfo(boolean success, String token){
            this.success = success;
            this.token = token;
        }

        public boolean getSuccess(){return success;}

        public void setSuccess(Boolean success){this.success = success;}

        public String getToken(){return token;}

        public void setToken(String token){this.token = token;}
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
