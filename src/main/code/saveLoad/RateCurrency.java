package saveLoad;

import mainClasses.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class RateCurrency {

    //debug
    //// http://www.nbrb.by/services/xmlexrates.aspx?ondate=01/31/2011
    static HashMap<String, Double> getRates(Currency base) throws ParserConfigurationException, IOException, SAXException {
        HashMap<String, NodeList> nodeMap = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Deprecated String url = "http://www.nbrb.by/Services/XmlExRates.aspx?ondate=" + dateFormat.format(new Date());
        String url = "http://www.nbrb.by/services/xmlexratesref.aspx";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = factory.newDocumentBuilder().parse(new URL(url).openStream());
        NodeList nodeList1 = document.getElementsByTagName("Currency");
        for (int i = 0; i < nodeList1.getLength(); i++) {
            Node node = nodeList1.item(i);
            NodeList n1Child = node.getChildNodes();
            for (int j = 0; j < n1Child.getLength(); j++) {
                if (n1Child.item(j).getNodeName().equals("CharCode")) {
                    nodeMap.put(n1Child.item(j).getTextContent(), n1Child);
                }
            }
        }
        HashMap<String, Double> rates = new HashMap<>();
        for (Map.Entry<String, NodeList> entry : nodeMap.entrySet()) {
            NodeList temp = entry.getValue();
            double value = 0;
            int faceValue = 0;  //номинал валюты
            for (int i = 0; i < temp.getLength(); i++) {
                if (temp.item(i).getNodeName().equals("Rate")) {
                    value = Double.parseDouble(temp.item(i).getTextContent().replace(",", "."));
                } else if (temp.item(i).getNodeName().equals("Scale")) {
                    faceValue = Integer.parseInt(temp.item(i).getTextContent());
                }
                double amount = value / faceValue;
                rates.put(entry.getKey(), (((double) Math.round(amount * 10000)) / 10000));
            }
        }
        rates.put("BLR", 1.0);
        double div = rates.get(base.getCode());
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            entry.setValue(entry.getValue() / div);
        }
        return rates;
    }
}
