package com.homelab.webagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RestController
public class WebAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAgentApplication.class, args);
    }

    // 1. Injeção do ChatClient (O Spring AI cria isso automaticamente usando suas properties)
    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    // 2. O Endpoint (A "URL" que vamos chamar no navegador)
    @GetMapping("/perguntar")
    String perguntar(@RequestParam(value = "msg", defaultValue = "Me conte uma curiosidade sobre Java") String mensagem, ChatClient client) {
        
        // Aqui o Java manda sua mensagem para a Groq e espera a resposta
        return client.prompt()
                .user(mensagem)
                .call()
                .content();
    }
}