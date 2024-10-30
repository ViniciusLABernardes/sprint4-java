package br.com.innovatech.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarroClienteTest {

    @Test
    void testeIgualdade(){
        CarroCliente carro1 = new CarroCliente("camaro","chevrolet",2012);
        CarroCliente carro2 = new CarroCliente("camaro","chevrolet",2012);

        assertEquals(carro1,carro2);
    }
    @Test
    void testeFalhaIgualdade(){
        CarroCliente carro1 = new CarroCliente("camaro","chevrolet",2012);
        CarroCliente carro2 = new CarroCliente("corsa","chevrolet",2005);

        assertEquals(carro1,carro2);
    }
    @Test
    void testarDisponibilidade(){
        CarroCliente carro2 = new CarroCliente("corsa","chevrolet",2008);
        boolean disponibilidade = carro2.disponibilidade();

        assertTrue(disponibilidade);

    }
    @Test
    void testarDisponibilidadeFracasso(){
        CarroCliente carro2 = new CarroCliente("corsa","chevrolet",2005);
        boolean disponibilidade = carro2.disponibilidade();

        assertTrue(disponibilidade);

    }
}
