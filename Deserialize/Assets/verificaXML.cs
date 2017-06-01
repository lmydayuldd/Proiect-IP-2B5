using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Xml;
using System.Xml.Schema;

public class verificaXML : MonoBehaviour{

	private bool isValid = false;

	void Start () {
        // Create the XmlReader object.
        XmlReader reader = new XmlTextReader("C:\\Users\\satellite\\Desktop\\Proiect-IP-2B5\\Deserialize\\Assets\\format_date.xml");

        // Parse the file.
        try
        {
            while (reader.Read()) ;
            isValid = true;
        } catch (XmlException e)
        {
            isValid = false;
        }

        Debug.Log(isValid);

    }

    private static void ValidationCallBack(object sender, ValidationEventArgs args)
    {
        if (args.Severity == XmlSeverityType.Warning)
            Debug.Log("\tWarning: Matching schema not found.  No validation occurred." + args.Message);
        else
            Debug.Log("\tValidation error: " + args.Message);
    }


    public bool isXmlValid()
    {
        return isValid;
    }

    void Update () {
		
	}
}