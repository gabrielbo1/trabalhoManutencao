package AlgortimosConversao;

import dominio.Leiaute;

import java.util.List;

/**
 * Created by gabriel on 23/07/16.
 */
public class AtividadesProdutos extends Atividades {
    public AtividadesProdutos(List<Leiaute> lista, Integer sequencia ){
        setLista(lista);
        setSequencia(sequencia);
    }

    @Override
    public List<Leiaute> converLeiautes(String txt) {
        return getLista();
    }
}
