
package com.springai.gemini.controller;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.FragmentsRendering;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class ChatController {

    private static final Logger log =
            LoggerFactory.getLogger(ChatController.class);

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(20)
                .build();

        this.chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory)
                                .build()
                )
                .build();
    }

    @GetMapping("")
    public String home() {
        return "index";
    }

    @HxRequest
    @PostMapping("/api/chat")
    public View generate(
            @RequestParam String message,
            Model model) {

        log.info("User Message: {}", message);

        String conversationId = "test-conversation";

        String response = chatClient.prompt()
                .user(message)
                .advisors(a -> a.param(
                        ChatMemory.CONVERSATION_ID,
                        conversationId
                ))
                .call()
                .content();

        log.info("Gemini Response: {}", response);

        model.addAttribute("response", response);
        model.addAttribute("message", message);

        return FragmentsRendering.with("response :: responseFragment")
                .build();
    }
}