package AlgortimosConversao;

import dominio.Leiaute;
import dominio.Resolucao;

import java.util.HashMap;
import java.util.List;

public abstract  class Atividades {



    private List<Leiaute> lista;
    private HashMap<String, Resolucao> resolucao;
    private Integer sequencia;


	public Atividades(){}


    public List<Leiaute> converLeiautes( String txt ){
        return null;
    }

    public List<Leiaute> getLista() {
        return lista;
    }

    public void setLista(List<Leiaute> lista) {
        this.lista = lista;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }
    
    private String     pontos;               /**Pontos*/
	public HashMap<String, Resolucao> getResolucao() {
		return resolucao;
	}


	public void setResolucao(HashMap<String, Resolucao> resolucao) {
		this.resolucao = resolucao;
	}
	
	public void buscaAtividadeResolucao( String descAtividade, Leiaute leiaute ){
		if( resolucao.containsKey(descAtividade) ){
			leiaute.setGrupoPontucao(
					resolucao.get(descAtividade).getCodigo().length() == 9 ? 
					resolucao.get(descAtividade).getCodigo().concat("000") : 
					resolucao.get(descAtividade).getCodigo());
		}else{
			leiaute.setGrupoPontucao("000000000000");
		}
    }
}
