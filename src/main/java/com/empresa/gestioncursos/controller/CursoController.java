package com.empresa.gestioncursos.controller;

import com.empresa.gestioncursos.entity.Curso;
import com.empresa.gestioncursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String home(){
        return "redirect:/cursos";
    }

    @GetMapping("/cursos")
    public String listarCurosos(Model model){
        List<Curso> cursos = cursoRepository.findAll();
        System.out.println(cursos);
        cursos = cursoRepository.findAll();
        System.out.println(cursos);
        model.addAttribute("cursos", cursos);
        return "cursos";
    }

    @GetMapping("/cursos/nuevo")
    public String agregarCurso(Model model){
        Curso curso = new Curso();
        curso.setPublicado(true);
        model.addAttribute("curso", curso);
        model.addAttribute("pageTitle", "Nuevo Curso");
        return "curso_form";
    }

    @PostMapping("/cursos/save")
    public String guardarCurso(Curso curso, RedirectAttributes redirectAttributes){
        try{
            cursoRepository.save(curso);
            redirectAttributes.addFlashAttribute("message", "El curso a sido guardado con éxito");
        }catch (Exception e){
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/cursos";
    }

    @GetMapping("/cursos/{id}")
    public String editarCurso(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        try{
            Curso curso = cursoRepository.findById(id).get();
            model.addAttribute("pageTitle", "Editar Curso" + id);
            model.addAttribute("curso", curso);
            redirectAttributes.addFlashAttribute("message", "El curso a sido actualizado con éxito");

            return "curso_form";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cursos";
    }

    @GetMapping("/cursos/delete/{id}")
    public String eliminarCurso(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes){
        try{
            cursoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Curso eliminado con éxito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/cursos";
    }

}
