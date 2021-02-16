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

    public DOMAttribute getNamedItem(String name) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey().equals(name)) {
                return new DOMAttribute(attribute);
            }
        }
        return null;
    }

    public void setNamedItem(String name, String value) {
        for (Attribute attribute : attributes) {
            if (attribute.getKey().equals(name)) {
                attribute.setValue(value);
                return;
            }
        }
        attributes.add(name, value);
    }

    public void removeNamedItem(String name) {
        attributes.remove(name);
    }
}
