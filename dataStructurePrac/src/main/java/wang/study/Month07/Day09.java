package wang.study.Month07;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import wang.study.innerClass.ListNode;
import wang.study.innerClass.Node;
import wang.study.innerClass.TreeNode;

/**
 * Author:   wang
 * Date:     2022/7/10 20:02
 * function:
 */
public class Day09 {
    /**
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;
        pre(root, res);
        return res;
    }

    /**
     * 使用递归的方式完成本题
     * @param root
     * @param list
     */
    private void pre(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.children == null || root.children.size() == 0) return;
        for (Node child : root.children) {
            pre(child, list);
        }
    }


    /**
     * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
     * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
     * 使用堆栈实现
     * @param root
     * @return
     */
    public List<Integer> preorder2(Node root) {
        List<Integer> res = new LinkedList<Integer>();
        if (root == null) return res;
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        Stack<Node> help = new Stack<Node>();
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            res.add(0,pop.val);
            if (pop.children == null) continue;
            for (Node child : pop.children) {
                help.push(child);
            }
            while (!help.isEmpty()) stack.push(help.pop());
        }
        return res;
    }


    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * 使用队列。时间复杂度：O（n），空间复杂度：O（n）
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<Integer>();
            while (size-- != 0) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
            res.add(list);
        }
        return res;
    }
}
