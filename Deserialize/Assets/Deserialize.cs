using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Xml;
using System.IO;
using System.Net;
using System.Security.Cryptography.X509Certificates;
using System.Net.Security;
using System;
using System.Net.Sockets;
using UnityEngine.UI;

public class Deserialize : MonoBehaviour // the Class
{  
    public static List<string> camere1 = new List<string>() { "1" };
    public static List<string> etajePentruDd = new List<string>() { "Selectare Etaj" };

    public static List<string>[] camere = new List<string>[11];
    public static List<string>[] usi = new List<string>[50];

    public static List<string> camere2 = new List<string>() { "1" };
    public static List<string> etaje = new List<string>() { "1" };
    public static int lastEtaj;
    public static string stringXml = "D:\\format_date_nou.xml";
    public static string stringXmlPath = "D:\\format_date_gol.xml";
    public static string camera_oficial;
    public static string EtajString;

    public static string x1String, x2String, y1String, y2String;
    static String sala_scris;
    public static float mouseSensitivity = 0.05F;
    public static Vector3 lastPosition;

    public static float xMin=101, yMin=101,xMax=0,yMax=0;
    public static GameObject vesselSegment;
    /*
    static void SpawnGround(Vector3 pA, Vector3 pB)
    {
        GameObject ground = GameObject.CreatePrimitive(PrimitiveType.Cube);
        Vector3 between = pB - pA;
        float distance = between.magnitude;
       ground.transform.localScale=new Vector3(distance,1,1);
       // x = distance;
        ground.transform.position = pA + (between / 2.0f);
        ground.transform.LookAt(pB);
    }*/
    static void create_3D(Vector3 p1, Vector3 p2,int option)
    {
        Vector3 pos = Vector3.Lerp(p1, p2, (float)0.5);

      //  vesselSegment = Resources.Load("Cube") as GameObject;
        GameObject segObj = GameObject.CreatePrimitive(PrimitiveType.Cube);
        segObj.tag = "naspa";
        if(option==1)segObj.name = "Perete";
        if (option == 2) segObj.name = "Stairs-Wall";
        if (option == 3) segObj.name = "Fereastra";
        if (option == 4) segObj.name = "Usa";

        Vector3 newScale = segObj.transform.localScale;
        if (option != 4) newScale.x = Vector3.Distance(p1, p2)+0.5f;
        else newScale.x = Vector3.Distance(p1, p2)-0.5f;
        if (option == 3) newScale.y = 2.5f;
        else newScale.y = 3;
        if (option == 3) newScale.z = 0.8f;
        else newScale.z = 0.5f;
        segObj.transform.localScale = newScale;
        
        segObj.transform.position = pos;
        //segObj.transform.up = p2 - p1;
        segObj.transform.LookAt(p2);
        
        Vector3 eulerAngleValues = segObj.transform.rotation.eulerAngles;
       if(option==1) segObj.GetComponent<Renderer>().material.color = Color.white;
        else if (option==2)segObj.GetComponent<Renderer>().material.color = Color.red;
        else if (option == 3) segObj.GetComponent<Renderer>().material.color = Color.blue;
        else if (option == 4) segObj.GetComponent<Renderer>().material.color = Color.black;

        Debug.Log(eulerAngleValues);
        if (eulerAngleValues.y == 0)
        {
            segObj.transform.rotation = Quaternion.Euler(0, 90, 0);
        }
        else if (eulerAngleValues.y == 270 || eulerAngleValues.y == 90)
        {
            segObj.transform.rotation = Quaternion.Euler(0, 0, 0);

        }
        else if (eulerAngleValues.y == 180)
        {
            segObj.transform.rotation = Quaternion.Euler(0, 90, 0);
        }
    }
    
    /*
   static void SpawnGround2(Vector3 startPos, Vector3 endPos)
    {
        GameObject ground = GameObject.CreatePrimitive(PrimitiveType.Cube);
        Vector3 centerPos = new Vector3(startPos.x + endPos.x, startPos.y + endPos.y) / 2;

        float scaleX = Mathf.Abs(startPos.x - endPos.x);
        float scaleY = Mathf.Abs(startPos.y - endPos.y);

        centerPos.x -= 0.5f;
        centerPos.y += 0.5f;
        ground.transform.position = centerPos;
        ground.transform.localScale = new Vector3(scaleX, scaleY, 1);
    }*/


