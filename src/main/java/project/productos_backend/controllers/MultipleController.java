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


import project.productos_backend.repositories.MultipleRepository;
import project.productos_backend.Exceptions.ResourceNotFoundException;
import project.productos_backend.models.Multiple;


@RestController
@RequestMapping("/api/v1/")
public class MultipleController {
   
    @Autowired
    private MultipleRepository multiplerepository;

    // este metodo sirve para listar todos los multiples
    @GetMapping("/multiples")
    public List<Multiple> listarTodosLosMultiples() {
        return multiplerepository.findAll();
    }

    // este metodo sirve para guardar los multiples
    @PostMapping("/multiples")
    public Multiple guardarMultiple(@RequestBody Multiple multiple){
        return multiplerepository.save(multiple);
    }

    

    // este metodo sirve para buscar un multiple por id
    @GetMapping("/multiples/{id}")
    public ResponseEntity<Multiple> obtenerMultipleporId(@PathVariable  Long id){
        Multiple multiple = multiplerepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe el multiple con el ID : " + id));
        return ResponseEntity.ok(multiple);
    }

    @PutMapping ("/multiples/{id}")
    public ResponseEntity<Multiple> actualizarmultipleporId(@PathVariable  Long id, @RequestBody Multiple detallesMultiple){
        Multiple multiple = multiplerepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe el multiple con el ID : " + id));
       
        multiple.setMarca(detallesMultiple.getMarca());     
        multiple.setModelo(detallesMultiple.getModelo()); 
        multiple.setDetalle(detallesMultiple.getDetalle()); 
        multiple.setPrecio(detallesMultiple.getPrecio());      
                  
        Multiple multipleActualizado = multiplerepository.save(multiple);
        return ResponseEntity.ok(multipleActualizado);
    }

  @DeleteMapping("/multiples/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarMultiple(@PathVariable Long id){
		Multiple multiple = multiplerepository.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el multiple con el ID : " + id));
		
		multiplerepository.delete(multiple);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
    
}
