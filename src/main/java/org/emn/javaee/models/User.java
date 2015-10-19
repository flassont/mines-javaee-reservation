package org.emn.javaee.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Application user.
 * Can manage Resource when admin
 */
@Entity
public class User {

    @Id
    private int id;

    @Column(nullable = false)
    private String login;

    /**
     * Plain password
     */
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    /**
     * Email address
     */
    @Column(nullable = false)
    private String mail;

    /**
     * Phone number
     */
    @Column(length = 12)
    private String phone;

    /**
     * Is this User administrator
     */
    private boolean isAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}