package hsf302.f25.s3.firstexam.controller;

import hsf302.f25.s3.firstexam.entity.Product;
import hsf302.f25.s3.firstexam.service.CategoryService;
import hsf302.f25.s3.firstexam.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    @GetMapping
    public String getAllProducts(Model model, HttpSession session) {
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("products", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("formMode", "update");
        return "product-form";
    }

    @GetMapping("/create/{id}")
    public String createProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("formMode", "update");
        return "product-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("products") Product product,
                        BindingResult bindingResult, Model model, @RequestParam("mode") String formMode) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("formMode", formMode);
            return "product-form";
        }
        productService.save(product);
        return "redirect:/products";
    }
}
