package com.mx.ebitware.examen_base;

import android.util.Log;

public class Persona {

    //TODO atributos de la clase
    private String nombre = "";
    private String apellidos = "";
    private int edad = 0;
    private int NSS;
    private static String sexo = "Hombre";
    private int peso = 0;
    private Float altura = 0F;

    //TODO constructor primario
    public Persona(String nombre, String apellidos, int edad, int NSS, int peso, Float altura) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.NSS = NSS;
        this.peso = peso;
        this.altura = altura;
    }

    //TODO Getter and Setter de la clase Persona

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNSS() {
        return NSS;
    }

    public void setNSS(int NSS) {
        this.NSS = NSS;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        Persona.sexo = sexo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    //TODO sobreescribir el metodo toString de la clase persona
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", NSS=" + NSS +
                ", sexo='" + sexo + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                '}';
    }

    //TODO Metodos de la clase Persona

    private Boolean esMayorDeEdad(int edad) {
        return edad >= 18;
    }

    private Boolean comprobarSexo(char sexo){
        switch (sexo){
            case 'H':
                Log.e("TAG", "El valor del sexo introducido es Hombre");
                break;
            case 'M':
                Log.e("TAG", "El valor del sexo introducido es mujer");
                break;
            default:
                Log.e("TAG", "El valor del sexo introducido es incorrecto");
        }
        return true;
    }

    private void generaNSS(){

    }

}
