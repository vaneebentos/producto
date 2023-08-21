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

import project.productos_backend.repositories.BajadaRepository;
import project.productos_backend.Exceptions.ResourceNotFoundException;
import project.productos_backend.models.Bajada;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin (origins = "http://localhost:4200/")
public class BajadaController {
    
     @Autowired
    private BajadaRepository bajadarepository;

    // este metodo sirve para listar todos los bajadas
    @GetMapping("/bajadas")
    public List<Bajada> listarTodosLasBajadas() {
        return bajadarepository.findAll();
    }

    // este metodo sirve para guardar los bajadas
    @PostMapping("/bajadas")
    public Bajada guardarBajada(@RequestBody Bajada bajada){
        return bajadarepository.save(bajada);
    }

    

    // este metodo sirve para buscar un bajada por id
    @GetMapping("/bajadas/{id}")
    public ResponseEntity<Bajada> obtenerBajadaporId(@PathVariable  Long id){
        Bajada bajada = bajadarepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe la bajada con el ID : " + id));
        return ResponseEntity.ok(bajada);
    }

    @PutMapping ("/bajadas/{id}")
    public ResponseEntity<Bajada> actualizarBajadaporId(@PathVariable  Long id, @RequestBody Bajada detallesBajada){
        Bajada bajada= bajadarepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("No existe la bajada con el ID : " + id));
       
        bajada.setMarca(detallesBajada.getMarca());     
        bajada.setModelo(detallesBajada.getModelo()); 
        bajada.setDetalle(detallesBajada.getDetalle()); 
        bajada.setPrecio(detallesBajada.getPrecio()); 
        bajada.setImagen(detallesBajada.getImagen());      
                  
        Bajada bajadaActualizada = bajadarepository.save(bajada);
        return ResponseEntity.ok(bajadaActualizada);
    }

  @DeleteMapping("/bajadas/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarBajada(@PathVariable Long id){
		Bajada bajada = bajadarepository.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe la bajada con el ID : " + id));
		
		bajadarepository.delete(bajada);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
}
