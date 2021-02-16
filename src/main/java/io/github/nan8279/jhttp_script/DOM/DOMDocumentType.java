package io.github.nan8279.jhttp_script.DOM;

import org.jsoup.nodes.DocumentType;

public class DOMDocumentType extends DOMNode {
    final private DocumentType documentType;

    public DOMDocumentType(DocumentType documentType) {
        super(documentType);
        this.documentType = documentType;
    }

    public String getName() {
        return documentType.name();
    }

    public String getPublicId() {
        return documentType.publicId();
    }

    public String getSystemId() {
        return documentType.systemId();
    }
}
