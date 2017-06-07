using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class showMenu5 : MonoBehaviour
{
    public Dropdown dd_2d_etaj;
    public Dropdown dd_etaj1;
    public Dropdown dd_etaj2;
    public Dropdown dd_camera1;
    public Dropdown dd_camera2;
    public Button btn_generate;
    public static int etaj_index_vizualizare, etaj_camera1 = 0 - 2, etaj_camera2 = 0 - 2, camera1 = 0 - 2, camera2 = 0 - 2;
    public static int buton_apasat = 0,buton_apasat2=0;
    Vector3 mainCam;
    public static float mouseSensitivity = 0.05F;
    public static Vector3 lastPosition;
    public static int test_etaj_count, test_etaj1_count, test_etaj2_count, test_camera1_count, test_camera2_count;


    public void doExitGame()
    {
        Application.Quit();
    }
    // Cand alege vizualizarea altui etaj
    public void Dropdown_IndexChanged_Vizualizare_Etaj(int index)
    {
        Debug.Log("Etaj selectat: " + index);
        etaj_index_vizualizare = index;

        GameObject[] gameObjects;
        gameObjects = GameObject.FindGameObjectsWithTag("naspa");

        for (var i = 0; i < gameObjects.Length; i++)
            Destroy(gameObjects[i]);


        StartCoroutine(Deserialize.GetLevel(etaj_index_vizualizare));
        StartCoroutine(Deserialize.GetLevelPath(etaj_index_vizualizare));


    }
    public static int getIndexEtaj()
    {
        return etaj_index_vizualizare; // nu e nevoie momentan
    }


    // Cand alege etajul de start pentru drum
    public void Dropdown_IndexChanged_Drum_Selectare_Etaj1(int index)
    {

        dd_camera1.ClearOptions(); // Stergem optiunile de la dropdown'ul camera1
        dd_camera1.AddOptions(Deserialize.getCamere(dd_etaj1.GetComponent<Dropdown>().value)); // Adaugam optiunile cu camerele etajului 1
        test_etaj1_count = dd_etaj1.GetComponent<Dropdown>().options.Count;
    }
    public void Dropdown_IndexChanged_Drum_Selectare_Etaj2(int index)
    {
        dd_camera2.ClearOptions(); // Stergem optiunile de la dropdown'ul camera1

        dd_camera2.AddOptions(Deserialize.getCamere(dd_etaj2.GetComponent<Dropdown>().value)); // Adaugam optiunile cu camerele etajului 1
        test_etaj2_count = dd_etaj2.GetComponent<Dropdown>().options.Count;
    }

    // Cand alege camera de start pentru drum
    public void Dropdown_IndexChanged_Drum_Selectare_Camera1(int index)
    {
        //Debug.Log("Camera start: ");// + dd_camera1[index]);
        test_camera1_count = dd_camera1.GetComponent<Dropdown>().options.Count;
    }


    // Cand alege camera destinatie pentru drum
    public void Dropdown_Index_Changed_Drum_Selectare_Camera2(int index)
    {
        //Debug.Log("Camera2: " + camera2);
        test_camera2_count = dd_camera2.GetComponent<Dropdown>().options.Count;
    }


    public void Button_Zoom_Out()
    {
        float zoom=transform.position.y;
        transform.Translate(0, 0, -1);
        
    }
    public void Button_Zoom_In()
    {

        float zoom = transform.position.y;
        transform.Translate(0, 0, 1);
    }
	//luam valorile din dropdown-uri, apelam functiile de get si le punem intr un string pe care il trimitem printr un socket.
    public void Button_Generate()
    {
        Debug.Log("Etaj1: " + dd_etaj1.GetComponent<Dropdown>().value + " - Camera1: " + dd_camera1.GetComponent<Dropdown>().value +
                    "\nEtaj2: " + dd_etaj2.GetComponent<Dropdown>().value + " - Camera2: " + dd_camera2.GetComponent<Dropdown>().value);

        Deserialize.stringXmlPath = "D:\\format_date_path.xml";

       etaj_camera1 = dd_etaj1.GetComponent<Dropdown>().value;
        etaj_camera2 = dd_etaj2.GetComponent<Dropdown>().value;
        camera1 = dd_camera1.GetComponent<Dropdown>().value;
        camera2 = dd_camera2.GetComponent<Dropdown>().value; 
        Deserialize d = new Deserialize();
        //x1 y1 de la usa, x1 y1 de la cealalta usa, etaj de la prima usa, etaj de la a doua usa

        string output = "";
        int douaUsi = 0;

        int valueIndex= dd_camera1.GetComponent<Dropdown>().value;
        List<Dropdown.OptionData> menuOptions = dd_camera1.GetComponent<Dropdown>().options;
        string numeCamera = menuOptions[valueIndex].text;

        List<string> value2 = new List<string>();
        value2.Clear();
        value2 =Deserialize.getUsa(dd_etaj1.GetComponent<Dropdown>().value, numeCamera);
        foreach (string value3 in value2)
        {
            Debug.Log("USA1 = " + value3);
            output += value3+" ";
            douaUsi++;
            if (douaUsi == 2) break;
        }
        douaUsi = 0;
        output += dd_etaj1.GetComponent<Dropdown>().value + " ";
        Deserialize.setUsi(dd_etaj1.GetComponent<Dropdown>().value);

        int valueIndex1 = dd_camera2.GetComponent<Dropdown>().value;
        List<Dropdown.OptionData> menuOptions1 = dd_camera2.GetComponent<Dropdown>().options;
        string numeCamera1 = menuOptions1[valueIndex1].text;

        List<string> value4 = new List<string>();
        value4.Clear();
        value4 = Deserialize.getUsa(dd_etaj2.GetComponent<Dropdown>().value, numeCamera1);
        foreach (string value5 in value4)
        {
            Debug.Log("USA2 = " + value5);
            output += value5 + " ";
            douaUsi++;
            if (douaUsi == 2) break;
        }
        douaUsi = 0;
        output += dd_etaj2.GetComponent<Dropdown>().value;
        Deserialize.setUsi(dd_etaj2.GetComponent<Dropdown>().value);
        Debug.Log("output = " + output + "xMin,xMax"+Deserialize.xMin + Deserialize.xMax + "yMin,yMax"+Deserialize.yMin+ Deserialize.yMax);
        //de vazut cum se returneaza stringul din dropdown
        d.Connect("localhost", output);


        dd_2d_etaj.value =0;
        Dropdown_IndexChanged_Vizualizare_Etaj(0);

    }

    public static int getEtaj_camera1()
    {
        return etaj_camera1;
    }
    public static int getEtaj_camera2()
    {
        return etaj_camera2;
    }
    public static int getCamera1()
    {
        return camera1;
    }
    public static int getCamera2()
    {
        return camera2;
    }
	//facem refresh la dropdown, golim intai etajele si camerele din valorile statice din Deserialize.cs si parsam din nou xml-ul nou.
    public void Button_Refresh_Dd()
    {
        int i=1;
        while(i<=Deserialize.lastEtaj)
        { 
            Deserialize.setCamere(i);
            i++;
        }
        Deserialize.setEtajePentruDd();
        StartCoroutine(Deserialize.GetLevelsForDropDown(0, Populare));
        dd_2d_etaj.ClearOptions();
        dd_2d_etaj.AddOptions(Deserialize.getEtajePentruDd());

        dd_2d_etaj.value = 0;
        Dropdown_IndexChanged_Vizualizare_Etaj(0);
    }
	//transforma stringxmlpath intr un fisier gol numit format_date_gol si o apelam la Start pentru a nu afisa vechiul path din ce ne a generat modulul 3.
	//in plus, seteaza vizualizarea etajului pe 0 ca sa aibe timp sa se stearga pathul vechi.
    public void Button_Clear_Path()
    {
        Deserialize.stringXmlPath = "D:\\format_date_gol.xml";
        dd_2d_etaj.value = 0;
        Dropdown_IndexChanged_Vizualizare_Etaj(0);


    }


    public static int countEtajElements()
    {
        return test_etaj_count;
    }

    public static int countEtaj1Elements()
    {
        return test_etaj1_count;
    }

    public static int countEtaj2Elements()
    {
        return test_etaj2_count;
    }

    public static int countCamera1Elements()
    {
        return test_camera1_count;
    }

    public static int countCamera2Elements()
    {
        return test_camera2_count;
    }

    void Start()
    {

        StartCoroutine(Deserialize.GetLevelsForDropDown(0, Populare));
        dd_2d_etaj.ClearOptions();
        dd_2d_etaj.AddOptions(Deserialize.getEtajePentruDd());

        StartCoroutine(Deserialize.GetLevelForCamera(1));
       // Debug.Log( "xMin,xMax" + Deserialize.xMin + Deserialize.xMax + "yMin,yMax" + Deserialize.yMin + Deserialize.yMax);
        float xMedie = (Deserialize.xMin + Deserialize.xMax) / 2;
        float yMedie = (Deserialize.yMin + Deserialize.yMax) / 2;
        mainCam = GameObject.Find("Main Camera").transform.position=new Vector3(xMedie, 30, yMedie);
    }


    void Populare()
    {

        dd_etaj1.ClearOptions();
        dd_etaj1.AddOptions(Deserialize.getEtajePentruDd());
        

        dd_etaj2.ClearOptions();
        dd_etaj2.AddOptions(Deserialize.getEtajePentruDd());
    }
}