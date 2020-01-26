import java.util.ArrayList;
import java.util.List;

/**
 * 本质是全排列
 */
public class _033_N皇后 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean[] visited = new boolean[n];
        dfs(n, new ArrayList<Integer>(), res, visited);
        return res;
    }

    //list中放的是每一行的皇后放在第几列的坐标
    private void dfs(int n, ArrayList<Integer> list, List<List<String>> res, boolean[] visited) {
        if (list.size() == n) {
            res.add(drawChessboard(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && isValid(list, i)) {
                visited[i] = true;
                list.add(i);
                dfs(n, list, res, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    private boolean isValid(ArrayList<Integer> list, int colIndex) {
        int rows = list.size();
        //遍历之前的每一行
        for (int i = 0; i < rows; i++) {
            //如果在同一列
            if (list.get(i) == colIndex) {
                return false;
            }

            //如果都在 y = -x 上
            if (i - list.get(i) == rows - colIndex) {
                return false;
            }

            //如果都在 y = x 上
            if (i + list.get(i) == rows + colIndex) {
                return false;
            }
        }
        return true;
    }


    private List<String> drawChessboard(ArrayList<Integer> list) {
        List<String> res = new ArrayList<>();
        //list中存放的是每一行的皇后再第几列
        int n = list.size();
        for (Integer index : list) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i == index) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}