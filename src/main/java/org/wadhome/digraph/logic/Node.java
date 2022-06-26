package org.wadhome.digraph.logic;

/**
 * @param name Slightly simpler to store the node name as a character,
 *             to save a few bytes, but not as useful in the future.
 *             In real world situations, there will be more than only 26 nodes!
 */
public record Node(String name) implements Comparable<Node> {

    public Node(String name) {
        this.name = validateNodeName(name);
    }

    String validateNodeName(String nodeName) {
        if (nodeName == null || nodeName.length() != 1) {
            throw new RuntimeException("Invalid nodeName: '" + nodeName + "'");
        }

        nodeName = nodeName.trim().toUpperCase();

        char c = nodeName.charAt(0);
        if (c < 'A' || c > 'Z') {
            throw new RuntimeException("Only accepting A-Z as valid node names.");
        }

        return nodeName;
    }

    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.name());
    }
}
