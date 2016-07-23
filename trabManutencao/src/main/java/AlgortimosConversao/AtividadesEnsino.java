package AlgortimosConversao;

import dominio.Leiaute;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtividadesEnsino  extends  Atividades {

    public  AtividadesEnsino(List<Leiaute> lista){
        setLista(lista);
    }

    @Override
    public List<Leiaute> converLeiautes(String txt) {
        /**Retira formatações das tabelas*/
        txt = txt.replaceAll("RGCG - Regime de Graduacao Semestral\n", "");
        txt = txt.replaceAll("Curso Disciplina CHA Ano Sem Turma Sub N alunos N sub CHT CHP CHAC Conjug\n", "");
        txt = txt.replaceAll("LEGENDA: CHA - Carga horaria da atividade | Sem - Semestre | Sub - Subturma | CHT, CHP e CHAC - Carga horaria teorica, pratica e acessoria | Conjug - Disciplina conjugada \n", "");
        txt = txt.replaceAll("DISCIPLINA UNIDADE - INSTITUTO DE\n" + "INFORMATICA\n", "");
        txt = txt.replaceAll("NAO", "");
        txt = txt.replaceAll("SIM", "");
        txt = txt.replaceAll("[|]", "");

        StringTokenizer token = new StringTokenizer( txt , "\n" );

        while( token.hasMoreTokens() ){
            String linha    = token.nextToken();
            Leiaute leiaute = new Leiaute();
            buscaAtividadeResolucao("aulas presenciais na graduacao",leiaute); 
            leiaute.setDescricaoAtividade(retiraDesc(linha));
            leiaute.setQtdeHorasAtividade(retiraQtdHoras(linha));
            leiaute = retiraDataInicioFim( linha , leiaute);

            if( leiaute != null )
                this.getLista().add(leiaute);
        }

        return this.getLista();
    }

    private String retiraDesc( String txt ){
        txt = txt.replaceAll("[0-9]","");
        txt = txt.replaceAll("[A-Z]{1}\\s{1}[A-Z]{1}\\s{1}", "");
        return txt;
    }

    private  String retiraQtdHoras( String txt ){
        txt = txt.replaceAll("\\s\\d\\s", "");
        txt = txt.replaceAll("[A-Z]", "");
        txt = txt.replaceAll("[a-z]", "");
        txt = txt.replaceAll("\\s", "");
        return txt.substring(0,2);
    }

    private Leiaute retiraDataInicioFim( String txt, Leiaute l  ){
        txt = txt.replaceAll("[A-Z]", "");
        txt = txt.replaceAll("[a-z]", "");
        Pattern regexAnoSeme   = Pattern.compile("\\s\\d\\d\\d\\d\\s\\d\\s");
        Matcher matcherAnoSeme =   regexAnoSeme.matcher(txt);
        if( matcherAnoSeme.find() )  {
            txt = matcherAnoSeme.group();
            if(txt.replaceAll("\\s\\d\\d\\d\\d\\s", "").replaceAll("\\s","").equals("1")){
                l.setDtInicioAtividade("01/01/" + txt.replaceAll("\\s\\d\\s", "").replaceAll("\\s",""));
                l.setDtFimAtividade("30/06/" + txt.replaceAll("\\s\\d\\s", "").replaceAll("\\s",""));
            }else{
                l.setDtInicioAtividade("01/07/" + txt.replaceAll("\\s\\d\\s", "").replaceAll("\\s",""));
                l.setDtFimAtividade("31/12/" + txt.replaceAll("\\s\\d\\s", "").replaceAll("\\s",""));
            }
        }
        else {
            return null;
        }

        return l;
    }

}
