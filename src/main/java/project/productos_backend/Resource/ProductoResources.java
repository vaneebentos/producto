package project.productos_backend.Resource;
import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/bajada")

public class ProductoResources {
    
        private final ProductoService productoService;
        public ProductoResources(ProductoService productoService){
            this.productoService = productoService;
        }

        @GetMapping("/all")
        public ResponseEntity<List<Producto>>getAllProductos (){
            List<Producto> productos= productoService.findAllProducto ();
            return new ResponseEntity<> (productos,HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<Producto> addproducto(@RequestBody Producto producto){
            Producto newProducto = productoService.addProducto(producto);
            return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
        }

    }

