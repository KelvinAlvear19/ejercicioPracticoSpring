package com.example.ejercicio.dto;

import com.example.ejercicio.model.TipoIdentificacion;

public class ClienteConDireccionMatrizDTO {
    private Long id;
    private TipoIdentificacion tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String correo;
    private String celular;

    // Propiedades de dirección
    private String provincia;
    private String ciudad;
    private String direccion;
    

    public ClienteConDireccionMatrizDTO() {
        
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }


    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }


    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }


    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }


    public String getNombres() {
        return nombres;
    }


    public void setNombres(String nombres) {
        this.nombres = nombres;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getCelular() {
        return celular;
    }


    public void setCelular(String celular) {
        this.celular = celular;
    }


    public String getProvincia() {
        return provincia;
    }


    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public String getCiudad() {
        return ciudad;
    }


    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
