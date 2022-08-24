package wang.study.Month07;

import wang.study.innerClass.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*********************************************************
 * 文件名称：Day25.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.Month07
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/25 10:59
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
public class Day25 {

    /**
     * 前序遍历（非递归，空间复杂度：O（n）
     * @param node
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode tem = stack.pop();
            res.add(tem.val);
            if (tem.right != null) stack.push(tem.right);
            if (tem.left != null) stack.push(tem.left);
        }
        return res;
    }

    /**
     * 中序遍历，非递归形式，空间复杂度：O（n）
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        /* 特殊情况处理 */
        if (root == null) return res;
        /* 进行中序便利 */
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tem = stack.pop();
            if (!stack.isEmpty() && stack.peek() == tem) {
                stack.pop();
                res.add(tem.val);
                continue;
            }
            if (tem.right != null) stack.push(tem.right);
            stack.push(tem);
            stack.push(tem);
            if (tem.left != null) stack.push(tem.left);
        }
        return res;
    }

    /**
     * 后序遍历，非递归形式，空间复杂度：O（n）
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        /* 特殊情况处理 */
        if (root == null) return res;
        /* 后续遍历 */
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (!stack.isEmpty() && stack.peek() == pop) {
                stack.pop();
                res.add(pop.val);
                continue;
            }
            stack.push(pop);
            stack.push(pop);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
        return res;
    }

    /**
     * 前序遍历，使用莫里斯遍历方式，空间复杂度：O（1）
     * 由于直接指向根节点的右子树，导致后续无法将二叉树进行还原
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        /* 特殊情况 */
        if (root == null) return res;
        /* 进行前序遍历 */
        TreeNode node = root;
        while (node != null) {
            res.add(node.val);
            if (node.left == null) {
                /* 左子树为空 */
                node = node.right;
            } else {
                /* 将左子树最node一个节点只想右字数第一个节点 */
                TreeNode tem = node.left;
                while (tem.right != null) tem = tem.right;
                tem.right = node.right;
                node = node.left;
            }
        }
        return res;
    }

    /**
     * 使用 morris 遍历法
     * 空间复杂度：O（1）
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                res.add(node.val);
                node = node.right;
            } else {
                TreeNode tem = node.left;
                while (tem.right != null && tem.right != node) tem = tem.right;
                if (tem.right == null) {
                    res.add(node.val);
                    tem.right = node;
                    node = node.left;
                } else {
                    tem.right = null;
                    node = node.right;
                }
            }
        }
        return res;
    }

    /**
     * 使用 morris 进行中序遍历
     * 空间复杂度：O（1） - 不算结果
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                res.add(node.val);
                node = node.right;
            } else {
                TreeNode tem = node.left;
                while (tem.right != null && tem.right != node) tem = tem.right;
                if (tem.right == null) {
                    /* 当前节点还没有遍历过 */
                    tem.right = node;
                    node = node.left;
                } else {
                    /* 左子树遍历结束，进入右子树 */
                    res.add(node.val);
                    tem.right = null;
                    node = node.right;
                }
            }
        }
        return res;
    }

    /**
     * 使用 morris 进行后序遍历，根右左 -> 左右根
     * 空间复杂度：O（1）
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.right == null) {
                res.addFirst(node.val);
                node = node.left;
            } else {
                TreeNode tem = node.right;
                while (tem.left != null && tem.left != node) tem = tem.left;
                if (tem.left == null) {
                    res.addFirst(node.val);
                    tem.left = node;
                    node = node.right;
                } else {
                    tem.left = null;
                    node = node.left;
                }
            }
        }
        return res;
    }


    private TrieNode root = null;

    /**
     * 初始化前缀树
     */
    public void Trie() {
        root = new TrieNode(true);
    }

    /**
     * 在前缀树中插入字符串 word
     * @param word
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.next[index] == null) node.next[index] = new TrieNode();
            node = node.next[index];
        }
        node.isWord = true;
    }

    /**
     * 判断 word 是否存在于前缀树中
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.next[index] == null) return false;
            node = node.next[index];
        }
        return node.isWord;
    }

    /**
     * 判断前缀树中是否存在以 prefix 为前缀的单词
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (node.next[index] == null) return false;
            node = node.next[index];
        }
        return true;
    }

    public class TrieNode {
        public TrieNode[] next = new TrieNode[26]; // 存储下一个小写英文字母对应的节点
        public boolean isWord; // 以当前节点为结尾的单词是否为完整的 word

        public TrieNode() {
        }

        public TrieNode(boolean isWord) {
            this.isWord = isWord;
        }
    }
}
