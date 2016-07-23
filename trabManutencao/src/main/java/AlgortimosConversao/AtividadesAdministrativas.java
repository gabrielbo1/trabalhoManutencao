package AlgortimosConversao;

import dominio.Leiaute;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by gabriel on 23/07/16.
 */
public class AtividadesAdministrativas extends Atividades {
    public AtividadesAdministrativas (List<Leiaute> lista ){
        setLista(lista);
    }

    @Override
    public List<Leiaute>    converLeiautes(String txt) {
        txt = txt.replaceAll("[|]", "");

        StringTokenizer token = new StringTokenizer( txt , "\n" );
        Leiaute leiaute = null;
        String linha = null;

        while( token.hasMoreTokens() ){
            linha = token.nextToken();
            if (linha.matches("^Tabela:.*")) {
                leiaute = new Leiaute();
                leiaute.setDescricaoAtividade(linha.replaceAll("Tabela:",""));
                buscaAtividadeResolucao(linha.replaceAll("Tabela:\\s", "").toLowerCase(), leiaute);
            }
            if( linha.matches("^Portaria.*") ){
                linha = linha.replaceAll("Portaria","")
                        .replaceAll("\\d\\d\\d/\\d\\d\\d\\d","")
                        .replaceAll("NA","")
                        .replaceAll("\\d\\d\\d\\d/\\d\\d\\d\\d","")
                        .replaceAll("\\s","");

                int tamLinha = linha.length();

                if( linha.matches("^\\d.*") )
                    linha = linha.substring(1,tamLinha);

                leiaute.setQtdeHorasAtividade(
                        linha.replaceAll("Datainicio:\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                             .replaceAll("Datatermino:\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                                .replaceAll("CHA:","")
                );

                if (linha.matches(".*CHA:\\d.*") )
                    linha = linha.replaceAll("CHA:\\d","");
                if (linha.matches(".*CHA:\\d\\d.*") )
                    linha = linha.replaceAll("CHA:\\d\\d","");
                if (linha.matches(".*CHA:\\d\\d\\d.*") )
                    linha = linha.replaceAll("CHA:\\d\\d\\d","");

                leiaute.setDtInicioAtividade(
                        linha.replaceAll("Datainicio:","")
                             .replaceAll("Datatermino:\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                );

                leiaute.setDtFimAtividade(
                        linha.replaceAll("Datatermino:","")
                             .replaceAll("Datainicio:\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                );

                getLista().add(leiaute);
            }
        }
        return getLista();
    }
}
