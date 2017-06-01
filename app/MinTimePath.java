package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class MinTimePath extends PathFinder {

    public MinTimePath(Matrix m) {
        super(m);
    }


    @Override
    public ArrayList<Point> execute(Point source, Point dest) {
        if (!source.isValid(Matrix.LEVEL_COUNT, Matrix.DIMENSION, Matrix.DIMENSION) ||
                !dest.isValid(Matrix.LEVEL_COUNT, Matrix.DIMENSION, Matrix.DIMENSION))
            return null;

        Queue<Point> q = new LinkedList<>();

        int dx[] = {-1, 0, 0, 1, 0, 0, 1, -1, -1, 1};
        int dy[] = {0, 1, -1, 0, 0, 0, 1, 1, -1, -1};
        int dz[] = {0, 0, 0, 0, 1, -1, 0, 0, 0, 0};
        int n = Matrix.DIMENSION;
        int lvl = 4;//Matrix.LEVEL_COUNT;
        double dist[][][] = new double[lvl][n][n];
        int from[][][] = new int[lvl][n][n];

        for (int i = 0; i < lvl; ++i)
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    dist[i][j][k] = (int) 1e9 + 69 * 69;
        //Djok's magic number

        double magic = Math.sqrt(2);
        dist[source.getFloor()][source.getX()][source.getY()] = 0;
        for (q.add(source); !q.isEmpty(); q.poll()) {
            int x = q.element().getX();
            int y = q.element().getY();
            int z = q.element().getFloor();
            Cell now = matrix.getCell(z, x, y);
            if (now == null) continue;

            for (int dir = 0; dir < 6; ++dir) {
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

                Cell now2 = matrix.getCell(newz, newx, newy);
                if (now2 == null) continue;

                if (dir == 1) {
                    if (new Point(newx + 1, newy, newz).isValid(lvl, n, n) && (now2.getWalls() & 8) > 0) {
                        if (dist[newz][newx + 1][newy] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx + 1][newy] = dist[newz][newx][newy] + magic;
                            from[newz][newx + 1][newy] = 6;
                            q.add(new Point(newx + 1, newy, newz));
                        }
                    }

                    if (new Point(newx - 1, newy, newz).isValid(lvl, n, n) && (now2.getWalls() & 1) > 0) {
                        if (dist[newz][newx - 1][newy] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx - 1][newy] = dist[newz][newx][newy] + magic;
                            from[newz][newx - 1][newy] = 7;
                            q.add(new Point(newx - 1, newy, newz));
                        }
                    }
                }

                if (dir == 2) {
                    if (new Point(newx + 1, newy, newz).isValid(lvl, n, n) && (now2.getWalls() & 8) > 0) {
                        if (dist[newz][newx + 1][newy] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx + 1][newy] = dist[newz][newx][newy] + magic;
                            from[newz][newx + 1][newy] = 9;
                            q.add(new Point(newx + 1, newy, newz));
                        }
                    }

                    if (new Point(newx - 1, newy, newz).isValid(lvl, n, n)  && (now2.getWalls() & 1) > 0) {
                        if (dist[newz][newx - 1][newy] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx - 1][newy] = dist[newz][newx][newy] + magic;
                            from[newz][newx - 1][newy] = 8;
                            q.add(new Point(newx - 1, newy, newz));
                        }
                    }
                }

                if (dir == 0) {
                    if (new Point(newx, newy + 1, newz).isValid(lvl, n, n) && (now2.getWalls() & 2) > 0) {
                        if (dist[newz][newx][newy + 1] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx][newy + 1] = dist[newz][newx][newy] + magic;
                            from[newz][newx][newy + 1] = 7;
                            q.add(new Point(newx, newy + 1, newz));
                        }
                    }

                    if (new Point(newx, newy - 1, newz).isValid(lvl, n, n)  && (now2.getWalls() & 4) > 0) {
                        if (dist[newz][newx][newy - 1] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx][newy - 1] = dist[newz][newx][newy] + magic;
                            from[newz][newx][newy - 1] = 8;
                            q.add(new Point(newx, newy - 1, newz));
                        }
                    }
                }

                if (dir == 3) {
                    if (new Point(newx, newy + 1, newz).isValid(lvl, n, n) && (now2.getWalls() & 2) > 0) {
                        if (dist[newz][newx][newy + 1] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx][newy + 1] = dist[newz][newx][newy] + magic;
                            from[newz][newx][newy + 1] = 6;
                            q.add(new Point(newx, newy + 1, newz));
                        }
                    }

                    if (new Point(newx, newy - 1, newz).isValid(lvl, n, n)  && (now2.getWalls() & 4) > 0) {
                        if (dist[newz][newx][newy - 1] > dist[newz][newy][newy] + magic) {
                            dist[newz][newx][newy - 1] = dist[newz][newx][newy] + magic;
                            from[newz][newx][newy - 1] = 9;
                            q.add(new Point(newx, newy - 1, newz));
                        }
                    }
                }
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