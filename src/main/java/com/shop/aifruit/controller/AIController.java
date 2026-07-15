package com.shop.aifruit.controller;

import com.shop.aifruit.service.AIKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fruit/ai")
public class AIController {

    @Autowired
    private AIKnowledgeService aiKnowledgeService;

    @GetMapping
    public String aiPage(Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));
        @SuppressWarnings("unchecked")
        List<Map<String, String>> history = (List<Map<String, String>>) session.getAttribute("chatHistory");
        if (history == null) {
            history = new ArrayList<>();
        }
        model.addAttribute("chatHistory", history);
        return "ai-chat";
    }

    @PostMapping("/ask")
    @ResponseBody
    public Map<String, String> ask(@RequestBody Map<String, String> request, HttpSession session) {
        String question = request.get("question");
        String answer = aiKnowledgeService.query(question);

        @SuppressWarnings("unchecked")
        List<Map<String, String>> history = (List<Map<String, String>>) session.getAttribute("chatHistory");
        if (history == null) {
            history = new ArrayList<>();
        }

        Map<String, String> userMsg = new HashMap<>();
        userMsg.put("role", "user");
        userMsg.put("content", question);
        history.add(userMsg);

        Map<String, String> aiMsg = new HashMap<>();
        aiMsg.put("role", "ai");
        aiMsg.put("content", answer);
        history.add(aiMsg);

        session.setAttribute("chatHistory", history);

        Map<String, String> response = new HashMap<>();
        response.put("answer", answer);
        return response;
    }

    @GetMapping("/clear")
    public String clearHistory(HttpSession session) {
        session.removeAttribute("chatHistory");
        return "redirect:/fruit/ai";
    }
}