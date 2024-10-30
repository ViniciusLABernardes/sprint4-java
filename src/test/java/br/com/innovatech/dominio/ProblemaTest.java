package br.com.innovatech.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProblemaTest {

    @Test
    void verificarPrecoProblemaMotor(){
        CarroCliente carro = new CarroCliente();
        Problema problema = new Problema("problema no motor",carro);
        double valor = problema.verificarPrecoTotal();
        assertEquals(1000,valor );
    }
    @Test
    void verificarPrecoProblemaCambio(){
        CarroCliente carro = new CarroCliente();
        Problema problema = new Problema("problema no câmbio",carro);
        double valor = problema.verificarPrecoTotal();
        assertEquals(200,valor );
    }
    @Test
    void verificarPrecoProblemaArCondicionado(){
        CarroCliente carro = new CarroCliente();
        Problema problema = new Problema("problema no ar condicionado",carro);
        double valor = problema.verificarPrecoTotal();
        assertEquals(400,valor );
    }
}
