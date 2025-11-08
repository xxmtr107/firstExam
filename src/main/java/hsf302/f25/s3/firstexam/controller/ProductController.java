package hsf302.f25.s3.firstexam.controller;

import hsf302.f25.s3.firstexam.entity.Account;
import hsf302.f25.s3.firstexam.entity.Product;
import hsf302.f25.s3.firstexam.service.AccountService;
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
    private final AccountService accountService;
    @GetMapping
    public String getAllProducts(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null || !accountService.isStaff(account) || !accountService.isAdmin(account)) {
            return  "redirect:/login";
        }
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null || !accountService.isAdmin(account)) {
            return  "redirect:/login";
        }
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null || !accountService.isAdmin(account)) {
            return  "redirect:/login";
        }
        model.addAttribute("products", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("formMode", "update");
        return "product-form";
    }

    @GetMapping("/create")
    public String createProduct(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null || !accountService.isAdmin(account)) {
            return  "redirect:/login";
        }
        model.addAttribute("products", new Product());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("formMode", "create");
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

    @GetMapping("/top-stock")
    public String getTopStock(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account == null || accountService.isAdmin(account)){
            return "redirect:/login";
        }

        model.addAttribute("topProducts", productService.getTop3ProductsByStockPerCategory());
        return "top-stock";
    }
}
