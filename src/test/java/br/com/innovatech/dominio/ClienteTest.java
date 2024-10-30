package br.com.innovatech.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    Cliente cliente = new Cliente("Jorge","098765432101",23,"everton","everton@email","evertin","40028922");
    Cliente cliente2 = new Cliente("Jorge","098765432101",23,"everton","everton@email","evertin","40028922");

    @Test
    void verificarIgualdade(){

        assertEquals(cliente, cliente2);
    }
}
