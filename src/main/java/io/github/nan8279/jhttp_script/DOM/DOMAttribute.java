package io.github.nan8279.jhttp_script.DOM;

import org.jsoup.nodes.Attribute;

public class DOMAttribute {
    final private Attribute attribute;

    public DOMAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return attribute.getKey();
    }

    public String getValue() {
        return attribute.getValue();
    }

    public void setValue(Object value) {
        attribute.setValue(value.toString());
    }
}
