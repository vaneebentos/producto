package project.productos_backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.productos_backend.repositories.EscapeRepository;
import project.productos_backend.Exceptions.ResourceNotFoundException;
import project.productos_backend.models.Escape;

@RestController
@RequestMapping("/api/v1/")
public class EscapeController {

    @Autowired
    private EscapeRepository escaperepository;

    // este metodo sirve para listar todos los escapes
    @GetMapping("/escapes")
    public List<Escape> listarTodosLosEscapes() {
        return escaperepository.findAll();
    }

    // este metodo sirve para guardar los escapes
    @PostMapping("/escapes")
    public Escape guardarEscape(@RequestBody Escape escape){
        return escaperepository.save(escape);
    }

    

    // este metodo sirve para buscar un escape por id
    @GetMapping("/escapes/{id}")
    public ResponseEntity<Escape> obtenerEscapeporId(@PathVariable  Long id){
        Escape escape = escaperepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe el escape con el ID : " + id));
        return ResponseEntity.ok(escape);
    }

    @PutMapping ("/escapes/{id}")
    public ResponseEntity<Escape> actualizarEscapeporId(@PathVariable  Long id, @RequestBody Escape detallesEscape){
        Escape escape = escaperepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe el escape con el ID : " + id));
       
        escape.setMarca(detallesEscape.getMarca());     
        escape.setModelo(detallesEscape.getModelo()); 
        escape.setDetalle(detallesEscape.getDetalle()); 
        escape.setPrecio(detallesEscape.getPrecio());      
                  
        Escape escapeActualizado = escaperepository.save(escape);
        return ResponseEntity.ok(escapeActualizado);
    }

  @DeleteMapping("/escapes/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEscape(@PathVariable Long id){
		Escape escape = escaperepository.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el escape con el ID : " + id));
		
		escaperepository.delete(escape);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
}
