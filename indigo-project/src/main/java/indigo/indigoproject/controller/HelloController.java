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

        try {
            response.setToken(memberservice.join(member));
            response.setSuccess(true);
        }
        catch(Exception e){
            response.setSuccess(false);
            response.setToken("Duplicate ID");
        }

        return response;
    }

    @GetMapping("get_video")
    @ResponseBody
    public VideoInfo getVideoApi(@RequestParam("number") String number){
        VideoInfo response = new VideoInfo();
        int n = Integer.parseInt(number);
        String[] urls = {"https://www.youtube.com/embed/Q3rZyswPArE", "https://www.youtube.com/embed/OzkJqjFwcyc", "https://www.youtube.com/embed/JcPhyzLDaQA", "https://www.youtube.com/embed/A06sJ4moRmA", "https://www.youtube.com/embed/BZWtYXFqewM", "https://www.youtube.com/embed/wklQdfUPt0g", "https://www.youtube.com/embed/E8V02ArInjY", "https://www.youtube.com/embed/6aa567C89pA", "https://www.youtube.com/embed/GdJwvZGpOXA", "https://www.youtube.com/embed/qJ4w6pYh5LU"};

        response.setUrl(urls[n]);

        return response;
    }

    static class VideoInfo{
        private String url;

        public String getUrl(){
            return url;
        }

        public void setUrl(String url){
            this.url = url;
        }
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
