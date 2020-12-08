package com.example.prueba;

public class PersonasDTO {

    private String id, nombre,email,celular,sexo,contrasenia;
    private int edad;

    public PersonasDTO(String id, String nombre, String email, String celular, String sexo, String contrasenia, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.sexo = sexo;
        this.contrasenia = contrasenia;
        this.edad = edad;
    }

    public PersonasDTO(){
    }
    public String getId() {
        return id;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }
}
