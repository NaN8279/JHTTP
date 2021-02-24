package io.github.nan8279.jhttp_script.DOM;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

public class DOMNamedNodeMap {
    final private Attributes attributes;

    public DOMNamedNodeMap(Attributes attributes) {
        this.attributes = attributes;
    }

    public int getLength() {
        return attributes.size();
    }

    public DOMAttribute getNamedItem(Object name) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey().equals(name.toString())) {
                return new DOMAttribute(attribute);
            }
        }
        return null;
    }

    public void setNamedItem(Object name, Object value) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey().equals(name.toString())) {
                attribute.setValue(value.toString());
                return;
            }
        }
        attributes.add(name.toString(), value.toString());
    }

    public void removeNamedItem(Object name) {
        attributes.remove(name.toString());
    }
}
