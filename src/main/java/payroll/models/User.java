package payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@JsonIgnoreProperties(ignoreUnknown=true)
@Component
@Entity
public class User {
    
    private @Id @GeneratedValue Integer id;
    private String name;
    private String role;

    private @Column(length = 5) Float weight;

    public User(){}

    /**
     * This method consumes a rest api then populates this model with the data 
     */
    @Autowired @Transient RestTemplate restTemplate;
    public @Transient User getForId(int id) throws RestClientException{

        // String url  =   String.format("http://payroll.tevva/employees/%d", id);
        String url  =   String.format("http://payroll2/employees/%d", id);

        User user   =   restTemplate.getForObject(url, this.getClass());
        return user;
    }

    public String getName(){

        return name;
    }

    public void setName(String name){

        this.name = name;
    }

    public String getRole() {
        
        return role;
    }


    public void setRole(String role){

        this.role = role;
    }


    public int getId(){

        return this.id;
    }

    public void setId(int id){

        this.id = id;
    }


    @Override
    public String toString(){

        return String.format("User{id=%d, name='%s', role='%s'}", this.id, this.name, this.role );
    }

}
