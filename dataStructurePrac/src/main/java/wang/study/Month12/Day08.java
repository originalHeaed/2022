package wang.study.Month12;

import com.alibaba.druid.sql.dialect.blink.parser.BlinkStatementParser;

import java.util.*;

public class Day08 {

    //============================ prim + 最小堆（very nice） ==========================================
    /**
     * 使用 prim + 最小堆的方式计算
     * n^2log(n)
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((x, y) -> x.cost - y.cost);
        boolean[] visited = new boolean[size];
        int result = 0;
        int count = size - 1;
        // Add all edges from points[0] vertexs
        for (int j = 1; j < size; j++) {
            // Calculate the distance between two coordinates.
            int[] coordinate1 = points[0];
            int[] coordinate2 = points[j];
            int cost = Math.abs(coordinate1[0] - coordinate2[0]) + Math.abs(coordinate1[1] - coordinate2[1]);
            Edge edge = new Edge(0, j, cost);
            pq.add(edge);
        }
        visited[0] = true;

        while (pq.size() > 0 && count > 0) {
            Edge e = pq.poll();
            int point1 = e.point1;
            int point2 = e.point2;
            int cost = e.cost;
            if ( !visited[point2] ) {
                result += cost;
                visited[point2] = true;
                for (int j = 0; j < size; j++ ) {
                    if ( !visited[j] ) {
                        int distance = Math.abs(points[point2][0] - points[j][0]) + Math.abs(points[point2][1] - points[j][1]);
                        pq.add(new Edge(point2, j, distance));
                    }
                }
                count--;
            }
        }
        return result;
    }

    class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }



    //======================================= 使用 prim 算法（稍微进行优化） =========================

    /**
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     * 使用 prim 算法
     * 耗费时间点：找到切分定理中权值最小的边
     * 时间复杂度：O（n^2）
     *  @param points
     * @return
     */
    public int minCostConnectPoints3(int[][] points) {
        /* 特殊情况处理 */
        if (points == null || points.length == 0 || points[0].length == 0 ) return -1;
        /* 使用 prim 算法获取加权无向图的最小生成树 */
        int res = 0;
        Map<Integer, Integer> out = new HashMap<>(); // 存放节点到最小生成树的最短距离
        for (int i = 1; i < points.length; i++) {
            out.put(i, Integer.MAX_VALUE);
        }
        int index = 0; // 最近一个加入到最小生成树的节点下标
        while (!out.isEmpty()) {
            /* 选出下一个加入最小生成树的节点 */
            int minWeight = Integer.MAX_VALUE;
            int temIndex = -1;
            for(Map.Entry<Integer, Integer> entry : out.entrySet()) {
                int weigth = Math.min(entry.getValue(), getWeight(points, entry.getKey(), index));
                out.put(entry.getKey(), weigth);
                if (weigth < minWeight) {
                    minWeight = weigth;
                    temIndex = entry.getKey();
                }
            }
            res += minWeight;
            index = temIndex;
            out.remove(index);
        }
        return res;
    }

    /**
     * 获取权重
     */
    private int getWeight(int[][] points, int index1, int index2) {
        return Math.abs(points[index1][0] - points[index2][0]) + Math.abs(points[index1][1] - points[index2][1]);
    }

    //=============================== 使用 prim 算法（完全未进行优化）=================================

