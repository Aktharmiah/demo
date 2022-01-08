package payroll.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Project {

  private @Id @GeneratedValue @Column(name = "id") Integer id;
  private String name;
  private String manager;
  private Date commenced;

  //mapped by relates to the field that mapps to this entity, not the entitly itself
  @OneToOne(mappedBy = "project")
  private Employee employee;

  Project() {}

  Project(String name, String role) {

    this.name = name;
    this.manager = role;
  }/*  */

  public Date getCommened(){
    return this.commenced;
  }

  public void setCommenced(Date commenced){

    this.commenced = commenced;
  }

  public Integer getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getRole() {
    return this.manager;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.manager = role;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Project))
      return false;
      Project project = (Project) o;
    return Objects.equals(this.id, project.id) && Objects.equals(this.name, project.name)
        && Objects.equals(this.manager, project.manager);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.manager);
  }

  @Override
  public String toString() {
    return "Project{" + "id=" + this.id + ", name='" + this.name + '\'' + ", manager='" + this.manager + '\'' + '}';
  }
}