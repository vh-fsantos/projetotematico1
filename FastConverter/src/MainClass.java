import java.io.File;
import java.io.IOException;	
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.PDFTextStripper;
import java.awt.*;

public class MainClass {
	
	public static void main (String[] args) throws IOException {
		String result = "";
		String fileName = "src/example.pdf";
		
		try (PDDocument document2 = PDDocument.load(new File(fileName))) {
			PDFTextStripper stripper = new PDFTextStripper();
			String str = stripper.getText(document2);
			System.out.println(str);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		try (PDDocument document = PDDocument.load(new File(fileName))) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	        stripper.setSortByPosition(true);
	        Rectangle rect = new Rectangle(260, 35, 70, 10);
	        stripper.addRegion("class1", rect);
	        PDPage firstPage = document	.getPage(0);
	        stripper.extractRegions( firstPage );
	        // System.out.println("Text in the area:" + rect);
	        result = stripper.getTextForRegion("class1");
		}catch (IOException e){
            System.err.println("Exception while trying to read pdf document - " + e);
        }
		if (result.isEmpty())
			System.out.println("VAZIO");
		
        System.out.println(result);
	}
	
}