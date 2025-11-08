package hsf302.f25.s3.firstexam.controller;

import hsf302.f25.s3.firstexam.entity.Account;
import hsf302.f25.s3.firstexam.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class AuthenticationController {
    private final AccountService accountService;

    @GetMapping({"/login","/"})
    public String showLogin(){
        return "login";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/auth")
    public String doLogin(@RequestParam String phone,
                          @RequestParam String password,
                          HttpSession session, Model model){

        Account account = accountService.authenticate(phone, password);
        if(account == null) {
            model.addAttribute("error", "Invalid phone or password!");
            return "login";
        }
        if(!accountService.isAdminOrStaff(account)){
            model.addAttribute("error", "You do not have permission to access this function!");
        }
        session.setAttribute("account", account);
        return "redirect:/products";
    }
}
