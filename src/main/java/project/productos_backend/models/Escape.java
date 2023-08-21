package project.productos_backend.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "escapes")

public class Escape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(name = "marca", length = 60, nullable = false)
    private String marca;

    @Column(name = "modelo", length = 60, nullable = false)
    private String modelo;

    @Column(name = "detalle", length = 250, nullable = false)
    private String detalle;

    @Column(name = "precio", length = 60, nullable = false)
    private Double precio;
    
    
    public Escape(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public Escape(long id, String marca, String modelo, String detalle, Double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.detalle = detalle;
        this.precio = precio;
    }

    
}
