package io.github.nan8279.jhttp_script.DOM;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.Arrays;
import java.util.Set;

public class DOMElement extends DOMNode {
    final private Element element;

    public DOMElement(Element element) {
        super(element);
        this.element = element;
    }

    public DOMNamedNodeMap getAttributes() {
        return new DOMNamedNodeMap(element.attributes());
    }

    public Set<String> getClassList() {
        return element.classNames();
    }

    public String getId() {
        return element.id();
    }

    public void setId(Object id) {
        element.attr("id", id.toString());
    }

    public String getInnerHTML() {
        return element.html();
    }

    public void setInnerHTML(Object innerHTML) {
        element.html(innerHTML.toString());
    }

    public String getOuterHTML() {
        return element.outerHtml();
    }

    public String getTagName() {
        return element.tagName();
    }

    public String getAttribute(Object name) {
        return element.attr(name.toString());
    }

    public String[] getAttributeNames() {
        String[] attributeNames = new String[element.attributes().size()];

        int i = 0;
        for (Attribute attribute : element.attributes()) {
            attributeNames[i] = attribute.getKey();
            i++;
        }

        return attributeNames;
    }

    public DOMElement[] getElementsByClassName(Object className) {
        DOMElement[] elements = new DOMElement[element.getElementsByClass(className.toString()).size()];

        int i = 0;
        for (Element childElement : element.getElementsByClass(className.toString())) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public DOMElement[] getElementsByTagName(Object tagName) {
        DOMElement[] elements = new DOMElement[element.getElementsByTag(tagName.toString()).size()];

        int i = 0;
        for (Element childElement : element.getElementsByTag(tagName.toString())) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public boolean hasAttribute(Object name) {
        return Arrays.asList(getAttributeNames()).contains(name.toString());
    }

    public DOMNode querySelector(Object selector) {
        return new DOMNode(element.selectFirst(selector.toString()));
    }

    public DOMNode[] querySelectorAll(Object selector) {
        DOMNode[] nodes = new DOMNode[element.select(selector.toString()).size()];

        int i = 0;
        for (Node childNode : element.select(selector.toString())) {
            nodes[i] = new DOMNode(childNode);
        }

        return nodes;
    }

    public void removeAttribute(Object name) {
        element.removeAttr(name.toString());
    }

    public void setAttribute(Object name, Object value) {
        element.attr(name.toString(), value.toString());
    }


}
