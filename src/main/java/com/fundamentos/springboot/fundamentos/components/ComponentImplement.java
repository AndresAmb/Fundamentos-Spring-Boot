package com.fundamentos.springboot.fundamentos.components;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependecy {

    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente");
    }
}