    /**
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     * 使用 prim 算法
     * 耗费时间点：找到切分定理中权值最小的边
     * 时间复杂度：O（n^2）
     * 超时了，虽然是规范的写法，但是时间复杂度比较高，主要是在获取切分权值边时
     *  @param points
     * @return
     */
    public int minCostConnectPoints2(int[][] points) {
        /* 特殊情况处理 */
        if (points == null || points.length == 0 || points[0].length == 0 ) return -1;
        /* 数据初始化工作 */
        Set<Integer> inTree = new HashSet<>(); // 保存已经存在于最小生成树中的节点
        Set<Integer> outTree = new HashSet<>(); // 保存尚未存在最小生成树中的节点
        int[][] weights = new int[points.length][points.length]; // 保存每个节点到另一个节点的权值
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                weights[i][j] = weight;
                weights[j][i] = weight;
            }
        }
        /* 使用 prim 算法获取加权无向图的最小生成树 */
        int res = 0;
        for (int i = 1; i < points.length; i++) {
            outTree.add(i);
        }
        while (!outTree.isEmpty()) {
            int minVal = Integer.MAX_VALUE;
            int nextIn = -1;
            for (Integer in : inTree) {
                for (Integer out : outTree) {
                    if (weights[out][in] < minVal) {
                        minVal = weights[out][in];
                        nextIn = out;
                    }
                }
            }
            inTree.add(nextIn);
            outTree.remove(nextIn);
            res += minVal;
        }
        return res;
    }

    //================================ 单源最短路径问题 ============================================

    /**
     * 有 n 个网络节点，标记为 1 到 n。
     * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
     * 不优化：时间复杂度 O（n^2），在构建图的数组以及获取下一个距离目标节点最近的节点上时间复杂度比较搞
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        /* 特殊情况处理 */
        if (times == null || times.length == 0 || n < 1 || k < 1 || k > n) return -1;
        /* 数据初始化 */
        int[][] cost = new int[n + 1][n + 1];
        Map<Integer, Integer> map = new HashMap<>(); // 记录从 k 到指定节点的最短路径
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[i].length; j++) {
                cost[i][j] = -1;
            }
        }
        for (int i = 0; i < times.length; i++) {
            cost[times[i][0]][times[i][1]] = times[i][2];
        }
        for (int i = 1; i <= n ; i++) {
            map.put(i, Integer.MAX_VALUE);
        }
        /* Dijkstra 算法 */
        int index = k; // 上一个距离 k 最短的节点
        int res = 0;
        map.remove(index);
        while (!map.isEmpty() && index != -1) {
            /* (1) 更新与 index 相连接的其他节点最短路径 */
            for (int i = 0; i <= n ; i++) {
                if (cost[index][i] != -1 && map.containsKey(i)) {
                    map.put(i, Math.min(map.get(i), res + cost[index][i]));
                }
            }
            /* 找到下一个距离 k 最小的节点 */
            index = -1;
            res = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() < res) {
                    res = entry.getValue();
                    index = entry.getKey();
                }
            }
            if (index != -1) {
                map.remove(index);
            }
        }
        return index == -1 ? -1 : res;
    }

    /**
     * 优化：不使用二维数组保存节点信息，使用 哈希表 + 链表的方式
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime2(int[][] times, int n, int k) {
        /* 特殊情况处理 */
        if (times == null || times.length == 0 || n < 1 || k < 1 || k > n) return -1;
        /* 数据初始化 */
        Map<Integer, List<Node>> cost = new HashMap<>();
        PriorityQueue<Edge2> priorityQueue = new PriorityQueue<>(new Comparator<Edge2>() {
            @Override
            public int compare(Edge2 o1, Edge2 o2) {
                return o1.cost - o2.cost;
            }
        });
        for (int i = 0; i < times.length; i++) {
            List<Node> list = cost.getOrDefault(times[i][0], new LinkedList<>());
            list.add(new Node(times[i][0], times[i][1], times[i][2]));
            cost.put(times[i][0], list);
        }
        for (int i = 1; i <= n ; i++) {
            priorityQueue.add(new Edge2(i, Integer.MAX_VALUE));
        }
        /* Dijkstra 算法 */
        int index = k; // 上一个距离 k 最短的节点
        int res = 0;
        priorityQueue.remove(new Edge2(k, Integer.MAX_VALUE));
        while (!priorityQueue.isEmpty() && index != -1) {
            /* (1) 更新与 index 相连接的其他节点最短路径 */
            List<Node> edges = cost.getOrDefault(index, new LinkedList<>());
            for (Node edge : edges) {
                if (map.containsKey(edge.tar)) {
                    map.put(edge.tar, Math.min(map.get(edge.tar), res + edge.cost));
                }
            }
            /* 找到下一个距离 k 最小的节点 */
            index = -1;
            res = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() < res) {
                    res = entry.getValue();
                    index = entry.getKey();
                }
            }
            if (index != -1) {
                map.remove(index);
            }
        }
        return index == -1 ? -1 : res;
    }


    private class Node {
        Node(int src, int tar, int cost) {
            this.src = src;
            this.tar = tar;
            this.cost = cost;
        }
        int src;
        int tar;
        int cost;
    }

    private class Edge2 {
        int index;
        int cost;

        public Edge2(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge2 edge2 = (Edge2) o;
            return index == edge2.index && cost == edge2.cost;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, cost);
        }
    }
}
