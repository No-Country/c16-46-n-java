package com.c1646njava.tuvivienda.controllers;

import com.c1646njava.tuvivienda.models.chatMessage.ChatMessage;
import com.c1646njava.tuvivienda.models.chatMessage.ChatMessageHistory;
import com.c1646njava.tuvivienda.models.chatMessage.MessageType;
import com.c1646njava.tuvivienda.repositories.ChatMessageHistoryRepository;
import com.c1646njava.tuvivienda.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatMessageHistoryRepository chatMessageHistoryRepository;
    private final UserRepository userRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public void sendMessage(@Payload ChatMessage chatMessage){
        ChatMessageHistory chatMessageHistory = new ChatMessageHistory();
        chatMessageHistory.setContent(chatMessage.getContent());
        chatMessageHistory.setSender(chatMessage.getSender());
        chatMessageHistory.setRecipient(chatMessage.getRecipient());
        chatMessageHistory.setTimestamp(LocalDateTime.now());
        chatMessageHistoryRepository.save(chatMessageHistory);

        // Enviar mensaje al destinatario
        simpMessageSendingOperations.convertAndSendToUser(String.valueOf(chatMessage.getRecipient()), "/queue/private", chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Obtener el ID del remitente y del destinatario
        Long senderId = chatMessage.getSender();
        Long recipientId = chatMessage.getRecipient();
        // Agregar el usuario y su sesión al headerAccessor

        headerAccessor.getSessionAttributes().put("userId", senderId);


        // Enviar un mensaje de "JOIN" a todos los clientes
        ChatMessage joinMessage = ChatMessage.builder()
                .type(MessageType.JOIN)
                .sender(senderId)
                .recipient(recipientId)
                .build();
        simpMessageSendingOperations.convertAndSend("/topic/public", joinMessage);

        // Cargar mensajes del historial y enviar al cliente
        List<ChatMessageHistory> chatHistory = chatMessageHistoryRepository.findChatHistoryBySenderAndRecipient(
                senderId, recipientId);
        for (ChatMessageHistory message : chatHistory) {
            simpMessageSendingOperations.convertAndSendToUser(String.valueOf(senderId), "/queue/private", message);
        }
    }

}
