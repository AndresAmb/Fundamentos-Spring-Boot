package com.fundamentos.springboot.fundamentos.components;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependecy{
    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente dos");
    }
}
