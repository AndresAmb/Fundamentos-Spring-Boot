package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithPropertiesImplemente implements MyBeanWithProperties{
    private String nombre;
    private String apellido;

    public MyBeanWithPropertiesImplemente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return nombre + " - " + apellido;
    }
}
