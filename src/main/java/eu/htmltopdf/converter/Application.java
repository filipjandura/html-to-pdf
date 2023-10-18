package eu.htmltopdf.converter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java html2pdf <inputHtmlFile> <outputPdfFile> [-l]"
                    + "example: java html2pdf /path/to/input.html /path/to/output.pdf"
                    + "-l (optional) for landscape mode");
            System.exit(1);
        }

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setCreateAcroForm(true);
//        converterProperties.setTagWorkerFactory(new MyFactory());
//        converterProperties.setFontProvider(new DefaultFontProvider(true, true, true));

        String inputHtmlFile = args[0];
        String outputPdfFile = args[1];

        CommandLineParser parser = new DefaultParser();

        Options options = new Options();
        options.addOption("l", "landscape", false, "landscape mode");

        boolean landScape = false;
        try {
            CommandLine line = parser.parse(options, args);
            landScape = line.hasOption("l");
        } catch (ParseException e) {
            System.err.println("Error parsing command line arguments: " + e.getMessage());
            System.exit(1);
        }

        try (Document document = new Document(new PdfDocument(new PdfWriter(outputPdfFile)), landScape ? PageSize.A4.rotate() : PageSize.A4)) {
            HtmlConverter.convertToPdf(Files.newInputStream(Paths.get(inputHtmlFile)), document.getPdfDocument(), converterProperties);
            System.out.println("HTML file converted to PDF successfully.");
        } catch (IOException e) {
            System.out.println("Error converting HTML file to PDF: " + e.getMessage());
        }
    }
}
