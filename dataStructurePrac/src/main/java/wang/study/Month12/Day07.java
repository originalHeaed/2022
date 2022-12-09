package wang.study.Month12;

import java.util.*;

public class Day07 {
    private List<List<Integer>> res = new ArrayList<>();

    /**
     * 使用深度优先搜索
     * 关键点：记录访问过的点，记录到当前点的路径
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        /* 特殊情况判断 */
        if (graph == null || graph.length == 0 || graph[0].length == 0) return res;
        /* 使用深度优先搜素 */
        boolean[] isVisited = new boolean[graph.length];
        dfs(graph, isVisited, new ArrayList<>(), 0);
        return res;
    }

    /**
     * dfs
     */
    private void dfs(int[][] graph, boolean[] isVisited, List<Integer> list, int i) {
        /* 1. 已经到达过该点，直接 retur */
        if (isVisited[i]) return;
        /* 2. 从该点出发进行 dfs */
        isVisited[i] = true;
        list.add(i);
        if (i == (graph.length - 1)) {
            /* 抵达终点 */
            res.add(new ArrayList<>(list));
        } else {
            for (int j = 0; j < graph[i].length; j++) {
                dfs(graph, isVisited, list, graph[i][j]);
            }
        }
        /* 3. 回退数据 */
        isVisited[i] = false;
        list.remove(list.size() - 1);
    }


    /**
     * dfs，由于是有向无环图，因此不可能出现顺着某条路径访问到一个已经访问过的点中，因此无需记录访问过的点
     */
    private void dfs2(int[][] graph, List<Integer> list, int i) {
        list.add(i);
        if (i == (graph.length - 1)) {
            /* 抵达终点 */
            res.add(new ArrayList<>(list));
        } else {
            for (int j = 0; j < graph[i].length; j++) {
                dfs2(graph, list, graph[i][j]);
            }
        }
        /* 3. 回退数据 */
        list.remove(list.size() - 1);
    }

    /**
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     * 使用 kruskal 算法 + 并查集
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        /* 1. 获取每个点之间的最短距离，并且排序 */
        class Node {
            public Node(int indexA, int indexB, int val) {
                this.nodeIndexA = indexA;
                this.nodeIndexB = indexB;
                this.val = val;
            }
            int nodeIndexA;
            int nodeIndexB;
            int val;
        }
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                list.add(new Node(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        /* 2. 取前 n - 1 个不形成环且值最先的结果集合 */
        int[] pointArray = new int[points.length];
        for (int i = 0; i < pointArray.length; i++) {
            pointArray[i] = i;
        }
        int res = 0;
        int count = 0;
        for (Node val : list) {
            if (merger(val.nodeIndexA, val.nodeIndexB, pointArray)) {
                res += val.val;
                count++;
            }
            if (count == (points.length - 1)) return res;
        }
        return 0;
    }

    /**
     * 返回 index 的根节点（并且压缩路径）
     */
    private int find(int index, int[] pointArray) {
        return pointArray[index] == index ? index : (pointArray[index] = find(pointArray[index], pointArray));
    }

    /**
     * 将 index1 节点和 index2 节点进行合并（如果已经存在与一个集合中，则返回 false，否则返回 true）
     */
    private boolean merger(int index1, int index2, int[] pointArray) {
        int root1 = find(index1, pointArray);
        int root2 = find(index2, pointArray);
        if (root1 != root2) {
            pointArray[root2] = root1;
            return true;
        }
        return false;
    }

}
