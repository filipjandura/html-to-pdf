package eu.htmltopdf.converter;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfChoiceFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.form.element.AbstractSelectField;
import com.itextpdf.forms.form.element.FormField;
import com.itextpdf.forms.form.renderer.SelectFieldComboBoxRenderer;
import com.itextpdf.html2pdf.attach.impl.layout.Html2PdfProperty;
//import com.itextpdf.html2pdf.attach.impl.layout.form.element.AbstractSelectField;
//import com.itextpdf.html2pdf.attach.impl.layout.form.renderer.SelectFieldComboBoxRenderer;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

import java.util.List;

import static com.itextpdf.forms.form.FormProperty.*;


public class MySelectFieldComboBoxRenderer extends SelectFieldComboBoxRenderer {
    /**
     * Creates a new {@link SelectFieldComboBoxRenderer} instance.
     *
     * @param modelElement the model element
     */
    public MySelectFieldComboBoxRenderer(final AbstractSelectField modelElement) {
        super(modelElement);
        setProperty(FORM_FIELD_FLATTEN, Boolean.FALSE);
    }


    @Override
    public IRenderer getNextRenderer() {
        return new MySelectFieldComboBoxRenderer((AbstractSelectField) modelElement);
    }

    @Override
    protected void applyAcroField(DrawContext drawContext) {
        String name = getModelId();
        final PdfDocument doc = drawContext.getDocument();
        final Rectangle area = occupiedArea.getBBox().clone();

        final PdfPage page = doc.getPage(occupiedArea.getPageNumber());
        List<IBlockElement> options = ((MyComboBoxField) getModelElement()).getOptions();

        String[][] listOptions = new String[options.size()][2];
        for (IBlockElement option : options) {
            listOptions[options.indexOf(option)][1] = option.getProperty(FORM_FIELD_VALUE);
            listOptions[options.indexOf(option)][0] = option.getProperty(FORM_FIELD_LABEL);
        }
//        final PdfFormField listField = PdfChoiceFormField.createComboBox(doc, area, name, listOptions[0][1], listOptions);
//        PdfAcroForm.getAcroForm(doc, true).addField(listField, page);
    }
}
