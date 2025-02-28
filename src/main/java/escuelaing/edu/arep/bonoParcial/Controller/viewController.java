package escuelaing.edu.arep.bonoParcial.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import escuelaing.edu.arep.bonoParcial.model.Property;

@Controller
@RequestMapping("/")

public class viewController {

    @GetMapping("home")
    public String home(){
        return "inmobiliaria";
    }

    
}
