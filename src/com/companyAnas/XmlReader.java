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


    public static ArrayList<Country> readUsersFromXml(String filepath) {
        ArrayList<Country> users = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(filepath));

            Element rootElement = document.getDocumentElement();


            NodeList childsOfRootElement = rootElement.getChildNodes();

            for (int i = 0; i < childsOfRootElement.getLength(); i++) {
                Node childNode = childsOfRootElement.item(i);
                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childsOfUserTag = childNode.getChildNodes();

                    String name = "";
                    int population = 0;
                    String capital = "";
                    Continent continent = Continent.AFRICA;
                    for (int j = 0; j < childsOfUserTag.getLength(); j++) {
                        Node childNodeOfUserTag = childsOfUserTag.item(j);
                        if (childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodeOfUserTag.getNodeName()) {
                                case "country" -> name = childNodeOfUserTag.getTextContent();
                                case "population" -> population = Integer.parseInt(childNodeOfUserTag.getTextContent());
                                case "capital" -> capital = childNodeOfUserTag.getTextContent();
                                case "continent" -> continent = Continent.valueOf(childNodeOfUserTag.getTextContent());
                            }
                        }
                    }
                    users.add(new Country(name, population, capital, continent));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


}
