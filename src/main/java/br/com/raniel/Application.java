package br.com.raniel;

import br.com.raniel.dao.DespesaDAO;
import br.com.raniel.model.Categoria;
import br.com.raniel.model.Despesa;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        DespesaDAO dao = new DespesaDAO();

        Despesa despesa = new Despesa();
        despesa.setDescricao("Uber");
        despesa.setData(LocalDate.of(2022, 3, 20));
        despesa.setValor(35);
        despesa.setCategoria(Categoria.TRANSPORTE);

        Despesa insertion = dao.save(despesa);
        System.out.println("Foi inserida a despesa com o id: " + insertion.getId());
    }
}
