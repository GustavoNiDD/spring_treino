package com.example.spring_jpa.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome_conta", unique = true, nullable = false)
    private String nomeConta;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;
    @Column(name = "genero", nullable = false)
    private String genero;
    
}
