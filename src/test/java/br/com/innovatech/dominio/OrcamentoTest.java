package br.com.innovatech.dominio;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrcamentoTest {

    @Test
     void testarValorTotal(){
        CarroCliente carroCliente = new CarroCliente();
        Problema problema1 = new Problema("problema no motor",carroCliente);
        Problema problema2 = new Problema("problema no câmbio",carroCliente);
        ArrayList<Problema> problemas = new ArrayList<>();
        problemas.add(problema1);
        problemas.add(problema2);
        Orcamento orcamento = new Orcamento();
        double valor = orcamento.calculoValor(problemas);

        assertEquals(1200, valor);
    }
}
