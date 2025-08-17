package dev.dev.controller;

import dev.dev.domain.FindIdForm;
import dev.dev.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AccountRecoveryController {

    private final UserService userService;

    @GetMapping("/forgot-id")
    public String forgotIdForm(Model model) {
        model.addAttribute("form", new FindIdForm());
        return "auth/forgot-id";
    }

    @PostMapping("/forgot-id")
    public String findLoginId(@Valid @ModelAttribute("form") FindIdForm form,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/forgot-id";
        }
        String masked = userService.findLoginIdByNameAndPhone(form.getName(), form.getPhone());
        model.addAttribute("maskedLoginId", masked);
        return "auth/forgot-id-result";
    }
}
