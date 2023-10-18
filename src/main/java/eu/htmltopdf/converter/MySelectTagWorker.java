package eu.htmltopdf.converter;

import com.itextpdf.forms.form.element.AbstractSelectField;
import com.itextpdf.forms.form.element.ListBoxField;
import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.layout.Html2PdfProperty;
//import com.itextpdf.html2pdf.attach.impl.layout.form.element.AbstractSelectField;
//import com.itextpdf.html2pdf.attach.impl.layout.form.element.ListBoxField;
import com.itextpdf.html2pdf.attach.impl.tags.IDisplayAware;
import com.itextpdf.html2pdf.attach.impl.tags.OptGroupTagWorker;
import com.itextpdf.html2pdf.attach.impl.tags.OptionTagWorker;
import com.itextpdf.html2pdf.attach.impl.tags.SelectTagWorker;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.html.AttributeConstants;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.styledxmlparser.css.util.CssDimensionParsingUtils;
import com.itextpdf.styledxmlparser.node.IElementNode;

import static com.itextpdf.forms.form.FormProperty.FORM_ACCESSIBILITY_LANGUAGE;
import static com.itextpdf.forms.form.FormProperty.FORM_FIELD_FLATTEN;

public class MySelectTagWorker implements ITagWorker, IDisplayAware {
    /**
     * Creates a new {@link SelectTagWorker} instance.
     *
     * @param element the element
     * @param context the context
     */    /** The form element. */
    private AbstractSelectField selectElement;

    /** The display. */
    private String display;

    public MySelectTagWorker(final IElementNode element, final ProcessorContext context) {
        String name = context.getFormFieldNameResolver().resolveFormName(element.getAttribute(AttributeConstants.NAME));

        boolean multipleAttr = element.getAttribute(AttributeConstants.MULTIPLE) != null;
        Integer sizeAttr = CssDimensionParsingUtils.parseInteger(element.getAttribute(AttributeConstants.SIZE));
        int size = getSelectSize(sizeAttr, multipleAttr);

        if (size > 1 || multipleAttr) {
            selectElement = new ListBoxField(name, size, multipleAttr);
        } else {
            selectElement = new MyComboBoxField(name); //Overrides default implementation
        }
        String lang = element.getAttribute(AttributeConstants.LANG);
        selectElement.setProperty(FORM_ACCESSIBILITY_LANGUAGE, lang);
        selectElement.setProperty(FORM_FIELD_FLATTEN, !context.isCreateAcroForm());
        display = element.getStyles() != null ? element.getStyles().get(CssConstants.DISPLAY) : null;
    }


    @Override
    public void processEnd(IElementNode element, ProcessorContext context) {

    }

    @Override
    public boolean processContent(String content, ProcessorContext context) {
        return content == null || content.trim().isEmpty();
    }

    @Override
    public boolean processTagChild(ITagWorker childTagWorker, ProcessorContext context) {
        if (childTagWorker instanceof OptionTagWorker || childTagWorker instanceof OptGroupTagWorker) {
            if (childTagWorker.getElementResult() instanceof IBlockElement) {
                selectElement.addOption((IBlockElement) childTagWorker.getElementResult());
                return true;
            }
        }
        return false;
    }

    @Override
    public IPropertyContainer getElementResult() {
        return selectElement;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    private int getSelectSize(Integer size, boolean multiple) {
        if (size != null && size > 0) {
            return (int) size;
        }

        return multiple ? 4 : 1;
    }
}
