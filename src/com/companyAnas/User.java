package com.companyAnas;

public class User {


    private String country;
    private int population;
    private String capital;
    private final Continent continent;

    public User() {
        this("Morocco", 37000000, "Rabat", Continent.AFRICA);
    }

    public User(String country, int population, String capital) {
        this.country = country;
        this.population = population;
        this.capital = capital;
        continent = Continent.AFRICA;
    }

    public User(String country, int population, String capital, Continent continent) {
        this.country = country;
        this.population = population;
        this.capital = capital;
        this.continent = continent;
    }

    public Continent  getContinent () {
        return continent;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return "\n"+country + " --> Population: " + population + "| Capital: " + capital + "| Continent: "
                + continent.toString() ;
    }
}
