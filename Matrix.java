package modul3;


import java.util.Vector;



public class Matrix {
    
  protected final int DIMENSION = 500;
  protected final int LEVEL_COUNT = 10;
  
  public Cell[][][] matrix;
  
  PathFinder pathFinder;

  public Matrix() {
      
      pathFinder = new SimplePath();
      
      /**
       * asdf
       * This is a matrix of cells, representing the building.
       * The first dimension of the 3D table represents the floor
       * The last two dimensions represent the coordinates of the cell in the floor
       * Each floor is mapped in a Cartesian system xOy.
       * matrix[k][i][j] represents the cell located at floor k
       *    with coordinates (i, j).
       */
      Cell[][][] matrix = new Cell[LEVEL_COUNT][DIMENSION][DIMENSION];
  }
  
  public void setPathFinder(PathFinder pf)
  {
      pathFinder = pf;
  }
  
  
  
  
  
}