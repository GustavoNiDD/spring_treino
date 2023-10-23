package com.example.spring_jpa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_jpa.dao.UsuarioDao;
import com.example.spring_jpa.models.Usuario;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioDao dao;

    @GetMapping(value = "/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @GetMapping(value = "/listaUsuarios")
    public ModelAndView listaUsuarios() {
        ModelAndView mv = new ModelAndView("listaUsuarios");
        mv.addObject("usuarios", dao.getUsuarios());
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping(value = "/cadastrarUsuario")
    public ModelAndView paginaCadastro() {
        ModelAndView mv = new ModelAndView("cadastrarUsuario");
        mv.addObject("usuarios", dao.getUsuarios());
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping(value = "/cadastrarUsuario")
    public ModelAndView cadastrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        dao.addUsuario(usuario);
        return listaUsuarios();
    }

    @GetMapping(value = "/deletarUsuario/{id}")
    public ModelAndView deletarUsuario(@PathVariable("id") int id) {
        dao.deleteUsuario(id);
        return listaUsuarios();
    }

    @PostMapping(value = "/editarUsuario/{id}")
    public ModelAndView editarUsuario(@PathVariable("id") int id, @ModelAttribute("usuario") Usuario usuario) {
        Usuario usuarioBox = dao.getUsuarioById(id);
        usuarioBox.setNomeConta(usuario.getNomeConta());
        usuarioBox.setEmail(usuario.getEmail());
        usuarioBox.setSenha(usuario.getSenha());
        usuarioBox.setDataNascimento(usuario.getDataNascimento());
        usuarioBox.setGenero(usuario.getGenero());
        dao.editarUsuario(usuarioBox);
        return listaUsuarios();
    }

    @GetMapping(value = "/busca")
    public ModelAndView buscar(@RequestParam("search") int id) {
        ModelAndView mv = new ModelAndView("listaUsuarios");
        mv.addObject("usuarios", dao.getUsuarioById(id));
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping(value = "/buscaNome")
    public ModelAndView buscarNome(@RequestParam("search") String nome) {
        ModelAndView mv = new ModelAndView("listaUsuarios");
        mv.addObject("usuarios", dao.getUsuarioByNomeConta(nome));
        mv.addObject("usuario", new Usuario());
        return mv;
    }
}