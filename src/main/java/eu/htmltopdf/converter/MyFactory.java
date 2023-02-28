package eu.htmltopdf.converter;

import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.styledxmlparser.node.IElementNode;

public class MyFactory extends DefaultTagWorkerFactory {
    @Override
    public ITagWorker getCustomTagWorker(IElementNode tag, ProcessorContext context) {
        if (tag.name().equals("select")) {
            return new MySelectTagWorker(tag, context);
        }
        return super.getCustomTagWorker(tag, context);
    }
}
