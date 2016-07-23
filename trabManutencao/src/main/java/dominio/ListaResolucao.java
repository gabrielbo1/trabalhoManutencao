package dominio;

import java.util.List;
import java.io.Serializable;

public class ListaResolucao implements Serializable{
	
	private List<Resolucao> Resolucao;

	public List<Resolucao> getResolucao() {
		return Resolucao;
	}

	public void setResolucao(List<Resolucao> resolucao) {
		Resolucao = resolucao;
	}
	
	

}
