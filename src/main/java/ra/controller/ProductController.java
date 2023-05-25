package ra.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.Product;
import ra.model.dao.product.ProductDaoIMPL;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping( "/productController")
@PropertySource("classpath:upload-file.properties")
public class ProductController {
    @Value("${file-upload}")
    private String fileUpload;
    ProductDaoIMPL productServiceIMPL = new ProductDaoIMPL();

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<Product> lists = productServiceIMPL.findAll();
        model.addAttribute("list", lists);
        return "table-data-product";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("newProduct", new Product());
        return "createProduct";
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam String id, Model model) {
        Product editProduct = productServiceIMPL.findById(id);
        ModelAndView mv = new ModelAndView("updateProduct");
        mv.addObject("product", editProduct);
        model.addAttribute("product", editProduct);
        return mv;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product,
                         @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        product.setImage(fileName);
        productServiceIMPL.update(product);
        return "redirect:/productController/getAll";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("newProduct") Product product, @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        product.setImage(fileName);
        productServiceIMPL.save(product);
        return "redirect:getAll";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, Model model) {
        productServiceIMPL.delete(id);
        return getAll(model);
    }
}

