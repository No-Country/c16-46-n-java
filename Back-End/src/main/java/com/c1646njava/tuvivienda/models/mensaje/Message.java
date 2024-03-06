package com.c1646njava.tuvivienda.models.mensaje;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String remitente;
    private String destinatario;
    private String contenido;
}
