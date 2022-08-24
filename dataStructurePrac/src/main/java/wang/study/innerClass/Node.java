package wang.study.innerClass;

import java.util.List;

/**
 * Author:   wang
 * Date:     2022/7/10 20:02
 * function:
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
