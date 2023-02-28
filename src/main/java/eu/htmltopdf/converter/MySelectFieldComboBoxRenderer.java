package eu.htmltopdf.converter;

import com.itextpdf.html2pdf.attach.impl.layout.Html2PdfProperty;
import com.itextpdf.html2pdf.attach.impl.layout.form.element.AbstractSelectField;
import com.itextpdf.html2pdf.attach.impl.layout.form.renderer.SelectFieldComboBoxRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

public class MySelectFieldComboBoxRenderer extends SelectFieldComboBoxRenderer {
    /**
     * Creates a new {@link SelectFieldComboBoxRenderer} instance.
     *
     * @param modelElement the model element
     */
    public MySelectFieldComboBoxRenderer(final AbstractSelectField modelElement) {
        super(modelElement);
        setProperty(Html2PdfProperty.FORM_FIELD_FLATTEN, Boolean.FALSE);
    }


    @Override
    public IRenderer getNextRenderer() {
        return new MySelectFieldComboBoxRenderer((AbstractSelectField) modelElement);
    }

    @Override
    protected void applyAcroField(DrawContext drawContext) {
        String name = getModelId();
//        UnitValue fontSize = (UnitValue) this.getPropertyAsUnitValue(Property.FONT_SIZE);
//        if (!fontSize.isPointValue()) {
//            Logger logger = LoggerFactory.getLogger(InputFieldRenderer.class);
//            logger.error(MessageFormatUtil.format(IoLogMessageConstant.PROPERTY_IN_PERCENTS_NOT_SUPPORTED,
//                    Property.FONT_SIZE));
//        }
//        final PdfDocument doc = drawContext.getDocument();
//        final Rectangle area = flatRenderer.getOccupiedArea().getBBox().clone();
//        final PdfPage page = doc.getPage(occupiedArea.getPageNumber());
//        final float fontSizeValue = fontSize.getValue();
//
//        FormsMetaInfoStaticContainer.useMetaInfoDuringTheAction(getMetaInfo(), () -> {
//            final PdfFormField inputField = PdfFormField.createText(doc, area, name, value, font, fontSizeValue);
//            if (password) {
//                inputField.setFieldFlag(PdfFormField.FF_PASSWORD, true);
//            } else {
//                inputField.setDefaultValue(new PdfString(value));
//            }
//            applyDefaultFieldProperties(inputField);
//            PdfAcroForm.getAcroForm(doc, true).addField(inputField, page);
//        });
//
//        writeAcroFormFieldLangAttribute(doc);
    }
}
