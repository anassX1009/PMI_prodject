package com.companyAnas;

public class Country {


    private String name;
    private int population;
    private String capital;
    private final Continent continent;

    public Country() {
        this("Morocco", 37000000, "Rabat", Continent.AFRICA);
    }

    public Country(String country, int population, String capital) {
        this.name = country;
        this.population = population;
        this.capital = capital;
        continent = Continent.AFRICA;
    }

    public Country(String country, int population, String capital, Continent continent) {
        this.name = country;
        this.population = population;
        this.capital = capital;
        this.continent = continent;
    }

    public Continent  getContinent () {
        return continent;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String address) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "\n"+name + " --> Population: " + population + "| Capital: " + capital + "| Continent: "
                + continent.toString() ;
    }
}
