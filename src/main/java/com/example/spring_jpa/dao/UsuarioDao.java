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

    String nomeConta = "nome_conta";
    String senha = "senha";
    String email = "email";
    String dataNascimento = "data_nascimento";
    String genero = "genero";

    public List<Usuario> getUsuarios() {
        String sql = "SELECT * FROM usuario";
        return db.query(sql, (res, rowNum) -> new Usuario(
                res.getInt("id"),
                res.getString(nomeConta),
                res.getString(senha),
                res.getString(email),
                res.getDate(dataNascimento),
                res.getString(genero)));
    }

    public List<Usuario> getUsuariosByGenero(String generoz) {
        String sql = "SELECT * FROM usuario WHERE genero = ?";
        return db.query(sql, (res, rowNum) -> new Usuario(
                res.getInt("id"),
                res.getString(nomeConta),
                res.getString(senha),
                res.getString(email),
                res.getDate(dataNascimento),
                res.getString(genero)), generoz);
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
        String sql = "UPDATE usuario SET nome_conta = ?, senha = ?, email = ?, data_nascimento = ?, genero = ? WHERE id = ?";
        db.update(sql, usuario.getNomeConta(), usuario.getSenha(), usuario.getEmail(), usuario.getDataNascimento(),
                usuario.getGenero(), usuario.getId());
    }

    public Usuario getUsuarioById(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        return db.queryForObject(sql, (res, rowNum) -> new Usuario(
                res.getInt("id"),
                res.getString("nome_conta"),
                res.getString("senha"),
                res.getString("email"),
                res.getDate("data_nascimento"),
                res.getString("genero")), id);
    }

    public List<Usuario> getUsuarioByAll(Object all) {
        if (all instanceof Integer) {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            return db.query(sql, (res, rowNum) -> new Usuario(
                    res.getInt("id"),
                    res.getString(nomeConta),
                    res.getString(senha),
                    res.getString(email),
                    res.getDate(dataNascimento),
                    res.getString(genero)), all);
        } else if (all instanceof String && !all.equals("")) {
            String sql = "select * from usuario where nome_conta like concat('%', ?, '%') and nome_conta like concat(?, '%')";
            return db.query(sql, (res, rowNum) -> new Usuario(
                    res.getInt("id"),
                    res.getString(nomeConta),
                    res.getString(senha),
                    res.getString(email),
                    res.getDate(dataNascimento),
                    res.getString(genero)), all, all);
        } else {
            return null;
        }
    }

    public List<Usuario> getUsuarioByGenero(Object all, String generoz) {
        if (all instanceof Integer) {
            String sql = "SELECT * FROM usuario WHERE id = ? AND GENERO = ?";
            return db.query(sql, (res, rowNum) -> new Usuario(
                    res.getInt("id"),
                    res.getString(nomeConta),
                    res.getString(senha),
                    res.getString(email),
                    res.getDate(dataNascimento),
                    res.getString(genero)), all, generoz);
        } else if (all instanceof String && !all.equals("")) {
            String sql = "select * from usuario where nome_conta like concat('%', ?, '%') and nome_conta like concat(?, '%') AND GENERO = ?";
            return db.query(sql, (res, rowNum) -> new Usuario(
                    res.getInt("id"),
                    res.getString(nomeConta),
                    res.getString(senha),
                    res.getString(email),
                    res.getDate(dataNascimento),
                    res.getString(genero)), all, all,generoz);
        } else {
            return null;
        }
    }
}