package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWhitDependencyImplements implements MyBeanWithDependency{

    private MyOperation myOperation;

    public MyBeanWhitDependencyImplements(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWhitDependency() {
        int numero = 1;
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
