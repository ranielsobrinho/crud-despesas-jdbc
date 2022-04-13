package br.com.raniel;

import br.com.raniel.dao.DespesaDAO;
import br.com.raniel.model.Despesa;

import java.util.Optional;

public class AtualizarDespesa {
    public static void main(String[] args) {
        DespesaDAO dao = new DespesaDAO();
        Optional<Despesa> despesaOptional = dao.findById(2L);

        Despesa despesa = despesaOptional.get();

        despesa.setDescricao("Viagem de uber ao shopping");

        dao.update(despesa);
    }
}
