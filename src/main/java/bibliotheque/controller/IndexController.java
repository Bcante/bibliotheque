package bibliotheque.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public ModelAndView getHome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

}
