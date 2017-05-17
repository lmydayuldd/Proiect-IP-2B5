package modul3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class MinDistancePath extends PathFinder {

    public MinDistancePath(Matrix m) {
        super(m);
    }

    @Override
    public Integer execute(Point source, Point dest) {

        Queue<Point> q = new LinkedList<>();

        int dx[] = {-1, 0, 0, 1, 0, 0};
        int dy[] = {0, 1, -1, 0, 0, 0};
        int dz[] = {0, 0, 0, 0, 1, -1};
        int n = matrix.DIMENSION;
        int lvl = matrix.LEVEL_COUNT;
        int dist[][][] = new int[lvl][n][n];

        for (int i = 0; i < lvl; ++i)
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    dist[i][j][k] = (int) 1e9 + 69 * 69;

        dist[source.getFloor()][source.getX()][source.getY()] = 0;
        for (q.add(source); !q.isEmpty(); q.poll()) {
            int x = q.element().getX();
            int y = q.element().getY();
            int z = q.element().getFloor();
            Cell now = matrix.getCell(x, y, z);

            for (int dir = 0; dir < 6; ++dir) {
                if ((now.walls & (1 << dir)) != 0)
                    continue;
                int newx = x + dx[dir];
                int newy = y + dy[dir];
                int newz = z + dz[dir];
                if (newx < 0 || newx >= n)
                    continue;
                if (newy < 0 || newy >= n)
                    continue;
                if (newz < 0 || newz >= lvl)
                    continue;
                if (dist[newz][newx][newy] <= dist[z][x][y] + 1)
                    continue;
                dist[newz][newx][newz] = dist[z][x][y] + 1;
                q.add(new Point(newx, newy, newz));
            }
        }

        if (dist[dest.getFloor()][dest.getX()][dest.getY()] > (int) 1e9)
            return -1;

        return dist[dest.getFloor()][dest.getX()][dest.getY()];
    }

    @Override
    public void execute(ArrayList<Point> path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}