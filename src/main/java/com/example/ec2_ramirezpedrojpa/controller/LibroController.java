/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ec2_ramirezpedrojpa.controller;

import com.example.ec2_ramirezpedrojpa.entity.Autor;
import com.example.ec2_ramirezpedrojpa.entity.Editorial;
import com.example.ec2_ramirezpedrojpa.entity.Libro;
import com.example.ec2_ramirezpedrojpa.repository.AutorRepository;
import com.example.ec2_ramirezpedrojpa.repository.EditorialRepository;
import com.example.ec2_ramirezpedrojpa.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author usuario
 */
@Controller
public class LibroController {

    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private EditorialRepository editorialRepository;
    
    @Autowired
    private LibroRepository libroRepository;

    @RequestMapping("/libs")
    public String libs(Model model) {
        model.addAttribute("libs", libroRepository.findAll());
        return "libs";
    }
    @RequestMapping("/create")
    public String createauto(Model model) {
        model.addAttribute("autos", autorRepository.findAll());
        model.addAttribute("edits",editorialRepository.findAll());
        return "addLibro";
    } 
    @RequestMapping("/libroadd")
    public String guardarlibro(@RequestParam String nombre, @RequestParam int idautor,@RequestParam int ideditorial, Model model) {
        Libro lib = new Libro();
        Autor auto = autorRepository.findById(idautor).get();
        Editorial edito = editorialRepository.findById(ideditorial).get();
        lib.setNombre(nombre);
        lib.setAutor(auto);
        lib.setEditorial(edito);
        libroRepository.save(lib);
        return "redirect:/libs";
    }
    @RequestMapping("/dellibro/{id}")
    public String deletelibro(@PathVariable(value="id") int id) {
        Libro lib = libroRepository.findById(id).orElse(null);
        libroRepository.delete(lib);
        return "redirect:/libs";
    }
    
    @RequestMapping("/editlibro/{id}")
    public String edit(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("autos", autorRepository.findAll());
        model.addAttribute("edits", editorialRepository.findAll());
        model.addAttribute("lib", libroRepository.findById(id).orElse(null));
        return "editlibro";
    }
    @RequestMapping("/updatelibro")
    public String update(@RequestParam int id, @RequestParam String nombre, @RequestParam int autor, @RequestParam int editorial ) {
        Libro lib = libroRepository.findById(id).orElse(null);
        Autor auto = autorRepository.findById(autor).get();
        Editorial edito = editorialRepository.findById(editorial).get();
        lib.setNombre(nombre);
        lib.setAutor(auto);
        lib.setEditorial(edito);
        libroRepository.save(lib);
        return "redirect:/libs";
    }
}
