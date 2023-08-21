package project.productos_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table (name = "Arquitecturas")

public class Arquitectura {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    
    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;

    @Column(name = "detalle", length = 250, nullable = false)
    private String detalle;

    @Column(name = "precio", length = 60, nullable = false)
    private Double precio;


    public Arquitectura(){

    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDetalle() {
        return detalle;
    }


    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


    public Double getPrecio() {
        return precio;
    }


    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public Arquitectura(long id, String nombre, String detalle, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.precio = precio;
    }

    
}
