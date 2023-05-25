package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.*;
import ra.model.dao.cartItem.CartItemDaoIMPl;
import ra.model.dao.product.ProductDaoIMPL;

import ra.model.util.Config;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping( "/cartItemController")
public class CartItemController {
    CartItemDaoIMPl cartItemService = new CartItemDaoIMPl();
    ProductDaoIMPL productServiceIMPL = new ProductDaoIMPL();
    @GetMapping("/getAll")
    public String showAllViewCart(Model model,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("userLogin");
        if (user == null){
            return "redirect:/userController/form-login";
        }
        CartItemDaoIMPl cartItemService = new CartItemDaoIMPl();
        List<CartItem> list = cartItemService.findCartByCartId(user.getId());
        float sum = cartItemService.total(user.getId());
        model.addAttribute("total", Config.currencyFormatter.format(sum));
        model.addAttribute("cartItemList", list);
        model.addAttribute("user",user.getUserName());
        return "shoppingCart";
    }
    @GetMapping("/create")
    public String addTocart(@RequestParam("productId") String productId,
                             HttpServletRequest request){
       User user= (User) request.getSession().getAttribute("userLogin");
       CartItem cartItem = cartItemService.checkExistProduct(productId, user.getId());
       boolean check = true;
       List<CartItem> list = cartItemService.findCartByCartId(user.getId());
        for (CartItem cartItem1:list) {
            if (cartItem1.getProductId().equals(productId)){
                check = false;
            }
        }
        if (check){
            Product product =productServiceIMPL.findById(productId);
            CartItem cartItem1 =new CartItem(1,product.getId(),product.getImage(),product.getName(), user.getId(), product.getPrice());
            cartItemService.save(cartItem1);
        } else {
            cartItemService.setQuantity(productId);
        }
        return "redirect:/cartItemController/getAll";
    }

    @GetMapping("/update")
    public String update(@RequestParam("idUp") String idUp,
                         @RequestParam("quantity") String quantity,
                         @RequestParam("price") String price){
         cartItemService.totalCartItemPrice(Integer.parseInt(idUp), Integer.parseInt(quantity));
        return "redirect:/cartItemController/getAll";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam String idDel,Model model,HttpServletRequest request){
        cartItemService.delete(Integer.valueOf(idDel));
        return showAllViewCart( model,request);
    }
}
