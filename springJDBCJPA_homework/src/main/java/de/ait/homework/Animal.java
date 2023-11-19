package de.ait.homework;

public class Animal {
    private String type;
    private String breed;
    private String name;
    private int age;

    public Animal(String type, String breed, String name, int age) {
        this.type = type;
        this.breed = breed;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Animal{");
        sb.append("type='").append(type).append('\'');
        sb.append(", breed='").append(breed).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
