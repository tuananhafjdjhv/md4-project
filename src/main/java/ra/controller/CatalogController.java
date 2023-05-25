package ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.entity.Catalog;
import ra.model.dao.catalog.CatalogDaoIMPL;

import java.util.List;

@Controller
@RequestMapping( "/catalogController")
public class CatalogController {
    CatalogDaoIMPL catalogServiceIMPL = new CatalogDaoIMPL();

    @GetMapping({ "/getAll"})
    public String getAll(Model model) {
        List<Catalog> list = catalogServiceIMPL.findAll();
        model.addAttribute("list", list);
        return "catalog";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, Model model) {
        catalogServiceIMPL.delete(id);
        return getAll(model);
    }

    @GetMapping("/create")
    public String create(@RequestParam("id") String id, @RequestParam("name") String name) {
        Catalog newCatalog = new Catalog(id, name);
        catalogServiceIMPL.save(newCatalog);
        return "redirect:getAll";

    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam String id, Model model) {
        Catalog editCatalog = catalogServiceIMPL.findById(id);
        ModelAndView mv = new ModelAndView("updateCatalog");
        mv.addObject("catalog", editCatalog);
        model.addAttribute("catalog", editCatalog);
        return mv;
    }

    @PostMapping("/update")
    public String update(@RequestParam String id, @RequestParam String name) {
        Catalog updateCatalog = new Catalog(id, name);
        catalogServiceIMPL.update(updateCatalog);
        return "redirect:/catalogController/getAll";
    }
    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute("catalog") Catalog catalog){
        return new ModelAndView("createCatalog","newCatalog",new Catalog());
    }
}