    void Start()
    {
        ServicePointManager.ServerCertificateValidationCallback = MyRemoteCertificateValidationCallback;


    }
    private void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            lastPosition = Input.mousePosition;
        }

        if (Input.GetMouseButton(0))
            {
                Vector3 delta = Input.mousePosition - lastPosition;
                transform.Translate(-delta.x * mouseSensitivity, -delta.y * mouseSensitivity, 0);
                lastPosition = Input.mousePosition;
            }
    }

    public static List<string> getCamere(int etaj)
    {
        return camere[etaj];
    }
    public static List<string> getUsa(int etaj,string camera) //comunic cu modul 1
    {

        
        WebRequest request = WebRequest.Create("http://localhost:4500/getXML");
        // If required by the server, set the credentials.
        request.Credentials = CredentialCache.DefaultCredentials;
        // Get the response.
        HttpWebResponse response = (HttpWebResponse)request.GetResponse();
        // Get the stream containing content returned by the server.
        Stream dataStream = response.GetResponseStream();
        // Open the stream using a StreamReader for easy access.
        StreamReader reader = new StreamReader(dataStream);
        // Read the content. 
        string responseFromServer = reader.ReadToEnd();


        XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        xmlDoc.LoadXml(responseFromServer); // load the file.
        
        camera_oficial = camera;

       //XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
      // xmlDoc.Load(stringXml); // load the file.


        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.


        foreach (XmlNode floorinfo2 in floorlist) //adica fiecare <floor>
        {
            renderLevelForDropDownDoor(floorinfo2, etaj);

        }

        return usi[etaj];
    }
    public static void setUsi(int index)
    {
        usi[index].Clear();
    }
    public static void setCamere(int index)
    {
        camere[index].Clear();
    }
    public static string getCamera()
    {
        return camera_oficial;
    }

    public static List<string> getEtaje()
    {
        return etaje;
    }
    public static List<string> getEtajePentruDd()
    {
        return etajePentruDd;
    }
    public static void setEtajePentruDd()
    {
       etajePentruDd.Clear();
        etajePentruDd.Add("Selectare Etaj");
    }

    public static int getLastEtaj()
    {
        return lastEtaj;
    }

  

    public static IEnumerator GetLevel(int Etaj) //comunic cu modul 1
    {
        
         WebRequest request = WebRequest.Create("http://localhost:4500/getXML");
        // If required by the server, set the credentials.
        request.Credentials = CredentialCache.DefaultCredentials;
        // Get the response.
        HttpWebResponse response = (HttpWebResponse)request.GetResponse();
        // Get the stream containing content returned by the server.
        Stream dataStream = response.GetResponseStream();
        // Open the stream using a StreamReader for easy access.
        StreamReader reader = new StreamReader(dataStream);
        // Read the content. 
        string responseFromServer = reader.ReadToEnd();
        

        XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        xmlDoc.LoadXml(responseFromServer); // load the file.
        
        
       // XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        //xmlDoc.Load(stringXml); // load the file.

        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.
       

        foreach (XmlNode floorinfo2 in floorlist) //adica fiecare <floor>
        {
            EtajString = Etaj.ToString();
            if (floorinfo2.Attributes["number"].Value.Equals(EtajString))
                renderLevelForDisplay(floorinfo2);

        }
        yield return new WaitForSeconds(0);

    }

    public static IEnumerator GetLevelForCamera(int Etaj) //comunic cu modul 1
    {
        
         WebRequest request = WebRequest.Create("http://localhost:4500/getXML");
        // If required by the server, set the credentials.
        request.Credentials = CredentialCache.DefaultCredentials;
        // Get the response.
        HttpWebResponse response = (HttpWebResponse)request.GetResponse();
        // Get the stream containing content returned by the server.
        Stream dataStream = response.GetResponseStream();
        // Open the stream using a StreamReader for easy access.
        StreamReader reader = new StreamReader(dataStream);
        // Read the content. 
        string responseFromServer = reader.ReadToEnd();
        

        XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        xmlDoc.LoadXml(responseFromServer); // load the file.
        

      //  XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        //xmlDoc.Load(stringXml); // load the file.

        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.


        foreach (XmlNode floorinfo2 in floorlist) //adica fiecare <floor>
        {
            EtajString = Etaj.ToString();
            if (floorinfo2.Attributes["number"].Value.Equals(EtajString))
                renderLevelForDisplayCamera(floorinfo2);

        }
        yield return new WaitForSeconds(0);

    }
    public static IEnumerator GetLevelPath(int Etaj)
    {

         XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
         xmlDoc.Load(stringXmlPath); // load the file.

        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.


        foreach (XmlNode floorinfo2 in floorlist) //adica fiecare <floor>
        {
            EtajString = Etaj.ToString();
            if (floorinfo2.Attributes["number"].Value.Equals(EtajString))
                renderLevelForDisplay(floorinfo2);

        }
        yield return new WaitForSeconds(0);

    }



    public bool MyRemoteCertificateValidationCallback(System.Object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors sslPolicyErrors)
    {
        bool isOk = true;
        // If there are errors in the certificate chain, look at each error to determine the cause.
        if (sslPolicyErrors != SslPolicyErrors.None)
        {
            for (int i = 0; i < chain.ChainStatus.Length; i++)
            {
                if (chain.ChainStatus[i].Status != X509ChainStatusFlags.RevocationStatusUnknown)
                {
                    chain.ChainPolicy.RevocationFlag = X509RevocationFlag.EntireChain;
                    chain.ChainPolicy.RevocationMode = X509RevocationMode.Online;
                    chain.ChainPolicy.UrlRetrievalTimeout = new TimeSpan(0, 1, 0);
                    chain.ChainPolicy.VerificationFlags = X509VerificationFlags.AllFlags;
                    bool chainIsValid = chain.Build((X509Certificate2)certificate);
                    if (!chainIsValid)
                    {
                        isOk = false;
                    }
                }
            }
        }
        return isOk;
    }




    public void Connect(String server, String message)
    {
        try
        {
            // Create a TcpClient.
            // Note, for this client to work you need to have a TcpServer
            // connected to the same address as specified by the server, port
            // combination.
            Int32 port = 6969;
            TcpClient client = new TcpClient(server, port);

            // Translate the passed message into ASCII and store it as a Byte array.
            Byte[] data = System.Text.Encoding.ASCII.GetBytes(message);

            // Get a client stream for reading and writing.
            //  Stream stream = client.GetStream();

            NetworkStream stream = client.GetStream();

            // Send the message to the connected TcpServer.
            stream.Write(data, 0, data.Length);

            Console.WriteLine("Sent: {0}", message);

            // Close everything.
            stream.Close();
            client.Close();
        }
        catch (ArgumentNullException e)
        {
            Console.WriteLine("ArgumentNullException: {0}", e);
        }
        catch (SocketException e)
        {
            Console.WriteLine("SocketException: {0}", e);
        }

        Console.WriteLine("\n Press Enter to continue...");
        Console.Read();
    }

    public static IEnumerator GetLevelsForDropDown(float waitTime, Action Populare) //aici comunic cu modul 1
    {

       
        WebRequest request = WebRequest.Create("http://localhost:4500/getXML");
        // If required by the server, set the credentials.
        request.Credentials = CredentialCache.DefaultCredentials;
        // Get the response.
        HttpWebResponse response = (HttpWebResponse)request.GetResponse();
        // Get the stream containing content returned by the server.
        Stream dataStream = response.GetResponseStream();
        // Open the stream using a StreamReader for easy access.
        StreamReader reader = new StreamReader(dataStream);
        // Read the content. 
        string responseFromServer = reader.ReadToEnd();


        XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        xmlDoc.LoadXml(responseFromServer); // load the file.
        
        // XmlDocument xmlDoc = new XmlDocument(); // xmlDoc is the new xml document.
        // xmlDoc.Load(stringXml); // load the file.


        XmlNodeList floorlist = xmlDoc.GetElementsByTagName("floor"); // array of the level nodes.
        float x1 = 0.0f, x2 = 0.0f, y1 = 0.0f, y2 = 0.0f;


        foreach (XmlNode floorinfo2 in floorlist) //adica fiecare <floor>
        {
            renderLevelForDropDown(floorinfo2, Int32.Parse(floorinfo2.Attributes["number"].Value));
            etajePentruDd.Add(floorinfo2.Attributes["number"].Value);
        }
        lastEtaj = Int32.Parse(etajePentruDd[etajePentruDd.Count - 1]);

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
                    sala_scris = nume;
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
                                    x1String=x1_str;
                                    x1 = float.Parse(x1_str);
                                    if (x1 < xMin) xMin = x1;
                                    if (x1 >= xMax) xMax = x1;
                                }
                                if (dimension.Name == "y1")
                                {
                                    string y1_str = dimension.InnerText;
                                    y1String=y1_str;
                                    y1 = float.Parse(y1_str);
                                    if (y1 < yMin) yMin = y1;
                                    if (y1 >= yMax) yMax = y1;
                                }
                                if (dimension.Name == "x2")
                                {
                                    string x2_str = dimension.InnerText;
                                    x2String=x2_str;
                                    x2 = float.Parse(x2_str);
                                    if (x2 < xMin) xMin = x2;
                                    if (x2 >= xMax) xMax = x2;

                                }
                                if (dimension.Name == "y2")
                                {
                                    string y2_str = dimension.InnerText;
                                    y2String=y2_str;
                                    y2 = float.Parse(y2_str);
                                    if (y2 < yMin) yMin = y2;
                                    if (y2 >= yMax) yMax = y2;
                                }

                            }/*
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
                            */
                            Vector3 p1 = new Vector3(x1, 1, y1);
                            Vector3 p2 = new Vector3(x2, 1, y2);

                            create_3D(p1, p2,1);



                            if (sala_scris.Equals("-1"))
                            {
                                GameObject text2 = new GameObject("text-coord");
                                text2.gameObject.tag = "naspa";
                                TextMesh t2 = text2.AddComponent<TextMesh>();
                                t2.text = x1String+" "+y1String;
                                t2.fontSize = 12;
                                t2.fontStyle = FontStyle.Bold;
                                t2.color = Color.magenta;
                                t2.transform.localEulerAngles += new Vector3(90, 0, 0);
                                t2.transform.localPosition += new Vector3(x1, 2f, y1);

                                GameObject text3 = new GameObject("text-coord2");
                                text3.gameObject.tag = "naspa";
                                TextMesh t3 = text3.AddComponent<TextMesh>();
                                t3.text = x2String + " " + y2String;
                                t3.fontSize = 12;
                                t3.fontStyle = FontStyle.Bold;
                                t3.color = Color.magenta;
                                t3.transform.localEulerAngles += new Vector3(90, 0, 0);
                                t3.transform.localPosition += new Vector3(x2, 2f, y2);

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
                            /*
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
                            */

                            Vector3 p7 = new Vector3(x1, 1, y1);
                            Vector3 p8 = new Vector3(x2, 1, y2);

                            create_3D(p7, p8, 4);

                            GameObject text = new GameObject("text-door");
                            text.gameObject.tag = "naspa";
                            TextMesh t = text.AddComponent<TextMesh>();
                            t.text = sala_scris;
                            t.fontSize = 12;
                            t.fontStyle = FontStyle.Bold;
                            t.color = Color.red;
                            t.transform.localEulerAngles += new Vector3(90, 0, 0);
                            t.transform.localPosition += new Vector3(x1, 2f, y1);

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
                            /*
                            LineRenderer lineRenderer3;
                            GameObject obj3 = new GameObject("line-window");

                            obj3.gameObject.tag = "naspa";
                            lineRenderer3 = obj3.AddComponent<LineRenderer>();
                            lineRenderer3.SetWidth(0.9f, 0.9f);
                            lineRenderer3.SetColors(Color.blue, Color.blue);
                            lineRenderer3.SetPosition(0, new Vector3(x1, 0, y1));
                            lineRenderer3.SetPosition(1, new Vector3(x2, 0, y2));
                            Material blueDiffuseMat = new Material(Shader.Find("Sprites/Default"));
                            lineRenderer3.material = blueDiffuseMat;*/
                            Vector3 p5 = new Vector3(x1, 1, y1);
                            Vector3 p6 = new Vector3(x2, 1, y2);
                        
                            create_3D(p5, p6, 3);

                            break;
                        case "path":

                            XmlNodeList roomdimensions4 = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                            foreach (XmlNode dimension in roomdimensions4)
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
                            LineRenderer lineRenderer4;
                            GameObject obj4 = new GameObject("line-path");

                            obj4.gameObject.tag = "naspa";
                            lineRenderer4 = obj4.AddComponent<LineRenderer>();
                            lineRenderer4.SetWidth(0.6f, 0.6f);
                            lineRenderer4.SetColors(Color.green, Color.green);
                            lineRenderer4.SetPosition(0, new Vector3(x1, 0.6f, y1));
                            lineRenderer4.SetPosition(1, new Vector3(x2, 0.6f, y2));
                            Material blueDiffuseMat4 = new Material(Shader.Find("Sprites/Default"));
                            lineRenderer4.material = blueDiffuseMat4;
                            break;

                        case "stairs":

                            XmlNodeList roomdimensions5 = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                            foreach (XmlNode dimension in roomdimensions5)
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
                            /*
                            LineRenderer lineRenderer5;
                            GameObject obj5 = new GameObject("line-path");

                            obj5.gameObject.tag = "naspa";
                            lineRenderer5 = obj5.AddComponent<LineRenderer>();
                            lineRenderer5.SetWidth(0.3f, 0.3f);
                            lineRenderer5.SetColors(Color.cyan, Color.cyan);
                            lineRenderer5.SetPosition(0, new Vector3(x1, 0.6f, y1));
                            lineRenderer5.SetPosition(1, new Vector3(x2, 0.6f, y2));
                            Material blueDiffuseMat5 = new Material(Shader.Find("Sprites/Default"));
                            lineRenderer5.material = blueDiffuseMat5;
                            */

                            Vector3 p3 = new Vector3(x1, 1, y1);
                            Vector3 p4 = new Vector3(x2, 1, y2);

                            create_3D(p3, p4,2);

                            break;

                    }
                }




            }
        }

    }

    public static void renderLevelForDisplayCamera(XmlNode floorinfo2)
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
                    sala_scris = nume;
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
                                    if (x1 < xMin) xMin = x1;
                                    if (x1 >= xMax) xMax = x1;
                                }
                                if (dimension.Name == "y1")
                                {
                                    string y1_str = dimension.InnerText;
                                    y1 = float.Parse(y1_str);
                                    if (y1 < yMin) yMin = y1;
                                    if (y1 >= yMax) yMax = y1;
                                }
                                if (dimension.Name == "x2")
                                {
                                    string x2_str = dimension.InnerText;
                                    x2 = float.Parse(x2_str);
                                    if (x2 < xMin) xMin = x2;
                                    if (x2 >= xMax) xMax = x2;

                                }
                                if (dimension.Name == "y2")
                                {
                                    string y2_str = dimension.InnerText;
                                    y2 = float.Parse(y2_str);
                                    if (y2 < yMin) yMin = y2;
                                    if (y2 >= yMax) yMax = y2;
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
                        case "path":

                            XmlNodeList roomdimensions4 = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                            foreach (XmlNode dimension in roomdimensions4)
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

                        case "stairs":

                            XmlNodeList roomdimensions5 = roomstuff.ChildNodes;//xmlDoc.GetElementsByTagName("type");// 
                            foreach (XmlNode dimension in roomdimensions5)
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

    public static void renderLevelForDropDown(XmlNode floorinfo2, int Etaj)
    {
       if (camere[Etaj] == null)
            camere[Etaj] = new List<string>() ;
        float x1 = 0.0f, x2 = 0.0f, y1 = 0.0f, y2 = 0.0f;
        int flag = 0;
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
                    if (nume.Equals(getCamera())) flag = 1;
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
                               // usi[Etaj]= x1_str + y1_str;
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

    public static void renderLevelForDropDownDoor(XmlNode floorinfo2, int Etaj)
    {
        if (usi[Etaj] == null) //sa incep de la 0 si sa pun aici pe undeca la citirea usilor un x1 concatenat cu y1 pe care il returnez cu getUsa
            usi[Etaj] = new List<string>();

        float x1 = 0.0f, x2 = 0.0f, y1 = 0.0f, y2 = 0.0f;
        int flag = 0;
        XmlNodeList roomcontent2 = floorinfo2.ChildNodes;

        foreach (XmlNode floorinfo in roomcontent2)
        {
            XmlNodeList roomcontent = floorinfo.ChildNodes;

            foreach (XmlNode roomstuff in roomcontent)
            {
                if (roomstuff.Name == "name")
                {
                    string nume = roomstuff.InnerText;
                    if (nume.Equals(getCamera())) flag = 1;
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

                                    if (flag == 1)
                                        usi[Etaj].Add(x1_str);
                                }
                                if (dimension.Name == "y1")
                                {
                                    string y1_str = dimension.InnerText;
                                    y1 = float.Parse(y1_str);
                                    if (flag == 1)
                                        usi[Etaj].Add(y1_str);
                                    flag = 0;
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
                                // usi[Etaj]= x1_str + y1_str;
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