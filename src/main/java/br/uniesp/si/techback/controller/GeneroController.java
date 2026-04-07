package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Genero;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/generos")
public class GeneroController {
    private static List<Genero> lista = new ArrayList<>();
    //
    @PostMapping
    public Genero PostMapping(Genero genero){
            lista.add(genero);
        return genero;
    }
    @GetMapping
    public List<Genero> listar(){
        return lista;
    }
   @PutMapping
   public String PutMapping(){
        return "PUT para inclusao de genero... v  ";
   }
   @DeleteMapping
    public String DeleteMapping(){
        return "DELETE para inclusao de genero... v  ";
   }


}
