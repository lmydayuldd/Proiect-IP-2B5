package modul3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class MinDistancePath extends PathFinder {

    public MinDistancePath(Matrix m) {
        super(m);
    }

    @Override
    public void execute(Point source, Point dest) {
         
        /*Queue<Point> q = new LinkedList<>();

        int dx[] = {-1, 0, 0, 1};
        int dy[] = {0, 1, -1, 0};
        int N = matrix.DIMENSION;

        for (q.add(source); !q.isEmpty(); q.poll()) {
            Cell front = q.element();

            for (int dir = 0; dir < 4; ++dir) {
                if ((front.walls & (1 << dir)) != 0)
                    continue;
                int newx = front.coord.x + dx[dir];
                int newy = front.coord.y + dy[dir];
                if (newx < 0 || newx >= N)
                    continue;
                if (newy < 0 || newy >= N)
                    continue;

                
            }
        }*/
    }

    @Override
    public void execute(ArrayList<Point> path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}