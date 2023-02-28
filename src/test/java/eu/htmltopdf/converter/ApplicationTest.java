package eu.htmltopdf.converter;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationTest {

    @Test
    public void testSimpleHtmlToPdf() {
        String outputFilePath = "src/test/resources/simple.pdf";
        deleteFileIfExists(outputFilePath);
        Application.main(new String[]{"src/test/resources/simple.html", outputFilePath});

        assertTrue(new File(outputFilePath).exists());
    }

    @Test
    public void testCssHtmlToPdf() {
        String outputFilePath = "src/test/resources/css.pdf";
        deleteFileIfExists(outputFilePath);
        Application.main(new String[]{"src/test/resources/css.html", outputFilePath});

        assertTrue(new File(outputFilePath).exists());
    }

    private static void deleteFileIfExists(final String outputFilePath) {
        if (new File(outputFilePath).exists()) {
            new File(outputFilePath).delete();
        }
    }
}
