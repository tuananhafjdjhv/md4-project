package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.dao.oderManagement.OderManagementDaoIMPL;
import ra.model.dao.oderManagementDetail.OderDetailManagementDaoIMPL;
import ra.model.dao.user.UserServiceIMPL;
import ra.model.entity.*;
import ra.model.dao.cartItem.CartItemDaoIMPl;
import ra.model.dao.user.UserDetailServiceIMPL;
import ra.model.util.Config;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/checkOutController")
public class CheckOutController {
    CartItemDaoIMPl cartItemService = new CartItemDaoIMPl();
    UserDetailServiceIMPL userDetailServiceIMPL = new UserDetailServiceIMPL();

    @GetMapping("/check-out")
    public String checkOut(@ModelAttribute("newUserDetail") UserDetail userDetail, Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLogin");
        List<CartItem> list = cartItemService.findCartByCartId(user.getId());
        float sum = cartItemService.total(user.getId());
        if (sum == 0) {
            return "redirect:/IndexHomeController/indexLogin";
        }
        model.addAttribute("total", Config.currencyFormatter.format(sum));
        model.addAttribute("cartItemList", list);
        model.addAttribute("username", user);
        return "checkout";
    }

    @GetMapping("/oder-list")
    public String oderListWithAdmin(Model model, HttpServletRequest request) {
//        User user= (User) request.getSession().getAttribute("userLogin");
//        List<CartItem> list = cartItemService.findCartByCartId(user.getId());
        UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
        List<User> listUser = userServiceIMPL.findAll();
        OderManagementDaoIMPL oderManagementDaoIMPL = new OderManagementDaoIMPL();
        List<OderManagement> oderManagements = oderManagementDaoIMPL.findAll();
//        float sum = cartItemService.total(user.getId());
//        model.addAttribute("total", Config.currencyFormatter.format(sum));
//        model.addAttribute("cartItemList", list);
        model.addAttribute("user", listUser);
        model.addAttribute("listOder", oderManagements);
        return "quanlydonhang";
    }

    @PostMapping("/create")
    public String createUserDetail(@ModelAttribute("newUserDetail") UserDetail userDetail, Model model) {
        userDetailServiceIMPL.save(userDetail);
        return "redirect:/IndexHomeController/indexLogin";
    }

    @GetMapping("/show-list-oder")
    public String showListOder(@RequestParam String id, Model model) {
        OderDetailManagementDaoIMPL oderdetail = new OderDetailManagementDaoIMPL();
        List<OderDetailManagement> list = oderdetail.findByUserId(Integer.parseInt(id));
        model.addAttribute("listUser", list);
        LocalDate cu = LocalDate.now();
        model.addAttribute("date", cu);
        return "showOderDetail";
    }

    @GetMapping("/delete-oder")
    public String deleteCartItem(@RequestParam("id") String id) {
        cartItemService.deleteCartItemFindByUserId(Integer.parseInt(id));
        return "redirect:/show-list-oder";
    }
}
