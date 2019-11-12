package com.devsmile.service.model;

//@Entity(name="User")
//@Getter
//@Setter
//@Table(name="User")
//@ToString
public class UserEntity {

//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Integer id;
//    @Column(name = "age")
    private Integer age;

    public UserEntity() {
        super();
    }

    public UserEntity(Integer id, Integer age) {
        super();
        this.id = id;
        this.age = age;
    }
    
}
