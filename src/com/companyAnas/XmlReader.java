package com.companyAnas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;



public class XmlReader {


    public static ArrayList<Country> readCountriesFromXml(String filepath) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            Element rootElement = document.getDocumentElement();


            NodeList childsOfRootElement = rootElement.getChildNodes();

            for (int i = 0; i < childsOfRootElement.getLength(); i++) {
                Node childNode = childsOfRootElement.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childsOfCountryTag = childNode.getChildNodes();

                    String name = "";
                    int population = 0;
                    String capital = "";
                    Continent continent = Continent.AFRICA;
                    for (int j = 0; j < childsOfCountryTag.getLength(); j++) {
                        Node childNodeOfCountryTag = childsOfCountryTag.item(j);
                        if (childNodeOfCountryTag.getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodeOfCountryTag.getNodeName()) {
                                case "name" -> name = childNodeOfCountryTag.getTextContent();
                                case "population" -> population = Integer.parseInt(childNodeOfCountryTag.getTextContent());
                                case "capital" -> capital = childNodeOfCountryTag.getTextContent();
                                case "continent" -> continent = Continent.valueOf(childNodeOfCountryTag.getTextContent());
                            }
                        }
                    }
                    countries.add(new Country(name, population, capital, continent));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }


}
