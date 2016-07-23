package file;

import dominio.Leiaute;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author  Lucas Vinicios
 */
public class TxtExport {

    /*Lista de Radocs a serem exportados para txt.*/
    private List<Leiaute> listaRadocs;
    private FileWriter    exportTxt;
    private PrintWriter   gravaArq;

    public TxtExport(){}

    public void exportarListaRadocs(  List<Leiaute> listaRadocs ){

        this.listaRadocs = listaRadocs;

        try {
            /**
             * Cria arquivo racExport.txt
             * */
            exportTxt = new FileWriter("AtividadesExtraídas.txt");
            gravaArq = new PrintWriter(exportTxt);

            /**
             * Inicia o contador de Atividades como 0
             * */
            Integer sequencia = 0;

            /**
             * Intera sobre a lista de radcs e os grava em Disco
             * */
            for ( Leiaute l : listaRadocs ){
                sequencia += 1;
                l.setSequencialAtividade(sequencia);
                gravaArq.printf(l.getGrupoPontucao() + "\n");
                gravaArq.printf(l.getSequencialAtividade() +"\n" );
                gravaArq.printf(l.getDescricaoAtividade().concat("\n"));
                gravaArq.printf(l.getQtdeHorasAtividade().toString().concat("\n"));
                gravaArq.printf(l.getDtInicioAtividade().toString().concat("\n"));
                gravaArq.printf(l.getDtFimAtividade().toString().concat("\n"));
            }

            /**
             *Fecha arquivo
             **/
             gravaArq.close();

        }catch(IOException e ){
            System.out.println("Erro ao gravar arq txt em disco ");
        }


    }
}
