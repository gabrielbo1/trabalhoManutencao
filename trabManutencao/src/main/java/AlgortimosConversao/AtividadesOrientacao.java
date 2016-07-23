package AlgortimosConversao;

import dominio.Leiaute;

import java.util.List;
import java.util.StringTokenizer;

public class AtividadesOrientacao extends Atividades {

    public AtividadesOrientacao(List<Leiaute> lista ){
        setLista(lista);
    }

    @Override
    public List<Leiaute> converLeiautes(String txt){
        txt = txt.replaceAll("[|]", "");

        StringTokenizer token = new StringTokenizer( txt , "\n" );

        Leiaute leiaute = null;

        while( token.hasMoreTokens() ){
            String linha = token.nextToken();
            if( linha.matches("^Titulo do trabalho:.*") ){
                leiaute = new Leiaute();
                leiaute.setSequencialAtividade(getSequencia());
                leiaute.setDescricaoAtividade( linha.replaceAll("Titulo do trabalho:", "") );
            }
            if( linha.matches("^Tabela:.*") ){
            	buscaAtividadeResolucao(linha.replaceAll("Tabela:\\s", "").toLowerCase(), leiaute);
            }
            if( linha.matches("^CHA:.*")){
                if( leiaute != null ){
                    linha = linha.replaceAll("\\s","");
                    leiaute.setQtdeHorasAtividade(linha.replaceAll("CHA:","")
                                            .replaceAll("Datainicio:\\d\\d/\\d\\d/\\d\\d\\d\\dDatatermino:\\d\\d/\\d\\d/\\d\\d\\d\\dTipoOrientacao:PRESENCIAL",""));
                    leiaute.setDtInicioAtividade(linha.replaceAll("CHA:","")
                                                      .replaceAll("Datainicio:","")
                                                      .replaceAll("Datatermino:\\d\\d/\\d\\d/\\d\\d\\d\\dTipoOrientacao:PRESENCIAL",""));
                    leiaute.setDtFimAtividade(linha.replaceAll("CHA:","")
                                                   .replaceAll("Datainicio:\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                                                   .replaceAll("Datatermino:","")
                                                   .replaceAll("TipoOrientacao:PRESENCIAL",""   ));
                    getLista().add(leiaute);
                }
            }

        }

        return getLista();
    }
}
