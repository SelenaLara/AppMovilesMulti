package com.example.prueba;

public class MedicamentosDTO {

    private String id, nombre,tipo,horaToma,FechaInicio,FechaFin;
    private int cantidad;

    public MedicamentosDTO(String id, String nombre, String tipo, String horaToma, String fechaInicio, String fechaFin, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.horaToma = horaToma;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        this.cantidad = cantidad;
    }

    public MedicamentosDTO() {
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getHoraToma() {
        return horaToma;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public int getCantidad() {
        return cantidad;
    }
}
