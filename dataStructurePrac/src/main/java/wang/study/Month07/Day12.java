package wang.study.Month07;

/**
 * Author:   wang
 * Date:     2022/7/13 20:17
 * function:
 */
public class Day12 {

    private boolean[][] helper;

    /**
     * 有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。
     * 你也被给予三个整数 sr ,  sc 和 newColor 。你应该从像素 image[sr][sc] 开始对图像进行 上色填充 。
     * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，
     * 接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。
     * 将所有有记录的像素点的颜色值改为 newColor 。
     * 使用暴力法：O（n）
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image == null) return image;
        helper = new boolean[image.length][image[0].length];
        floodFill(image, sr, sc, color);
        return image;
    }

    private void fill(int[][] image, int y, int x, int color) {
        int[][] around = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int tem = image[y][x];
        helper[y][x] = true;
        image[y][x] = color;
        for (int i = 0; i < around.length; i++) {
            int indexX = around[i][0] + x;
            int indexY = around[i][1] + y;
            if (indexX >= 0 && indexX < image[0].length
                    && indexY >= 0 && indexY < image.length
                    && !helper[indexY][indexX] && image[indexY][indexX] == tem) {
                floodFill(image, indexY, indexX, color);
            }
        }
    }

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    /* 将该块岛屿进行淹没 */
                    ++total;
                    flood(grid, i, j);
                }
            }
        }
        return total;
    }

    private void flood(char[][] gird, int x, int y) {
        if (x < 0 || y < 0 || x >= gird.length || y >= gird[0].length || gird[x][y] == '0') return;
        gird[x][y] = '0';
        flood(gird, x - 1, y);
        flood(gird, x + 1, y);
        flood(gird, x, y - 1);
        flood(gird, x, y + 1);
    }
}
