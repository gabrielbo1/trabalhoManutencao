package file;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

/**
 * @author Pedro Victor
 * Classe para passar txt para String.
 * basedo em @see <a href="https://radixcode.com/pdfbox-example-code-how-to-extract-text-from-pdf-file-with-java/">radixcod</a>
 */

public class PdfImport {

    private File file;

    /**
     * Objetos da biblioteca para
     * passar arquivos pdf para do String;*
     * */
    private PDFParser parser;
    private PDFTextStripper pdfTextStripper;
    private PDDocument pdDocument;
    private COSDocument cosDocument;

    public PdfImport(File file ){
        this.file = file;
    }

    /**
     * @return  stringPdf
     * */
    public String pdfText() throws IOException{
        parser = new PDFParser(new RandomAccessFile(file,"r"));
        parser.parse();

        /**
         * Cria documento especifico da Biblioteca a partir do PDF.
         * */
        cosDocument = parser.getDocument();
        pdDocument  = new PDDocument(cosDocument);

        /**Cria nova instancia de PDFTextStripper e
         * passa da primeira a ultima pagina para o Objeto.
         */
        pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setEndPage(pdDocument.getNumberOfPages());

        /**
         * Retorna a String do texto PDF
         **/
        return pdfTextStripper.getText(pdDocument);
    }

}
