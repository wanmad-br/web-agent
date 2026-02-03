package com.homelab.webagent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebAgentApplication {

    // 1. Definimos o cliente como uma ferramenta fixa da classe
    private final ChatClient chatClient;

    // 2. Construtor: O Spring entrega o "Builder" e nós criamos o cliente aqui
    public WebAgentApplication(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebAgentApplication.class, args);
    }

    // 3. O Endpoint usa o cliente da classe, não pede ele nos argumentos
    @GetMapping("/perguntar")
    public String perguntar(@RequestParam(value = "msg", defaultValue = "Me conte uma curiosidade") String mensagem) {
        return chatClient.prompt()
                .user(mensagem)
                .call()
                .content();
    }
}