package com.c1646njava.tuvivienda.controllers;

import com.c1646njava.tuvivienda.models.mensaje.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatContoller {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatContoller(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void onReceiveMessage(Message message) {
        // Envíe el mensaje al destinatario
        messagingTemplate.convertAndSendToUser(message.getDestinatario(), "/chat", message);
    }
}
