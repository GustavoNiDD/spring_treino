package com.example.spring_jpa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring_jpa.dao.UsuarioDao;
import com.example.spring_jpa.models.Usuario;

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

    @GetMapping(value = "/editarUsuario/{id}")
    public ModelAndView editarUsuario(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("cadastrarUsuario");
        Optional<Usuario> usuario = dao.getUsuarios().stream().filter(u -> u.getId() == id).findFirst();
        if (usuario.isPresent()) {
            mv.addObject("usuario", usuario.get());
        }
        return mv;
    }

}
