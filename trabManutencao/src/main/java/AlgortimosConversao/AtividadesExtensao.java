package AlgortimosConversao;

import dominio.Leiaute;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by gabriel on 22/07/16.
 */
public class AtividadesExtensao extends Atividades {

    public AtividadesExtensao(List<Leiaute> lista ){
        setLista(lista);
    }

    @Override
    public List<Leiaute> converLeiautes(String txt) {
        txt = txt.replaceAll("[|]", "");

        StringTokenizer token = new StringTokenizer( txt , "\n"  );
        String linha;
        Leiaute leiaute = new Leiaute();

        while ( token.hasMoreTokens() ){
           linha = token.nextToken();
            if( linha.matches("Tabela:.*") ){
                leiaute = new Leiaute();
                leiaute.setSequencialAtividade(getSequencia());
                buscaAtividadeResolucao(linha.replaceAll("Tabela:\\s", "").toLowerCase(), leiaute);
            }
            if( linha.matches("CHA:.*") ){
                leiaute.setQtdeHorasAtividade(linha
                        .replaceAll("Data\\sInicio:\\s\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                        .replaceAll("Data\\sTermino:\\s\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                        .replaceAll("CHA:","")
                        .replaceAll("\\s",""));

                leiaute.setDtInicioAtividade(linha
                        .replaceAll("Data\\sTermino:\\s\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                        .replaceAll("CHA:\\s[0-9][0-9][0-9]","")
                        .replaceAll("CHA:\\s[0-9][0-9]","")
                        .replaceAll("CHA:\\s[0-9]","")
                        .replaceAll("Data\\sInicio:","")
                        .replaceAll("\\s",""));

                leiaute.setDtFimAtividade(linha
                        .replaceAll("Data\\sInicio:\\s\\d\\d/\\d\\d/\\d\\d\\d\\d","")
                        .replaceAll("CHA:\\s[0-9][0-9][0-9]","")
                        .replaceAll("CHA:\\s[0-9][0-9]","")
                        .replaceAll("CHA:\\s[0-9]","")
                        .replaceAll("Data\\sInicio:","")
                        .replaceAll("\\s",""));
            }
            if( linha.matches("^Descricao da atividade:") ){
                leiaute.setDescricaoAtividade(linha.replaceAll("Descricao da atividade:", ""));
                getLista().add(leiaute);
            }
        }
        return getLista();
    }
}
