package io.github.nan8279.jhttp_script.DOM;

import org.jsoup.nodes.Node;

public class DOMNode {
    final private Node node;

    public DOMNode(Node node) {
        this.node = node;
    }

    public String getBaseURI() {
        return node.baseUri();
    }

    public DOMNode[] getChildNodes() {
        DOMNode[] childNodes = new DOMNode[node.childNodes().size()];

        int i = 0;
        for (Node node : node.childNodes()) {
            childNodes[i] = new DOMNode(node);
            i++;
        }
        return childNodes;
    }

    public DOMNode getFirstChild() {
        try {
            return getChildNodes()[0];
        } catch (ArrayIndexOutOfBoundsException exception) {
            return null;
        }
    }

    public DOMNode getLastChild() {
        try {
            return getChildNodes()[getChildNodes().length - 1];
        } catch (ArrayIndexOutOfBoundsException exception) {
            return null;
        }
    }

    public DOMNode getNextSibling() {
        if (node.nextSibling() != null) {
            return new DOMNode(node.nextSibling());
        }
        return null;
    }

    public String getNodeName() {
        return node.nodeName();
    }

    public DOMDocument ownerDocument() {
        return new DOMDocument(node.ownerDocument());
    }

    public DOMNode getParentNode() {
        return new DOMNode(node.parentNode());
    }

    public DOMNode getRootNode() {
        return new DOMNode(node.root());
    }

    public boolean hasChildNodes() {
        return getChildNodes().length != 0;
    }

    public void insertBefore(DOMNode otherNode) {
        node.before(otherNode.node);
    }
}
