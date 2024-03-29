package org.springLibrary.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "Поле ФИО не может быть пустое")
    @Size(min = 2, max = 100, message = "Длина ФИО должна быть от 2-х до 100 символов")
    private String fullName;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900г.")
    private int birthYear;
    public Person(){}

    public Person(int id, String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
