using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class showMenu : MonoBehaviour
{
    public List<string> etajj = new List<string>() { "Alege Etaj", "Etaj 1", "Etaj 2" };
    public List<string> camere_etaj1 = new List<string> { "etaj 1", "C101", "C102", "C103", "C104", "C105", "C106", "C107", "C108", "C109" };
    public List<string> camere_etaj2 = new List<string> { "etaj 2", "C201", "C202", "C203", "C204", "C205", "C206", "C207", "C208", "C209" };

    public Dropdown dd_2d_etaj;
    public Dropdown dd_etaj1;
    public Dropdown dd_etaj2;
    public Dropdown dd_camera1;
    public Dropdown dd_camera2;
    public Button btn_generate;
    public int etaj1, etaj2, camera1, camera2;

    // Cand alege vizualizarea altui etaj
    public void Dropdown_IndexChanged_Vizualizare_Etaj(int index)
    {
        Debug.Log("Etaj selectat: " + index);
    }


    // Cand alege etajul de start pentru drum
    public void Dropdown_IndexChanged_Drum_Selectare_Etaj1(int index)
    {
        if (index == 1) // Daca a ales prima optiune din dropdown
        {
            dd_camera1.ClearOptions(); // Stergem optiunile de la dropdown'ul camera1
            dd_camera1.AddOptions(camere_etaj1); // Adaugam optiunile cu camerele etajului 1
        }
        else if (index == 2) // Daca a ales a doua optiune din dropdown
        {
            dd_camera1.ClearOptions(); // Stergem optiunile vechi
            dd_camera1.AddOptions(camere_etaj2); // Adaugam optiunile cu camerele etajului 2
        }
    }

    // Cand alege etajul destinatie pentru drum
    public void Dropdown_IndexChanged_Drum_Selectare_Etaj2(int index)
    {
        if (index == 1) // Daca a ales etajul 1
        {
            dd_camera2.ClearOptions(); // Stergem optiunile vechi
            dd_camera2.AddOptions(camere_etaj1); // Adaugam camerele etajului 1
        }
        else if (index == 2)
        {
            dd_camera2.ClearOptions(); // Stergem optiunile vechi
            dd_camera2.AddOptions(camere_etaj2); // Adaugam camerele etajului 2
        }
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


    public void Button_Generate(int etaj1)
    {
        Debug.Log(  "Etaj1: " + dd_etaj1.GetComponent<Dropdown>().value + " - Camera1: " + dd_camera1.GetComponent<Dropdown>().value +
                    "\nEtaj2: " + dd_etaj2.GetComponent<Dropdown>().value + " - Camera2: " + dd_camera2.GetComponent<Dropdown>().value);
    }

   
    void Start()
    {
        Populare();
    }

    void Populare()
    {
        dd_2d_etaj.ClearOptions();
        dd_2d_etaj.AddOptions(etajj);
        dd_etaj1.ClearOptions();
        dd_etaj1.AddOptions(etajj);
        dd_etaj2.ClearOptions();
        dd_etaj2.AddOptions(etajj);
    }
}
