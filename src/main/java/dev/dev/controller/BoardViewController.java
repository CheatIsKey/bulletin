package dev.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardViewController {

    @GetMapping
    public String listView() {
        return "board/list";
    };

    @GetMapping("/new")
    public String newView() {
        return "board/new";
    };

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        model.addAttribute("postId", id);
        return "board/detail";
    };

}
