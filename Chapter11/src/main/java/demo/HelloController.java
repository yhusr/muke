package demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }
    public static void main(String[] args) {
        SpringApplication.run(HelloController.class,args);
    }

}
