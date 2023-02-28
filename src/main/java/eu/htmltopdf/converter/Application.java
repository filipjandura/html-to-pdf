package eu.htmltopdf.converter;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java html2pdf <inputHtmlFile> <outputPdfFile>"
                    + "example: java html2pdf /path/to/input.html /path/to/output.pdf");
            System.exit(1);
        }

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setCreateAcroForm(true);
//        converterProperties.setTagWorkerFactory(new MyFactory()); TODO override SelectTagWorker

        String inputHtmlFile = args[0];
        String outputPdfFile = args[1];

        File htmlFile = new File(inputHtmlFile);
        File pdfFile = new File(outputPdfFile);

        try {
            HtmlConverter.convertToPdf(htmlFile,pdfFile,converterProperties);
            System.out.println("HTML file converted to PDF successfully.");
        } catch (IOException e) {
            System.out.println("Error converting HTML file to PDF: " + e.getMessage());
        }
    }


}
