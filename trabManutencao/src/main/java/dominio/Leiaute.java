package dominio;

/**
 * Created by gabriel on 09/07/16.
 */
public class Leiaute {

    private String     grupoPontucao;         /**Grupo Pontucao*/
    //private String     pontos;               /**Pontos*/
    private Integer    sequencialAtividade;  public String getGrupoPontucao() {
		return grupoPontucao;
	}

	public void setGrupoPontucao(String grupoPontucao) {
		this.grupoPontucao = grupoPontucao;
	}

	/**SEQUENCIA DE ATIVIDADES*/
    private String     descricaoAtividade;  /**DESCRICAO DA ATIVIDADE */
    private String     qtdeHorasAtividade; /**QUANTIDADE DE HORAS*/
    private String     dtInicioAtividade, dtFimAtividade; /**DATA INICIO E DATA FIM*/

    public Integer getSequencialAtividade() {
        return sequencialAtividade;
    }

    public String getDescricaoAtividade()   { return descricaoAtividade;  }

    public String getQtdeHorasAtividade()   { return qtdeHorasAtividade;  }

    public String getDtInicioAtividade()    { return dtInicioAtividade;   }

    public String getDtFimAtividade()       { return dtFimAtividade;      }

    public void setSequencialAtividade(Integer sequencialAtividade) {
        this.sequencialAtividade = sequencialAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public void setQtdeHorasAtividade(String qtdeHorasAtividade) {
        this.qtdeHorasAtividade = qtdeHorasAtividade;
    }

    public void setDtInicioAtividade(String dtInicioAtividade) {
        this.dtInicioAtividade = dtInicioAtividade;
    }

    public void setDtFimAtividade(String dtFimAtividade) {
        this.dtFimAtividade = dtFimAtividade;
    }

    public Leiaute(){
        sequencialAtividade  = 0;
        descricaoAtividade   = null;
        qtdeHorasAtividade   = null;
        dtInicioAtividade    = null;
        dtFimAtividade       = null;
    }
}
