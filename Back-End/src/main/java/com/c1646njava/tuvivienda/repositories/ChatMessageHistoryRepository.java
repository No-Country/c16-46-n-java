package com.c1646njava.tuvivienda.repositories;

import com.c1646njava.tuvivienda.models.chatMessage.ChatMessageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageHistoryRepository extends JpaRepository<ChatMessageHistory,Long> {
    @Query("SELECT c FROM ChatMessageHistory c WHERE c.sender = :senderId AND c.recipient = :recipientId")
    List<ChatMessageHistory> findChatHistoryBySenderAndRecipient(Long senderId, Long recipientId);

}
