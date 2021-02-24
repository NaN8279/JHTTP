package io.github.nan8279.jhttp_script.DOM;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class DOMDocument extends DOMNode {
    final private Document document;

    public DOMDocument(Document document) {
        super(document);
        this.document = document;
    }

    public DOMNode getBody() {
        return new DOMNode(document.body());
    }

    public String getCharacterSet() {
        return document.charset().name();
    }

    public DOMDocumentType getDocType() {
        return new DOMDocumentType(document.documentType());
    }

    public DOMElement getHead() {
        return new DOMElement(document.head());
    }

    public DOMElement[] getChildren() {
        DOMElement[] children = new DOMElement[document.children().size()];

        int i = 0;
        for (Element child : document.children()) {
            children[i] = new DOMElement(child);
        }

        return children;
    }

    public DOMElement getFirstElementChild() {
        return new DOMElement(document.firstElementSibling());
    }

    public DOMElement getLastElementChild() {
        return new DOMElement(document.lastElementSibling());
    }

    public String getTitle() {
        return document.title();
    }

    public void setTitle(Object title) {
        document.title(title.toString());
    }

    public DOMElement createElement(Object tagName) {
        return new DOMElement(document.createElement(tagName.toString()));
    }

    public DOMElement[] getElementsByClassName(Object className) {
        DOMElement[] elements = new DOMElement[document.getElementsByClass(className.toString()).size()];

        int i = 0;
        for (Element childElement : document.getElementsByClass(className.toString())) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public DOMElement[] getElementsByTagName(Object tagName) {
        DOMElement[] elements = new DOMElement[document.getElementsByTag(tagName.toString()).size()];

        int i = 0;
        for (Element childElement : document.getElementsByTag(tagName.toString())) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public DOMElement getElementById(Object id) {
        return new DOMElement(document.getElementById(id.toString()));
    }

    public DOMNode querySelector(Object selector) {
        return new DOMNode(document.selectFirst(selector.toString()));
    }

    public DOMNode[] querySelectorAll(Object selector) {
        DOMNode[] nodes = new DOMNode[document.select(selector.toString()).size()];

        int i = 0;
        for (Node childNode : document.select(selector.toString())) {
            nodes[i] = new DOMNode(childNode);
        }

        return nodes;
    }
}
