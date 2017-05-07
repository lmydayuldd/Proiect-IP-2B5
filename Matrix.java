package modul3;


import java.util.Vector;



public class Matrix {
    
  protected final int DIMENSION = 500;
  protected final int LEVEL_COUNT = 10;
  
  public Cell[][][] matrix;
  
  PathFinder pathFinder;

  public Matrix() {
      
      pathFinder = new SimplePath();
      Cell[][][] matrix = new Cell[LEVEL_COUNT][DIMENSION][DIMENSION];
  }
  
  public void setPathFinder(PathFinder pf)
  {
      pathFinder = pf;
  }
  
  
  
  
  
}