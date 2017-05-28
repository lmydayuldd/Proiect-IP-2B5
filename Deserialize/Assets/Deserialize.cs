﻿using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Xml;
using System.IO;


public class Deserialize : MonoBehaviour // the Class
{
    
    void Start()
    { 
        GetLevel();
    }

    public void GetLevel()
    {
        XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.

        xmlDoc.Load("C:\\Users\\Alex\\Documents\\Deserialize\\Assets\\format_date.xml"); // load the file.

        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.
        float x1=0.0f, x2=0.0f, y1=0.0f, y2=0.0f;
        foreach (XmlNode floorinfo2 in floorlist)
        {
            XmlNodeList roomcontent2 = floorinfo2.ChildNodes;

            foreach (XmlNode floorinfo in roomcontent2)
            {
                XmlNodeList roomcontent = floorinfo.ChildNodes;

                foreach (XmlNode roomstuff in roomcontent) 
                {
                    if (roomstuff.Name == "name")
                    {
                        //GameObject newCube3 = GameObject.CreatePrimitive(PrimitiveType.Cylinder);
                    }


                    if (roomstuff.Name == "type")
                    {
                        switch (roomstuff.Attributes["name"].Value)
                        {
                            case "wall":

                                XmlNodeList roomdimensions = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                                foreach (XmlNode dimension in roomdimensions) 
                                {
                                    if (dimension.Name == "x1")
                                    {
                                        string x1_str = dimension.InnerText;
                                        x1 = float.Parse(x1_str);
                                    }
                                    if (dimension.Name == "y1")
                                    {
                                        string y1_str = dimension.InnerText;
                                        y1 = float.Parse(y1_str);

                                    }
                                    if (dimension.Name == "x2")
                                    {
                                        string x2_str = dimension.InnerText;
                                        x2 = float.Parse(x2_str);
                                    }
                                    if (dimension.Name == "y2")
                                    {
                                        string y2_str = dimension.InnerText;
                                        y2 = float.Parse(y2_str);
                                    }
                                    LineRenderer lineRenderer;
                                    GameObject obj = new GameObject("line");
                                    lineRenderer = obj.AddComponent<LineRenderer>();

                                    lineRenderer.SetPosition(0, new Vector3(x1, 0, y1));
                                    lineRenderer.SetPosition(1, new Vector3(x2, 0, y2));
                                }
                                break;

                            case "door":
                                XmlNodeList roomdimensions2 = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                                foreach (XmlNode dimension in roomdimensions2) 

                                {
                                    if (dimension.Name == "x1")
                                    {
                                        string x1_str = dimension.InnerText;
                                        x1 = float.Parse(x1_str);
                                    }
                                    if (dimension.Name == "y1")
                                    {
                                        string y1_str = dimension.InnerText;
                                        y1 = float.Parse(y1_str);
                                    }
                                    if (dimension.Name == "x2")
                                    {
                                        string x2_str = dimension.InnerText;
                                        x2 = float.Parse(x2_str);
                                    }
                                    if (dimension.Name == "y2")
                                    {
                                        string y2_str = dimension.InnerText;
                                        y2 = float.Parse(y2_str);
                                    }
                                    LineRenderer lineRenderer;
                                    GameObject obj = new GameObject("line");
                                    lineRenderer = obj.AddComponent<LineRenderer>();

                                    lineRenderer.SetPosition(0, new Vector3(x1, 0, y1));
                                    lineRenderer.SetPosition(1, new Vector3(x2, 0, y2));
                                }
                                
                                break;
                        }
                    }


                   

                }
            }
        }
    }
}