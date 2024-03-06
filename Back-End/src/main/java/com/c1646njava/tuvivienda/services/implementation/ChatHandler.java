package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Service
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final UserRepository userRepository;
    private final Map<Long, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private final Map<Long, WebSocketSession> adminSessions = new ConcurrentHashMap<>();
    private final Map<Pair<Long, Long>, List<WebSocketSession>> userAdminChats = new ConcurrentHashMap<>();
    private final Map<Pair<Long, Long>, List<WebSocketSession>> adminUserChats = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Principal principal = session.getPrincipal();
        Long userId = Long.valueOf(principal.getName()); // Obtener directamente el ID del usuario
        Optional<User> optionalUser = userRepository.findById(userId); // Suponiendo que tienes un repositorio para los usuarios
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user instanceof Administrator) {
                // El usuario es un administrador
                adminSessions.put(userId, session);
            } else {
                // El usuario no es un administrador
                userSessions.put(userId, session);
            }
        } else {
            throw new Exception("The user with ID " + userId + " was not found.");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Simply remove the closed session from the list of active sessions
        Long userIdToRemove = null;
        for (Map.Entry<Long, WebSocketSession> entry : userSessions.entrySet()) {
            if (entry.getValue().equals(session)) {
                userIdToRemove = entry.getKey();
                break;
            }
        }
        if (userIdToRemove != null) {
            userSessions.remove(userIdToRemove);
        }

        Long adminIdToRemove = null;
        for (Map.Entry<Long, WebSocketSession> entry : adminSessions.entrySet()) {
            if (entry.getValue().equals(session)) {
                adminIdToRemove = entry.getKey();
                break;
            }
        }
        if (adminIdToRemove != null) {
            adminSessions.remove(adminIdToRemove);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Principal principal = session.getPrincipal();
        Long userId = Long.valueOf(principal.getName()); // Obtener directamente el ID del usuario
        Optional<User> optionalUser = userRepository.findById(userId); // Suponiendo que tienes un repositorio para los usuarios
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user instanceof Administrator) {
                // Manejar el mensaje del administrador
                // Obtener el ID del usuario con el que se está comunicando el administrador
                Long userToChatId = // Lógica para obtener el ID del usuario con el que el administrador está chateando
                Pair<Long, Long> chatKey = Pair.of(userId, userToChatId);
                List<WebSocketSession> userSessions = adminUserChats.getOrDefault(chatKey, new ArrayList<>());
                for (WebSocketSession userSession : userSessions) {
                    userSession.sendMessage(message);
                }
            } else {
                // Manejar el mensaje del usuario
                // Obtener el ID del administrador con el que se está comunicando el usuario
                Long adminToChatId = // Lógica para obtener el ID del administrador con el que el usuario está chateando
                Pair<Long, Long> chatKey = Pair.of(adminToChatId, userId);
                List<WebSocketSession> adminSessions = userAdminChats.getOrDefault(chatKey, new ArrayList<>());
                for (WebSocketSession adminSession : adminSessions) {
                    adminSession.sendMessage(message);
                }
            }
        }
    }
}
