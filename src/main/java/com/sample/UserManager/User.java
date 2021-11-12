package com.sample.UserManager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
public  class User {
    private @Id
    @GeneratedValue
    Long id;

    @NotNull
    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$\n")
    /**
     * ^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$
     *  └─────┬────┘└───┬──┘└─────┬─────┘└─────┬─────┘ └───┬───┘
     *        │         │         │            │           no _ or . at the end
     *        │         │         │            │
     *        │         │         │            allowed characters
     *        │         │         │
     *        │         │         no __ or _. or ._ or .. inside
     *        │         │
     *        │         no _ or . at the beginning
     *        │
     *        username is 8-20 characters long
     */
    private  String userName;
    private  String firstName;

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private  String lastName;
    private  String password;

    public User() {
    }

    public User(String userName, String firstName, String lastName, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

}
