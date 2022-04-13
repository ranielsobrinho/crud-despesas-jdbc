package br.com.raniel;

import br.com.raniel.dao.DespesaDAO;
import br.com.raniel.model.Categoria;
import br.com.raniel.model.Despesa;

import java.time.LocalDate;

public class CriarDespesa {
    public static void main(String[] args) {
        DespesaDAO dao = new DespesaDAO();
        Despesa despesa = new Despesa("Pagamento internet", LocalDate.of(2022, 4, 11), 25, Categoria.OUTROS);
        dao.save(despesa);
    }
}
