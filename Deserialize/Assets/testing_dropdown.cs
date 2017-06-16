using UnityEngine;
using UnityEngine.TestTools;
using NUnit.Framework;
using System.Collections;

public class testing_dropdown //: showMenu
{
	[Test]
	public void testing_dropdown_etaj_vizualizare_count_elements() {
        // Verificam daca numarul elementelor din dropdown'ul cu etajele este 0
        Assert.AreEqual(0, showMenu.countEtajElements());
	}

    [Test]
    public void testing_dropdown_etaj1_count_elements()
    {
        // Verificam daca numarul elementelor din dropdown'ul cu etajul de start pentru drum(etaj1) este 0
        Assert.AreEqual(0, showMenu.countEtaj1Elements());
    }

    [Test]
    public void testing_dropdown_etaj2_count_elements()
    {
        // Verificam daca numarul elementelor din dropdown'ul cu etajul destinatie pentru drum(etaj2) este 0
        Assert.AreEqual(0, showMenu.countEtaj2Elements());
    }


    [Test]
    public void testing_dropdown_camera1_count_elements()
    {
        // Verificam daca numarul elementelor din dropdown'ul cu camerele de start pentru drum(camera1) este 0
        Assert.AreEqual(0, showMenu.countCamera1Elements());
    }

    [Test]
    public void testing_dropdown_camera2_count_elements()
    {
        // Verificam daca numarul elementelor din dropdown'ul cu camerele destinatie pentru drum(camera2) este 0
        Assert.AreEqual(0, showMenu.countCamera2Elements());
    }
    
    
    // A UnityTest behaves like a coroutine in PlayMode
    // and allows you to yield null to skip a frame in EditMode
    [UnityTest]
	public IEnumerator testing_dropdownWithEnumeratorPasses() {
		// Use the Assert class to test conditions.
		// yield to skip a frame
		yield return null;
	}
}
