package AlgortimosConversao;

import dominio.Leiaute;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by gabriel on 21/07/16.
 */
public class AtividadesProjeto extends Atividades {


    public AtividadesProjeto (List<Leiaute> lista){
        setLista(lista);
    }

    @Override
    public List<Leiaute> converLeiautes( String txt ){
        txt = txt.replaceAll("[|]", "");

        StringTokenizer token = new StringTokenizer( txt , "\n" );

        Pattern expQtdeHoras    = Pattern.compile(".*CHA:\\s[0-9].*");

        Leiaute leiaute = null;


        while( token.hasMoreTokens() ){
            String linha = token.nextToken();
            if( linha.matches("^Titulo do Projeto:.*") ){
                leiaute = new Leiaute();
                leiaute.setSequencialAtividade(getSequencia());
                leiaute.setDescricaoAtividade(linha.replaceAll("Titulo do Projeto:",""));
            }
            if( linha.matches("^Tabela:.*") ){
            	buscaAtividadeResolucao(linha.replaceAll("Tabela:\\s", "").toLowerCase(), leiaute);
            }
            if ( linha.matches("^CHA:.*") ){
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

                getLista().add(leiaute);
            }
        }
        return getLista();
    }

}
