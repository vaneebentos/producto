package project.productos_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.productos_backend.repositories.ArquitecturaRepository;
import project.productos_backend.repositories.BajadaRepository;
import project.productos_backend.Exceptions.ResourceNotFoundException;
import project.productos_backend.models.Arquitectura;
import project.productos_backend.models.Bajada;

@RestController

@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ArquitecturaController {

    @Autowired
    private ArquitecturaRepository arquitecturarepository;

    // este metodo sirve para listar todos las arquitectura
    @GetMapping("/arquitecturas")
    public List<Arquitectura> listarTodosLasArquitectura() {
        return arquitecturarepository.findAll();
    }

    // este metodo sirve para guardar las Arquitecturas
    @PostMapping("/arquitecturas")
    public Arquitectura guardarArquitectura(@RequestBody Arquitectura arquitectura){
        return arquitecturarepository.save(arquitectura);
    }

    // este metodo sirve para buscar una Arquitectura por id
    @GetMapping("/arquitecturas/{id}")
    public ResponseEntity<Arquitectura> obtenerArquitecturaporId(@PathVariable  Long id){
        Arquitectura arquitectura = arquitecturarepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe la arquitectura con el ID : " + id));
        return ResponseEntity.ok(arquitectura);
    }

    @PutMapping ("/arquitecturas/{id}")
    public ResponseEntity<Arquitectura> actualizarArquitecturaporId(@PathVariable  Long id, @RequestBody Arquitectura detallesArquitectura){
        Arquitectura arquitectura= arquitecturarepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe la Arquitectura con el ID : " + id));
       
        arquitectura.setNombre(detallesArquitectura.getNombre());     
        arquitectura.setDetalle(detallesArquitectura.getDetalle()); 
        arquitectura.setPrecio(detallesArquitectura.getPrecio());      
                  
        Arquitectura arquitecturaActualizada = arquitecturarepository.save(arquitectura);
        return ResponseEntity.ok(arquitecturaActualizada);
    }

  @DeleteMapping("/arquitecturas/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarArquitectura(@PathVariable Long id){
		Arquitectura arquitectura = arquitecturarepository.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe la Arquitectura con el ID : " + id));
		
		arquitecturarepository.delete(arquitectura);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
    
}
