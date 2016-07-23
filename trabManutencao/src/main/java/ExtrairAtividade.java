import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import AlgortimosConversao.AtividadesAcademicasEspeciais;
import AlgortimosConversao.AtividadesAdministrativas;
import AlgortimosConversao.AtividadesEnsino;
import AlgortimosConversao.AtividadesExtensao;
import AlgortimosConversao.AtividadesOrientacao;
import AlgortimosConversao.AtividadesProjeto;
import AlgortimosConversao.AtividadesQualificacao;
import dominio.Leiaute;
import dominio.ListaResolucao;
import dominio.Resolucao;
import file.PdfImport;
import file.TxtExport;
import json.JsonParser;

/**
 * @author João Henrique.
 */
public class ExtrairAtividade {
    private static StringBuilder caputrarPartesRadoc(String radoc, String limite) {
        StringBuilder conteudo = new StringBuilder("");
        StringTokenizer tokenAtvOri = new StringTokenizer(radoc, "\n");
        while (tokenAtvOri.hasMoreTokens()) {
            String linhaAtvcOri = tokenAtvOri.nextToken();
            if (!linhaAtvcOri.equals(limite))
                conteudo.append(linhaAtvcOri).append("\n");
            else
                break;
        }
        return conteudo;
    }


    public static void main( String[] args ){

        List<Leiaute> lista      = new ArrayList<Leiaute>();
        TxtExport     txtRadocExport = new TxtExport();
        Leiaute       leiauteRadoc;
        HashMap<String, Resolucao> resolucao = new HashMap<String, Resolucao>();
        
        ListaResolucao listaResolucao = (ListaResolucao) new JsonParser(ListaResolucao.class,"/home/gabriel/atividades.json").leJson();
        listaResolucao.getResolucao().forEach((a) -> resolucao.put(a.getIdunico(), a));
		
        try{
            PdfImport file = new PdfImport(new File("/home/gabriel/teste.pdf"));
            String txtRadoc  = file.pdfText();

            /**RETIRA OS RODAPES DO PDF*/
            txtRadoc = txtRadoc.replaceAll("Data: \\d\\d/\\d\\d/\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d .*(\\r\\n|\\r|\\n)","");
            
            /**
             * Auxilidar para dividir o radoc em partes.
             * */
            String radocParts = txtRadoc;
            
            /**
             * Variaveis que guardam as
             * Partes do Radoc
             **/
            StringBuilder atividadesEnsino          = new StringBuilder("");
            StringBuilder atividadesOrientacao      = new StringBuilder("");
            StringBuilder atividadesProjetos        = new StringBuilder("");
            StringBuilder atividadesExtensao        = new StringBuilder("");
            StringBuilder atividadesQualificacao    = new StringBuilder("");
            StringBuilder atividadesAcdEspecial     = new StringBuilder("");
            StringBuilder atividadesAdministrativa  = new StringBuilder("");
            StringBuilder atividadesProdutos        = new StringBuilder("");


            StringTokenizer token = new StringTokenizer(txtRadoc, "\n");

            while ( token.hasMoreTokens() ) {
                String linha = "";
                //Retira os rodapes;
                //if(token.nextToken().matches("Data: \\d\\d/\\d\\d/\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d .*") == false)
                linha = token.nextToken();

                //Caputra a parte do Radoc especifica das Atividades de Ensino
                if (linha.equals("Atividades de ensino")) {
                    radocParts = radocParts.replace("Atividades de ensino", "");
                    /**Primeiro Grupo a ser convertido*/
                    AtividadesEnsino atvEnsinoConv = new AtividadesEnsino( lista );
                    atividadesEnsino = caputrarPartesRadoc(radocParts, "Atividades de orientação");
                    atividadesEnsino = new StringBuilder(removerAcentos(atividadesEnsino.toString()));
                    
                    atvEnsinoConv.setResolucao(resolucao);
                    lista     = atvEnsinoConv.converLeiautes(atividadesEnsino.toString());
                }
                //Caputra a parte do Radoc especifica das Atividades de Orientacao
                if (linha.equals("Atividades de orientação")) {
                    /**Segunda Grupo a ser convertido*/
                    AtividadesOrientacao atvOri = new AtividadesOrientacao(lista);
                    radocParts = radocParts.replace("Atividades de orientação", "");
                    atividadesOrientacao = caputrarPartesRadoc(radocParts, "Atividades em projetos");
                    atividadesOrientacao = new StringBuilder(removerAcentos(atividadesOrientacao.toString()));
                    
                    atvOri.setResolucao(resolucao);
                    lista     = atvOri.converLeiautes(atividadesOrientacao.toString());
                }
                //Caputra a parte do Radoc especifica das Atividades em prjetos
                if (linha.equals("Atividades em projetos")) {
                    /**Terceiro Grupo a ser convertido*/
                    AtividadesProjeto atvProj = new AtividadesProjeto(lista);
                    radocParts = radocParts.replace("Atividades em projetos", "");
                    atividadesProjetos   = caputrarPartesRadoc(radocParts, "Atividades de extensão");
                    atividadesProjetos   = new StringBuilder(removerAcentos(atividadesProjetos.toString()));
                    
                    atvProj.setResolucao(resolucao);
                    lista     = atvProj.converLeiautes(atividadesProjetos.toString());
                }
                //Caputra a parte do Radoc especifica das Atividades de extensão
                if (linha.equals("Atividades de extensão")) {
                    /**Quarto Grupo a ser convertido*/
                    AtividadesExtensao atvExt = new AtividadesExtensao(lista);
                    radocParts = radocParts.replace("Atividades de extensão", "");
                    atividadesExtensao = caputrarPartesRadoc(radocParts, "Atividades de qualificação");
                    atividadesExtensao = new StringBuilder(removerAcentos(atividadesExtensao.toString()));
                    
                    atvExt.setResolucao(resolucao);
                    lista      = atvExt.converLeiautes(atividadesExtensao.toString());
                }
                //Caputra a parte do Radoc especifica das Atividades de qualificação
                if (linha.equals("Atividades de qualificação")) {
                    AtividadesQualificacao atvQual = new AtividadesQualificacao(lista);
                    radocParts = radocParts.replace("Atividades de qualificação", "");
                    atividadesQualificacao = caputrarPartesRadoc(radocParts, "Atividades acadêmicas especiais");
                    atividadesQualificacao = new StringBuilder(removerAcentos(atividadesQualificacao.toString()));
                    
                    atvQual.setResolucao(resolucao);
                    lista     = atvQual.converLeiautes(atividadesQualificacao.toString());
                }
                //Caputra a parte do Radoc especifica das Atividades acadêmicas especiais
                if (linha.equals("Atividades acadêmicas especiais")) {
                    AtividadesAcademicasEspeciais atvAcadEsp = new AtividadesAcademicasEspeciais(lista);
                    radocParts = radocParts.replace("Atividades acadêmicas especiais", "");
                    atividadesAcdEspecial = caputrarPartesRadoc(radocParts, "Atividades administrativas");
                    atividadesAcdEspecial = new StringBuilder(removerAcentos(atividadesAcdEspecial.toString()));
                    
                    atvAcadEsp.setResolucao(resolucao);
                    lista       = atvAcadEsp.converLeiautes(atividadesAcdEspecial.toString());
                }
                //Caputra a parte do Radoc especifica das Atividades administrativas
                if (linha.equals("Atividades administrativas")) {
                    AtividadesAdministrativas atvAdm  = new AtividadesAdministrativas(lista);
                    radocParts = radocParts.replace("Atividades administrativas", "");
                    atividadesAdministrativa = caputrarPartesRadoc(radocParts, "Produtos");
                    atividadesAdministrativa = new StringBuilder(removerAcentos(atividadesAdministrativa.toString()));
                    
                    atvAdm.setResolucao(resolucao);
                    lista     = atvAdm.converLeiautes(atividadesAdministrativa.toString());
                }
                //Retira da variavel auxiliar as linhas já processadas.
                radocParts = radocParts.replaceAll(linha, "");

                txtRadocExport.exportarListaRadocs(lista);
            }

        }catch (IOException e ){
            e.printStackTrace();
        }

        System.exit(0);
    }

    private static  String removerAcentos ( String txt ){
        return Normalizer.normalize(txt, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
