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

    public void setId(String id) {
        element.attr("id", id);
    }

    public String getInnerHTML() {
        return element.html();
    }

    public void setInnerHTML(String innerHTML) {
        element.html(innerHTML);
    }

    public String getOuterHTML() {
        return element.outerHtml();
    }

    public String getTagName() {
        return element.tagName();
    }

    public String getAttribute(String name) {
        return element.attr(name);
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

    public DOMElement[] getElementsByClassName(String className) {
        DOMElement[] elements = new DOMElement[element.getElementsByClass(className).size()];

        int i = 0;
        for (Element childElement : element.getElementsByClass(className)) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public DOMElement[] getElementsByTagName(String tagName) {
        DOMElement[] elements = new DOMElement[element.getElementsByTag(tagName).size()];

        int i = 0;
        for (Element childElement : element.getElementsByTag(tagName)) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public boolean hasAttribute(String name) {
        return Arrays.asList(getAttributeNames()).contains(name);
    }

    public DOMNode querySelector(String selector) {
        return new DOMNode(element.selectFirst(selector));
    }

    public DOMNode[] querySelectorAll(String selector) {
        DOMNode[] nodes = new DOMNode[element.select(selector).size()];

        int i = 0;
        for (Node childNode : element.select(selector)) {
            nodes[i] = new DOMNode(childNode);
        }

        return nodes;
    }

    public void removeAttribute(String name) {
        element.removeAttr(name);
    }

    public void setAttribute(String name, String value) {
        element.attr(name, value);
    }


}
