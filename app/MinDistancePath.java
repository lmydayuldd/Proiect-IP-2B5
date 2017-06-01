package app;

import java.util.*;


public class MinDistancePath extends PathFinder {

    public MinDistancePath(Matrix m) {
        super(m);
    }

    @Override
    public ArrayList<Point> execute(Point source, Point dest) {
        if (!source.isValid(Matrix.LEVEL_COUNT, Matrix.DIMENSION, Matrix.DIMENSION) ||
                !dest.isValid(Matrix.LEVEL_COUNT, Matrix.DIMENSION, Matrix.DIMENSION))
            return null;

        Queue<Point> q = new LinkedList<>();

        int dx[] = {-1, 0, 0, 1, 0, 0};
        int dy[] = {0, 1, -1, 0, 0, 0};
        int dz[] = {0, 0, 0, 0, 1, -1};
        int n = Matrix.DIMENSION;
        int lvl = Matrix.LEVEL_COUNT;
        int dist[][][] = new int[lvl][n][n];
        int from[][][] = new int[lvl][n][n];

        for (int i = 0; i < lvl; ++i)
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    dist[i][j][k] = (int) 1e9 + 69 * 69;
        //Djok's magic number

        dist[source.getFloor()][source.getX()][source.getY()] = 0;
        for (q.add(source); !q.isEmpty(); q.poll()) {
            int x = q.element().getX();
            int y = q.element().getY();
            int z = q.element().getFloor();
            Cell now = matrix.getCell(x, y, z);
            if(now == null) continue;
            for (int dir = 0; dir < 6; ++dir) {
                //System.out.println("");
                if ((now.getWalls() & (1 << dir)) != 0)
                    continue;
                int newx = x + dx[dir];
                int newy = y + dy[dir];
                int newz = z + dz[dir];
                if (!(new Point(newx, newy, newz).isValid(lvl, n, n)))
                    continue;
                if (dist[newz][newx][newy] <= dist[z][x][y] + 1)
                    continue;
                dist[newz][newx][newy] = dist[z][x][y] + 1;
                from[newz][newx][newy] = dir;
                q.add(new Point(newx, newy, newz));
            }
        }

        if (dist[dest.getFloor()][dest.getX()][dest.getY()] > (int) 1e9)
            return null;

        ArrayList<Point> ans = new ArrayList<>();

        int x = dest.getX();
        int y = dest.getY();
        int z = dest.getFloor();
        int xx = source.getX();
        int yy = source.getY();
        int zz = source.getFloor();
        while (true) {
            ans.add(new Point(x, y, z));
            if (x == xx && y == yy && z == zz)
                break;
            int dir = from[z][x][y];
            x -= dx[dir];
            y -= dy[dir];
            z -= dz[dir];
        }
        Collections.reverse(ans);

        return ans;
    }

}