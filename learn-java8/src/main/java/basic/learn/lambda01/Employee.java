package basic.learn.lambda01;

import java.util.Enumeration;
import java.util.Objects;

/**
 * @author Hai
 * @date 2020/6/13 - 15:00
 */


public class Employee {
  private Integer id;
  private String name;
  private Integer age;
  private State state;

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public Employee(Integer id, String name, Integer age, State state) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.state = state;
  }

  @Override
  public String toString() {
    return "Employee{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", state=" + state +
            '}';
  }

  public Employee(){

  }

  public Employee(Integer id) {
    this.id = id;
  }

  public Employee(Integer id, Integer age) {
    this.id = id;
    this.age = age;
  }

  public Employee(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id) &&
            Objects.equals(name, employee.name) &&
            Objects.equals(age, employee.age) &&
            state == employee.state;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, state);
  }
}
