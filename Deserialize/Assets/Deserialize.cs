﻿using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Xml;
using System.IO;
using System;
using System.Net.Sockets;
using System.Linq;
public class Deserialize : MonoBehaviour // the Class
{
    public static List<string> camere1 = new List<string>() { "1" };
    public static List<string> etajePentruDd = new List<string>() { "Selectare Etaj" };

    public static List<string>[] camere = new List<string>[11];
    public static List<string> camere2 = new List<string>() { "1" };
    public static List<string> etaje = new List<string>() { "1" };
    public static int lastEtaj;
    public static string stringXml = "C:\\Users\\Alex\\Documents\\Deserialize\\Assets\\format_date.xml";
   public static string EtajString;

    void Start()
    {


    }
    private void Update()
    {
       
    }

    public static List<string> getCamere(int etaj)
    {
        return camere[etaj];
    }

    public static List<string> getEtaje()
    {

        return etaje;
    }
    public static List<string> getEtajePentruDd()
    {
        return etajePentruDd;
    }

    public static int getLastEtaj()
    {
        return lastEtaj;
    }


    public static IEnumerator GetLevel(int Etaj)
    {
        
        XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        xmlDoc.Load(stringXml); // load the file.

        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.
        float x1 = 0.0f, x2 = 0.0f, y1 = 0.0f, y2 = 0.0f;


        foreach (XmlNode floorinfo2 in floorlist) //adica fiecare <floor>
        {
            EtajString = Etaj.ToString();
            if (floorinfo2.Attributes["number"].Value.Equals(EtajString))
                    renderLevelForDisplay(floorinfo2);
             
        }
        yield return new WaitForSeconds(0);

    }
     public static IEnumerator GetLevelsForDropDown(float waitTime, Action Populare)
    {
        XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        xmlDoc.Load(stringXml); // load the file.
        /*
        List<string> etaj = new List<string>();
        List<string> camera_etaj0 = new List<string>();
        List<string> camera_etaj1 = new List<string>();
        List<string> camera_etaj2 = new List<string>();
        */
        
        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.
        float x1 = 0.0f, x2 = 0.0f, y1 = 0.0f, y2 = 0.0f;


        foreach (XmlNode floorinfo2 in floorlist) //adica fiecare <floor>
        {
            renderLevelForDropDown(floorinfo2, Int32.Parse(floorinfo2.Attributes["number"].Value));
            etajePentruDd.Add(floorinfo2.Attributes["number"].Value);
        }
        lastEtaj = Int32.Parse(etajePentruDd[etajePentruDd.Count-1]);

        yield return new WaitForSeconds(waitTime);
        Populare();
    }

    public static void renderLevelForDisplay(XmlNode floorinfo2)
    {
        XmlNodeList roomcontent2 = floorinfo2.ChildNodes;
        float x1 = 0.0f, x2 = 0.0f, y1 = 0.0f, y2 = 0.0f;

        foreach (XmlNode floorinfo in roomcontent2)
        {
            XmlNodeList roomcontent = floorinfo.ChildNodes;

            foreach (XmlNode roomstuff in roomcontent)
            {
                if (roomstuff.Name == "name")
                {
                    string nume = roomstuff.InnerText;

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

                            }
                            LineRenderer lineRenderer;
                            GameObject obj = new GameObject("line");
                            obj.gameObject.tag = "naspa";
                            lineRenderer = obj.AddComponent<LineRenderer>();
                            lineRenderer.SetWidth(0.5f, 0.5f);
                            lineRenderer.SetColors(Color.white, Color.white);
                            lineRenderer.SetPosition(0, new Vector3(x1, 0, y1));
                            lineRenderer.SetPosition(1, new Vector3(x2, 0, y2));
                            Material whiteDiffuseMat = new Material(Shader.Find("Sprites/Default"));
                            lineRenderer.material = whiteDiffuseMat;
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
                            }

                            LineRenderer lineRenderer2;
                            GameObject obj2 = new GameObject("line_usa");

                            obj2.gameObject.tag = "naspa";
                            lineRenderer2 = obj2.AddComponent<LineRenderer>();
                            lineRenderer2.SetWidth(0.5f, 0.5f);
                            lineRenderer2.SetColors(Color.black, Color.black);
                            lineRenderer2.SetPosition(0, new Vector3(x1, 0.35f, y1));
                            lineRenderer2.SetPosition(1, new Vector3(x2, 0.35f, y2));
                            Material blackDiffuseMat = new Material(Shader.Find("Sprites/Default"));
                            lineRenderer2.material = blackDiffuseMat;
                            break;

                        case "window":

                            XmlNodeList roomdimensions3 = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                            foreach (XmlNode dimension in roomdimensions3)
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

                            }
                            LineRenderer lineRenderer3;
                            GameObject obj3 = new GameObject("line-window");

                            obj3.gameObject.tag = "naspa";
                            lineRenderer3 = obj3.AddComponent<LineRenderer>();
                            lineRenderer3.SetWidth(0.9f, 0.9f);
                            lineRenderer3.SetColors(Color.blue, Color.blue);
                            lineRenderer3.SetPosition(0, new Vector3(x1, 0, y1));
                            lineRenderer3.SetPosition(1, new Vector3(x2, 0, y2));
                            Material blueDiffuseMat = new Material(Shader.Find("Sprites/Default"));
                            lineRenderer3.material = blueDiffuseMat;
                            break;

                    }
                }




            }
        }

    }

    public static void renderLevelForDropDown(XmlNode floorinfo2, int Etaj)
    {
        if (camere[Etaj] == null)
            camere[Etaj] = new List<string>();

        float x1 = 0.0f, x2 = 0.0f, y1 = 0.0f, y2 = 0.0f;

        XmlNodeList roomcontent2 = floorinfo2.ChildNodes;

        foreach (XmlNode floorinfo in roomcontent2)
        {
            XmlNodeList roomcontent = floorinfo.ChildNodes;

            foreach (XmlNode roomstuff in roomcontent)
            {
                if (roomstuff.Name == "name")
                {
                    string nume = roomstuff.InnerText;
                    camere[Etaj].Add(nume);
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
                            }

                            break;

                        case "window":

                            XmlNodeList roomdimensions3 = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                            foreach (XmlNode dimension in roomdimensions3)
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

                            }

                            break;

                    }
                }




            }
        }

    }

}