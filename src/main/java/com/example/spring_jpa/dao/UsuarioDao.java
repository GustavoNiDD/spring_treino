package com.example.spring_jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.spring_jpa.models.Usuario;

@Component
public class UsuarioDao {

    @Autowired
    JdbcTemplate db;

    public List<Usuario> getUsuarios() {
        String sql = "SELECT * FROM usuario";
        return db.query(sql, (res, rowNum) -> new Usuario(
                res.getInt("id"),
                res.getString("nome_conta"),
                res.getString("senha"),
                res.getString("email"),
                res.getDate("data_nascimento"),
                res.getString("genero")));
    }

    public void addUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome_conta, senha, email, data_nascimento, genero) VALUES (?, ?, ?, ?, ?)";
        db.update(sql, usuario.getNomeConta(), usuario.getSenha(), usuario.getEmail(), usuario.getDataNascimento(),
                usuario.getGenero());
    }

    public void deleteUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        db.update(sql, id);
    }

    public void editarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome_data = ?, senha = ?, email = ?, data_nascimento = ?, genero = ? WHERE id = ?";
        db.update(sql, usuario.getNomeConta(), usuario.getSenha(), usuario.getEmail(), usuario.getDataNascimento(),
                usuario.getGenero(), usuario.getId());
    }
}
