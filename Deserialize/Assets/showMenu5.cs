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

    }
    public void Dropdown_IndexChanged_Drum_Selectare_Etaj2(int index)
    {
        dd_camera2.ClearOptions(); // Stergem optiunile de la dropdown'ul camera1
        dd_camera2.AddOptions(Deserialize.getCamere(dd_etaj2.GetComponent<Dropdown>().value)); // Adaugam optiunile cu camerele etajului 1
    }



    // Cand alege camera de start pentru drum
    public void Dropdown_IndexChanged_Drum_Selectare_Camera1(int index)
    {
        //Debug.Log("Camera start: ");// + dd_camera1[index]);
    }


    // Cand alege camera destinatie pentru drum
    public void Dropdown_Index_Changed_Drum_Selectare_Camera2(int index)
    {
        //Debug.Log("Camera2: " + camera2);
    }


    public void Button_Generate()
    {
        Debug.Log("Etaj1: " + dd_etaj1.GetComponent<Dropdown>().value + " - Camera1: " + dd_camera1.GetComponent<Dropdown>().value +
                    "\nEtaj2: " + dd_etaj2.GetComponent<Dropdown>().value + " - Camera2: " + dd_camera2.GetComponent<Dropdown>().value);

        etaj_camera1 = dd_etaj1.GetComponent<Dropdown>().value;
        etaj_camera2 = dd_etaj2.GetComponent<Dropdown>().value;
        camera1 = dd_camera1.GetComponent<Dropdown>().value;
        camera2 = dd_camera2.GetComponent<Dropdown>().value; 
        Deserialize d = new Deserialize();
        //x1 y1 de la usa, x1 y1 de la cealalta usa, etaj de la prima usa, etaj de la a doua usa

        string output = "";
        

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
        }
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
        }
        output += dd_etaj2.GetComponent<Dropdown>().value;
        Deserialize.setUsi(dd_etaj2.GetComponent<Dropdown>().value);
        Debug.Log("output = " + output);
        //de vazut cum se returneaza stringul din dropdown
        d.Connect("localhost", output);
        
       // Dropdown_IndexChanged_Vizualizare_Etaj(0);
        
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

    void Start()
    {

        StartCoroutine(Deserialize.GetLevelsForDropDown(0, Populare));
        dd_2d_etaj.ClearOptions();
        dd_2d_etaj.AddOptions(Deserialize.getEtajePentruDd());
    }

    void Populare()
    {

        dd_etaj1.ClearOptions();
        dd_etaj1.AddOptions(Deserialize.getEtajePentruDd());

        dd_etaj2.ClearOptions();
        dd_etaj2.AddOptions(Deserialize.getEtajePentruDd());
    }
}
