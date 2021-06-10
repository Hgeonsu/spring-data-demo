package me.geonsu.springdatademo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    /*
     * reference가 되는 쪽이 collection 타입이니까 **many로 끝난다고 생각.
     * //join 테이블이 만들어진다
     */
    @OneToMany(mappedBy = "owner") //mappedBy 는 양방향 맵핑할 때 사용
    private Set<Study> studies = new HashSet<>();



    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    private String yes;

    @Transient //객체에서는 사용하고, 컬럼에 맵핑은 하고 싶지 않을 때
    private String no;

    //아래와 같이 그냥 쓰거나
//    @Embedded
//    private Address address;

    //Address라는 valuetype의 street 필드를, home_street으로 바꿔 쓰고 싶을 때
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = yes;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addStudy(Study study) {
        this.getStudies().add(study); //관계를 account에 설정할 때
        study.setOwner(this); //관계의 주인이 study였을 때
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study); //관계를 account에 설정할 때
        study.setOwner(null); //관계의 주인이 study였을 때
    }
}
