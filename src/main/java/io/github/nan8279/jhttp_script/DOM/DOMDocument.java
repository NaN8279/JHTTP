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

    public void setTitle(String title) {
        document.title(title);
    }

    public DOMElement createElement(String tagName) {
        return new DOMElement(document.createElement(tagName));
    }

    public DOMElement[] getElementsByClassName(String className) {
        DOMElement[] elements = new DOMElement[document.getElementsByClass(className).size()];

        int i = 0;
        for (Element childElement : document.getElementsByClass(className)) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public DOMElement[] getElementsByTagName(String tagName) {
        DOMElement[] elements = new DOMElement[document.getElementsByTag(tagName).size()];

        int i = 0;
        for (Element childElement : document.getElementsByTag(tagName)) {
            elements[i] = new DOMElement(childElement);
        }

        return elements;
    }

    public DOMElement getElementById(String id) {
        return new DOMElement(document.getElementById(id));
    }

    public DOMNode querySelector(String selector) {
        return new DOMNode(document.selectFirst(selector));
    }

    public DOMNode[] querySelectorAll(String selector) {
        DOMNode[] nodes = new DOMNode[document.select(selector).size()];

        int i = 0;
        for (Node childNode : document.select(selector)) {
            nodes[i] = new DOMNode(childNode);
        }

        return nodes;
    }
}
