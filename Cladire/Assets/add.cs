using UnityEngine;
using System.Collections;
using System.Collections.Generic;


public class add : MonoBehaviour {

    private float[] walls;
    public Transform target;
    public float smoothTime = 0.3f;

    private Vector3 velocity = Vector3.zero;

    //                        [0]     [1]     [2]     [3]     [4]       [5]
    public void createCube(int type, int x1, int y1, int x2, int y2, int r)
    {// 1-wall 2-door 3-window
        GameObject cube = GameObject.CreatePrimitive(PrimitiveType.Cube);
        cube.transform.position = new Vector3(
                                                Mathf.Abs(x1 + x2) * 0.5f,  // X
                                                0.05f,                     // Y
                                                Mathf.Abs(y1 + y2) * 0.5f   // Z
                                             );

        if (type == 1)
        {
            cube.GetComponent<Renderer>().material.color = Color.white;
            if (x1 == x2)
                cube.transform.localScale = new Vector3(
                                                        Mathf.Abs(1) * 0.5f,    // X
                                                        0.1f,          // Y
                                                        Mathf.Abs(y1 - y2) * 1f // Z
                                                       );
            else if(y1 == y2)
                cube.transform.localScale = new Vector3(
                                                        Mathf.Abs(1) * 0.5f,     // X
                                                        0.1f,
                                                        Mathf.Abs(x1 - x2) * 1f + 0.5f  // Z
                                                       );
        }
        else if (type == 2)
        {
            cube.GetComponent<Renderer>().material.color = Color.black;
            if(y1 == y2) cube.transform.localScale = new Vector3(
                                                    Mathf.Abs(1) * 0.5f + 0.1f,     // X
                                                    0.15f,                // Y
                                                    Mathf.Abs(x1 - x2) * 1f  // Z
                                                   );
            else if(x1 == x2) cube.transform.localScale = new Vector3(
                                                    Mathf.Abs(1) * 0.5f + 0.1f, // X
                                                    0.15f,            // Y
                                                    Mathf.Abs(y1 - y2) * 1f     // Z
                                                   );
        }
        /*cube.transform.position = new Vector3(
                                                Mathf.Abs(x1 + x2) * 0.5f,  // X
                                                1 * 1f,                     // Y
                                                Mathf.Abs(y1 + y2) * 0.5f   // Z
                                             );
*/
        if (r == 90) cube.transform.Rotate(90 * Vector3.up);
    }
 
    void Start () {
        List<int[]> doors = new List<int[]>();
        int[] x = new int[] { 32, 17, 34, 17, 90 }; doors.Add(x);
        x = new int[] { 36, 17, 38, 17, 90 }; doors.Add(x);
        x = new int[] { 8, 1, 8, 3, 0 }; doors.Add(x);
        x = new int[] { 6, 6, 6, 7, 0 }; doors.Add(x);
        x = new int[] { 6, 9, 6, 10, 0 }; doors.Add(x);
        x = new int[] { 1, 20, 2, 20, 90 }; doors.Add(x);
        x = new int[] { 4, 20, 5, 20, 90 }; doors.Add(x);
        x = new int[] { 6, 22, 7, 22, 90 }; doors.Add(x);
        x = new int[] { 14, 22, 15, 22, 90 }; doors.Add(x);
        x = new int[] { 17, 22, 18, 22, 90 }; doors.Add(x);

        List<int[]> walls = new List<int[]>();
        x = new int[] { 0, 0, 20, 0, 90 }; walls.Add(x);   //  0
        x = new int[] { 0, 0, 0, 25, 0 }; walls.Add(x);        //  1
        x = new int[] { 0, 25, 60, 25, 90 }; walls.Add(x);       //  2
        x = new int[] { 60, 11, 60, 25, 0 }; walls.Add(x);     //  3
        x = new int[] { 20, 11, 60, 11, 90 }; walls.Add(x);      //  4
        x = new int[] { 20, 0, 20, 12, 0 }; walls.Add(x);      //  5
        x = new int[] { 55, 17, 55, 25, 0 }; walls.Add(x);     //  6   
        x = new int[] { 20, 17, 55, 17, 90 }; walls.Add(x);      //  7
        x = new int[] { 35, 17, 35, 25, 0 }; walls.Add(x);     //  8
        x = new int[] { 20, 17, 20, 25, 0 }; walls.Add(x);     //  9
        x = new int[] { 6, 0, 6, 20, 0 }; walls.Add(x);        //  10
        x = new int[] { 8, 0, 8, 20, 0 }; walls.Add(x);        //  11
        x = new int[] { 15, 0, 15, 20, 0 }; walls.Add(x);      //  12
        x = new int[] { 8, 9, 15, 9, 90 }; walls.Add(x);         //  13
        x = new int[] { 8, 20, 15, 20, 90 }; walls.Add(x);       //  14
        x = new int[] { 15, 8, 20, 8, 90 }; walls.Add(x);        //  15
        x = new int[] { 0, 8, 6, 8, 90 }; walls.Add(x);          //  16
        x = new int[] { 0, 16, 6, 16, 90 }; walls.Add(x);        //  17
        x = new int[] { 0, 18, 6, 18, 90 }; walls.Add(x);        //  18
        x = new int[] { 0, 20, 6, 20, 90 }; walls.Add(x);        //  19
        x = new int[] { 12, 22, 20, 22, 90 }; walls.Add(x);      //  20
        x = new int[] { 0, 22, 8, 22, 90 }; walls.Add(x);        //  21
        x = new int[] { 3, 18, 3, 20, 0 }; walls.Add(x);       //  22
        x = new int[] { 8, 22, 8, 25, 0 }; walls.Add(x);       //  23
        x = new int[] { 12, 22, 12, 25, 0 }; walls.Add(x);     //  24
        x = new int[] { 16, 22, 16, 25, 0 }; walls.Add(x);     //  25


        /* DOORS */
        for (int i = 0; i < 10; i++)
            createCube(2, doors[i][0], doors[i][1], doors[i][2], doors[i][3], doors[i][4]);


        /* WALLS */
        for (int i = 0; i < 26; i++)
            createCube(1, walls[i][0], walls[i][1], walls[i][2], walls[i][3], walls[i][4]);
}


    void Update() {
        Vector3 goalPos = target.position;
        goalPos.y = transform.position.y;
        transform.position = Vector3.SmoothDamp(transform.position, goalPos, ref velocity, smoothTime);
    }
}
