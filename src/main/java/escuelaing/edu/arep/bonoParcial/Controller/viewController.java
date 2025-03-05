package escuelaing.edu.arep.bonoParcial.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {

    @GetMapping("/home")
    public String home(){
        return "inmobiliaria";
    }

    
}
