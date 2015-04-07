package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person>{
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired;
  private int numberOfInstances;
  
  public Person() {
    this("", 0, 0.0d);
    numberOfInstances++;
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    numberOfInstances++;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int num) throws IllegalArgumentException {
    if(num < 0 ) {
      throw new IllegalArgumentException("Age cannot be lower than 0.");
    } else {
      int oldAge = age;
      age = num;
    }
  }
  
  public String getName() {
    return name;
  }

  public void setName(String str) throws IllegalArgumentException {
    if (str == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    } else {
      String oldName = name;
      name = str; 
    }
  }
  
  public double getSalary() {
    return salary;
  }

  public void setSalary(double doub) throws IllegalArgumentException {
    if(doub < 0 ) {
        throw new IllegalArgumentException("Salary cannot be lower than 0.");
      } else {
       double oldSalary = salary;
       salary = doub;
      }
  }
  
  public String getSSN() {
    return ssn;
  }
  
  public void setSSN(String value) {
    String oldSSN = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", oldSSN, value);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public String toString() {
    return "[Person name:" + this.getName() + " age:" + this.getAge() + " salary:" + this.getSalary() + "]";
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof Person) {
      Person p = (Person)other;
      return p.name == this.name && p.age == this.age;
    }
    return false;
  }
  
  public int count() {
    return numberOfInstances;
  }
  
  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> family = new ArrayList<Person>();
    family.add(new Person("Ted", 41, 250000));
    family.add(new Person("Charlotte", 43, 150000));
    family.add(new Person("Michael", 22, 10000));
    family.add(new Person("Matthew", 15, 0));
    return family;
  }
  
  public int compareTo(Person other) {
    return -((int) (this.salary - other.salary));
  }
  
  public static class AgeComparator implements Comparator<Person> {

    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();  
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}