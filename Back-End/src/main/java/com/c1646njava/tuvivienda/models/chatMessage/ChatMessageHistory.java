package com.c1646njava.tuvivienda.models.chatMessage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageHistory {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String content;
private Long sender;
private Long recipient;
private LocalDateTime timestamp;
}
