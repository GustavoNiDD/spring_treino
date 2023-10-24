package com.example.spring_jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "/buscaAll")
    public ModelAndView buscarAll(@RequestParam("search") String all) {
        all = all.trim();
        ModelAndView mv = new ModelAndView("listaUsuarios");
        if (all.isEmpty()) {
            mv.addObject("usuarios", dao.getUsuarios());
        } else {
            char c = all.charAt(0);
            if (Character.isDigit(c)) {
                mv.addObject("usuarios", dao.getUsuarioByAll(Integer.parseInt(all)));
            } else {
                mv.addObject("usuarios", dao.getUsuarioByAll(all));
            }
        }

        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping(value = "/buscaMasculino")
    public ModelAndView buscarGeneroM(@RequestParam("search") String all) {
        all = all.trim();
        ModelAndView mv = new ModelAndView("listaUsuarios");
        if (all.isEmpty()) {
            mv.addObject("usuarios", dao.getUsuariosByGenero("MASCULINO"));
        } else {
            char c = all.charAt(0);
            if (Character.isDigit(c)) {
                mv.addObject("usuarios", dao.getUsuarioByGenero(Integer.parseInt(all), "MASCULINO"));
            } else {
                mv.addObject("usuarios", dao.getUsuarioByGenero(all, "MASCULINO"));
            }
        }

        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping(value = "/buscaFeminino")
    public ModelAndView buscarGeneroF(@RequestParam("search") String all) {
        all = all.trim();
        ModelAndView mv = new ModelAndView("listaUsuarios");
        if (all.isEmpty()) {
            mv.addObject("usuarios", dao.getUsuariosByGenero("FEMININO"));
        } else {
            char c = all.charAt(0);
            if (Character.isDigit(c)) {
                mv.addObject("usuarios", dao.getUsuarioByGenero(Integer.parseInt(all), "FEMININO"));
            } else {
                mv.addObject("usuarios", dao.getUsuarioByGenero(all, "FEMININO"));
            }
        }

        mv.addObject("usuario", new Usuario());
        return mv;
    }
}