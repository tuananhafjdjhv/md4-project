package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.User;
import ra.model.dao.user.UserServiceIMPL;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/userController")
public class UserController {
    UserServiceIMPL userService = new UserServiceIMPL();

    @GetMapping("/form-login")
    public String formLogin() {
        return "login";
    }

    @GetMapping("/form-register")
    public String formRegister() {
        return "register";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("list", list);
        return "userManagement";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
        User user = userService.login(new User(username, password));
        if (user == null) {
            model.addAttribute("login", "Username or password incorrect");
            return "login";
        } else {
            if (!user.isUserStatus()){
                model.addAttribute("login","Tài khoản đã bị khóa ");
                return "login";
            }
            request.getSession().setAttribute("userLogin", user);
            if (user.getUserName().equals("admin") && user.getPassword().equals("admin")) {
                return "redirect:/productController/getAll";
            } else {
                return "redirect:/IndexHomeController/indexLogin";
            }
        }
    }


    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (userService.checkUsernameExists(username)) {
            model.addAttribute("message", "User Name  đã tồn tại !!");
            return "register";
        } else
        if (userService.checkEmailExists(email)) {
            model.addAttribute("message", "Email  đã tồn tại !!");
            return "register";
        } else if (username.trim().length() < 8 || password.trim().length() < 8){
            model.addAttribute("message", "Tên đăng nhập và pass word phải trên 8 kí tự !!");
            return "register";
        } else

            userService.save(new User(username, email, password));
            return "redirect:/userController/form-login";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userlogin");
        return "redirect:/IndexHomeController/index";
    }
    @GetMapping("/block-user")
    public String blockUser(@RequestParam("id") int id){
        userService.blockUser(id);
        return "redirect:/userController/getAll";
    }
    @GetMapping("/un-block-user")
    public String unBlockUser(@RequestParam("id") int id){
        userService.unBlockUser(id);
        return "redirect:/userController/getAll";
    }
}
