package hig.se.domain;

import java.time.Year;
import java.util.Objects;

public class Person {
    int id;
    String name = "";
    int birthYear;
    int year = Year.now().getValue();
    //Year birthYear;

    public Person(int id, String name, int birthYear)  {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public Person(String name, int birthYear) {
        setName(name);
        setBirthYear(birthYear);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isBlank() && !name.isEmpty()){
            this.name = name;
        }else {
            throw new IllegalArgumentException("Name cant be null or empty");
        }

    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        if (birthYear > year){
            throw new IllegalArgumentException("Du kan inte född än, välkommen tillbaka när du är född");
        }
        this.birthYear = birthYear;
    }
    @Override
    public boolean equals(Object p){
        return p instanceof Person && hashCode() == p.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthYear, year);
    }
}