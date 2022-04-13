package br.com.raniel;

import br.com.raniel.dao.DespesaDAO;
import br.com.raniel.model.Despesa;

import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        DespesaDAO dao = new DespesaDAO();

        List<Despesa> despesas = dao.findAll();

        for (Despesa despesa : despesas) {
            System.out.println("Id: " + despesa.getId());
            System.out.println("Descrição: " + despesa.getDescricao());
            System.out.println("Valor: " + despesa.getValor());
            System.out.println("Data: " + despesa.getData());
            System.out.println("Categoria: " + despesa.getCategoria());
            System.out.println();
        }

        Optional<Despesa> despesaOptional = dao.findById(2L);
        despesaOptional.ifPresent(despesa -> {
            System.out.println("Encontrado pelo método getById");
            System.out.println("Id: " + despesa.getId());
            System.out.println("Descrição: " + despesa.getDescricao());
            System.out.println("Valor: " + despesa.getValor());
            System.out.println("Data: " + despesa.getData());
            System.out.println("Categoria: " + despesa.getCategoria());
        });


    }
}
