package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWhitDependencyImplements implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWhitDependencyImplements.class);

    private MyOperation myOperation;

    public MyBeanWhitDependencyImplements(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWhitDependency() {
        LOGGER.info("Hemos ingresado al metodo printWhitDependency");
        int numero = 1;
        LOGGER.debug("El numero enviado como parametro a la dependencia operacion es: "+ numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
