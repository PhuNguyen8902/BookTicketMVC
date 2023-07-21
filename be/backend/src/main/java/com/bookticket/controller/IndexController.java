/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookticket.controller;

//import com.bookticket.dto.AuthenticationRequest;
//import com.bookticket.dto.JWTResponse;
//import com.bookticket.pojo.Product;
//import com.bookticket.service.AuthenticationService;
//import com.bookticket.service.JwtService;
import com.bookticket.pojo.User;
import com.bookticket.service.JwtService;
import com.bookticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * // * @author ADMIN
 */
@Controller
//@RequestMapping("/")
@RequiredArgsConstructor

public class IndexController {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

//    @GetMapping("/")
//    @Transactional
//    public ResponseEntity<List<Product>> index(Model model) {
//        Session s = factory.getObject().getCurrentSession();
//        Query q = s.createQuery("FROM Product");
//        List<Product> listp = q.getResultList();
//        return new ResponseEntity<>(
//                listp,
//                HttpStatus.OK);
//    }
    @RequestMapping("/hello/{name}")
    public String hello(Model model, @PathVariable(value = "name") String name) {
        String token = name;
//        model.addAttribute("msg", "Welcome " + name + " !!!");
        model.addAttribute("msg", this.jwtService.getUsernameFromToken(token));
        return "hello";
    }

    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        String fn = params.get("fn");
        String ln = params.get("ln");
        if (fn != null && ln != null) {
            model.addAttribute("name", String.format("%s  %s", fn, ln));

        } else {
            model.addAttribute("name", "khong co gi");
        }
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(path = "/hello-post/", method = RequestMethod.POST)
    public String show(Model model, @ModelAttribute(value = "user") User user) {
        String email = user.getEmail();
        String password = user.getPassword();
//        if (this.userService.comparePassword(email, password)) {
//            model.addAttribute("full", this.jwtService.generateTokenLogin(email));
            model.addAttribute("full", "success");

//        } else {
//            model.addAttribute("full", "error");
//        }
        return "index";
    }

    @RequestMapping(path = "/test")
    public String testRedirect(Model model) {
        model.addAttribute("testRedirect", "Welcome redirect");
        return "forward:/hello/phu";
    }
}
