package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.model.entity.CartItem;
import ra.model.entity.Product;
import ra.model.entity.User;
import ra.model.dao.cartItem.CartItemDaoIMPl;
import ra.model.dao.product.ProductDaoIMPL;
import ra.model.util.Config;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping({"/", "/IndexHomeController"})
public class IndexHomeController {
    ProductDaoIMPL productServiceIMPL = new ProductDaoIMPL();

    @GetMapping({"/", "/index"})
    public String indexWithNotLogin(Model model) {
        List<Product> list = productServiceIMPL.findAll();
        model.addAttribute("list", list);
        return "index";
//        return "quanlydonhang";
    }



    @GetMapping("/indexLogin")
    public String indexWithLogin(Model model, HttpServletRequest request) {
        List<Product> list = productServiceIMPL.findAll();

        CartItemDaoIMPl cartItemService = new CartItemDaoIMPl();

        User user = (User) request.getSession().getAttribute("userLogin");
        float sum = cartItemService.total(user.getId());
        List<CartItem> cartItemList = cartItemService.findCartByCartId(user.getId());
        model.addAttribute("total", Config.currencyFormatter.format(sum));
        model.addAttribute("list", list);
        model.addAttribute("username",user.getUserName());
        model.addAttribute("cartItemList",cartItemList);
        return "indexLogin";
    }

    @GetMapping("/search")
    public String search(@RequestParam("searchName") String name, Model model){
        List<Product> list = productServiceIMPL.findBYName(name);
        model.addAttribute("list", list);
        return "index";
    }
    @GetMapping("/searchByName")
    public String searchByName(@RequestParam("searchName") String name, Model model){
        List<Product> list = productServiceIMPL.findBYName(name);
        model.addAttribute("list", list);
        return "indexLogin";
    }
}
