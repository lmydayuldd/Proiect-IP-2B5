package app;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tamara on 28.05.2017.
 */
public class SomeTest {

    @Test
    public void testMatrixCell1() {
        Cell cell = new Cell(1, 1);
        Matrix matrix = new Matrix();
        matrix.setCell(cell, 1, 2, 1);
        Assert.assertEquals(cell.getFree(), (matrix.getCell(1, 2, 1)).getFree());
        Assert.assertEquals(cell.getWalls(), (matrix.getCell(1, 2, 1)).getWalls());
    }

    @Test
    public void testMatrixCell2() {
        Cell cell = new Cell(1, 1);
        Matrix matrix = new Matrix();
        matrix.setCell(cell, 1, 2, 1);
        assertFalse(0 == (matrix.getCell(1, 2, 1)).getFree());
    }

    @Test
    public void testMatrixCell3() {
        Matrix matrix = new Matrix();
        assertTrue(null == matrix.getCell(1, 2, 3));
    }

    //test pentru validitatea coordonatelor (Point)
    @Test
    public void testPoint1() {
        Point p = new Point(1, 4, 9);
        Assert.assertEquals(1, p.getX());
        Assert.assertEquals(4, p.getY());
        Assert.assertEquals(9, p.getFloor());
    }

    @Test
    public void testPoint2() {
        Point p = new Point(-1, 4, 9);
        assertFalse(-1 == p.getX());
    }

    @Test
    public void testPoint3() {
        Point p = new Point(1, 4, 11);
        assertFalse(11 == p.getFloor());
    }

    //teste pentru crearea peretilor in RawMatrix
    @Test
    public void testRawMatrix1() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(2, 1, 2, 10, 3);

        int[][][] xmat = sample.getRawMatrix();
        /*
        for(int i=1; i<=15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }
        */
        assertTrue(1 == sample.getRawMatrixCell(2, 7, 3));
    }

    @Test
    public void testRawMatrix2() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(2, 1, 5, 10, 3);
        /*
        int[][][] xmat = sample.getRawMatrix();

        for(int i=1; i<=15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }
        */
        assertTrue(1 == sample.getRawMatrixCell(3, 4, 3));
    }

    @Test
    public void testRawMatrix3() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(2, 1, 100, 200, 3);
        /*
        int[][][] xmat = sample.getRawMatrix();

        for(int i=1; i<=15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }
        */
        assertTrue(1 == sample.getRawMatrixCell(3, 4, 3));
    }

    @Test
    public void testRawMatrix4() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(2, 1, 2, 2, 3);
        /*
        int[][][] xmat = sample.getRawMatrix();

        for(int i=1; i<=15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }
        */
        assertTrue(1 == sample.getRawMatrixCell(2, 1, 3));
    }

    @Test
    public void testRawMatrix5() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(1, 1, 1, 100, 3);

        int[][][] xmat = sample.getRawMatrix();

        for (int i = 1; i <= 15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }

        assertTrue(1 == xmat[3][1][16]);
    }

    @Test
    public void testRawMatrix6() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(100, 1, 1, 1, 3);

        int[][][] xmat = sample.getRawMatrix();

        for (int i = 1; i <= 15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }

        assertTrue(1 == xmat[3][16][1]);
    }

    //part2
    @Test
    public void testRawMatrix7() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(100, 100, 1, 1, 3);

        int[][][] xmat = sample.getRawMatrix();

        for (int i = 1; i <= 15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }

        assertTrue(1 == xmat[3][1][1]);
    }

    @Test
    public void testRawMatrix8() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(100, 100, 2, 3, 3);

        int[][][] xmat = sample.getRawMatrix();

        for (int i = 1; i <= 15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }

        assertTrue(1 == xmat[3][2][3]);
    }

    //fix FillWall
    @Test
    public void testRawMatrix9() {
        XmlBuildingParser sample = new XmlBuildingParser("exXML");
        sample.fillWall(100, 1, 2, 70, 3);

        int[][][] xmat = sample.getRawMatrix();

        for (int i = 1; i <= 15; ++i) {
            for (int j = 1; j <= 15; ++j)
                System.out.print(xmat[3][i][j] + " ");
            System.out.println();
        }

        assertTrue(1 == xmat[3][2][3]);
    }

}
