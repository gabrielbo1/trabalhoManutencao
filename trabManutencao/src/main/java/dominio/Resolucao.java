package dominio;

import java.io.Serializable;

/**
 * Created by gabriel on 23/07/16.
 */
public class Resolucao implements Serializable{
    String idunico;
    String codigo;
    String pontos;
    
	
	public String getIdunico() {
		return idunico;
	}
	public void setIdunico(String idunico) {
		this.idunico = idunico;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPontos() {
		return pontos;
	}
	public void setPontos(String pontos) {
		this.pontos = pontos;
	}
}
