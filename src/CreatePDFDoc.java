import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class CreatePDFDoc {

	// itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/
	// xmlworker-5.4.1.jar http://sourceforge.net/projects/xmlworker/files/
	public void createPDFDocument(String str, String fileName) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			document.add(new Paragraph(str));
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createhtmlPDFDocument(String str, String fileName) {
		try {
			Document document = new Document(PageSize.LETTER);
			PdfWriter pdfWriter = PdfWriter.getInstance(document,
					new FileOutputStream(fileName));
			document.open();
			document.addAuthor("NYIT Certificate Authority");
			//document.addCreator("Real's HowTo");
			document.addSubject("Certificate Details");
			document.addCreationDate();
			document.addTitle("NYIT Certificate Details");

			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			String docString = "<html><head></head><body><table><tr><td>" + str
					+ "</td></tr></table></body></html>";

			/*String str = "<html><head></head><body>"+
			  "<a href='http://www.rgagnon.com/howto.html'><b>Real's HowTo</b></a>" +
			  "<h1>Show your support</h1>" +
			  "<p>It DOES cost a lot to produce this site - in ISP storage and transfer fees, " +
			  "in personal hardware and software costs to set up test environments, and above all," +
			  "the huge amounts of time it takes for one person to design and write the actual content.</p>" +
			  "<p>If you feel that effort has been useful to you, perhaps you will consider giving something back?</p>" +
			  "<p>Donate using PayPal� to real@rgagnon.com.</p>" +
			  "<p>Contributions via PayPal are accepted in any amount</p>" +
			  "<P><br/><table border='1'><tr><td>Java HowTo</td></tr><tr>" +
			  "<td style='background-color:red;'>Javascript HowTo</td></tr>" +
			  "<tr><td>Powerbuilder HowTo</td></tr></table></p>" +
			  "</body></html>";*/
			worker.parseXHtml(pdfWriter, document, new StringReader(str));
			document.close();
			System.out.println("Done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}