package br.com.raniel;

import br.com.raniel.dao.DespesaDAO;

public class DeleteDespesa {
    public static void main(String[] args) {
        DespesaDAO dao = new DespesaDAO();
        dao.delete(3L);
    }
}
