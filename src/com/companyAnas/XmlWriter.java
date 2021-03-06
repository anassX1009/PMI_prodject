package com.companyAnas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class XmlWriter {


    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String filepath = "src/com/companyAnas/countries.xml";
        ArrayList<Country> countries = XmlReader.readCountriesFromXml(filepath);

        //You could find more info in the app documentation in target --> README.md
        System.out.println("--> This is an EDUCATIONAL application. It's basically a database of countries.");
        System.out.println("--> This app is to help people learn more about world's countries. ");
        System.out.println("--> You could add new countries to the list, modify countries information, and delete countries from the list: ");
        System.out.println();

        int choice = -1;
        while (choice != 0) {
            System.out.println("1 . The list of available countries\r\n2 . Add a new country\r\n3 . Modify a country's information\r\n" +
                    "4 . Delete a country from the list\r\n0 . Exit the app");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || 4 < choice) {
                    System.out.println("Not valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid option.");
                scanner.nextLine();
            }
            switch (choice) {
                case 1 -> System.out.println(countries);
                case 2 -> addCountry(countries);
                case 3 -> modifyCountry(countries);
                case 4 -> deleteCountry(countries);
            }
        }

        saveCountriessToXml(countries, filepath);
    }

    private static void addCountry(ArrayList<Country> countries) {
        System.out.print("Enter name of the country you want to add: ");
        String country = scanner.nextLine();
        int population = readPopulation();
        System.out.print("Enter the capital of this country: ");
        String capital = scanner.nextLine();
        Continent continent = readContinent();
        countries.add(new Country(country, population, capital, continent));
    }

    private static void modifyCountry(ArrayList<Country> countries) {
        Country country = findCountryIn(countries);
        int population = readPopulation();
        System.out.print("Enter the capital of the country: ");
        String capital = scanner.nextLine();
        Continent continent = readContinent();
        countries.set(countries.indexOf(country),
                  new Country(country.getName(), population, capital, continent));
    }

    private static void deleteCountry(ArrayList<Country> countries) {
        countries.remove(findCountryIn(countries));
    }

    private static Country findCountryIn(ArrayList<Country> countries) {
        Country country = new Country();
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Enter the name of the country: ");
            name = scanner.nextLine();
            for (Country countryElement : countries) {
                if (countryElement.getName().equals(name)) {
                    return countryElement;
                }
            }
            name = "";
        }
        return country;
    }

    private static int readPopulation() {
        int population = 0;
        while (population == 0) {
            try {
                System.out.print("Enter the estimated population of the country: ");
                population = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("This is not a valid number. Please enter an integer.");
                scanner.nextLine();
            }
        }
        return population;
    }

    private static Continent readContinent() {
        Continent continent = Continent.AFRICA;
        String rawInput = "";
        while (rawInput.isEmpty()) {
            try {
                System.out.println("Enter the continent of the country: ");
                rawInput = scanner.nextLine();
                continent = Continent.valueOf(rawInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Not a valid continent");
                rawInput = "";
            }
        }
        return continent;
    }

    public static void saveCountriessToXml(ArrayList<Country> countries, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element rootElement = document.createElement("countries");
            document.appendChild(rootElement);

            for (Country country : countries) {
                Element countryElement = document.createElement("country");
                rootElement.appendChild(countryElement);
                createChildElement(document, countryElement, "name", country.getName());
                createChildElement(document, countryElement, "population", String.valueOf(country.getPopulation()));
                createChildElement(document, countryElement, "capital", country.getCapital());
                createChildElement(document, countryElement, "continent", country.getContinent().toString());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

}
