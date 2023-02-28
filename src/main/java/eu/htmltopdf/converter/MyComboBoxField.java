package eu.htmltopdf.converter;

import com.itextpdf.html2pdf.attach.impl.layout.form.element.ComboBoxField;
import com.itextpdf.layout.renderer.IRenderer;

public class MyComboBoxField extends ComboBoxField {
    /**
     * Creates a new select field box.
     *
     * @param id the id
     */
    public MyComboBoxField(final String id) {
        super(id);
    }

    @Override
    protected IRenderer makeNewRenderer() {
        return new MySelectFieldComboBoxRenderer(this);
    }
}
