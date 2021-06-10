package me.geonsu.springdatademo;

import javax.persistence.*;

@Entity
public class Study {
    @Id @GeneratedValue
    private Long id;

    private String name;

    /*
    현재 이 reference가 collection이 아니라, 한 개이기 때문에 **One으로 끝난다.
     */
    /*
    @ManyToOne
    private Account owner;

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }
     */

    @ManyToOne
    private Account owner;

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
