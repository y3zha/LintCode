import java.util.Arrays;

/**
 * Dijkstra
 *
 * 有向
 */
public class _1057_网络延迟时间 {

    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] edge = new int[N + 1][N + 1];
        final int INF = 0x3f3f3f3f;
        //初始化
        for (int[] e : edge) {
            Arrays.fill(e, INF);
        }
        for (int i = 0; i < N + 1; i++) {
            edge[i][i] = 0;
        }
        for (int[] t : times) {
            // edge[t[0]][t[1]] = edge[t[1]][t[0]] = t[2];  //错误写法，这是双向写法
            //有向图只能是单边
            edge[t[0]][t[1]] = t[2];
        }

        int[] dist = new int[N + 1];
        boolean[] vis = new boolean[N + 1];

        //init dist K到其他节点的距离
        for (int i = 0; i < N + 1; i++) {
            dist[i] = edge[K][i];
        }
        //对剩余N-1哥顶点做处理，所以是N-1，dijkstra算法都是这样的
        for (int i = 0; i < N - 1; i++) {
            int min = INF;
            //从这个起始节点K开始，找到它所有的邻接点中与它最近的那个
            int start = K;
            for (int j = 1; j <= N; j++) {
                if (!vis[j] && dist[j] < min) {
                    min = dist[j];
                    start = j;
                }
            }
            //把与它最近的那个节点标记为访问过
            vis[start] = true;
            //从这个节点开始去更新路径
            for (int j = 1; j <= N; j++) {
                if (!vis[j] && dist[j] > dist[start] + edge[start][j]) {
                    dist[j] = dist[start] + edge[start][j];
                }
            }
        }
        //这样就拿到了K到其他节点的最短路径
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dist[i]);
        }
        if (res == INF) {
            return -1;
        } else {
            return res;
        }
    }
}