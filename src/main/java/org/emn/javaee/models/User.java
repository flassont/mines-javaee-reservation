package org.emn.javaee.models;

import javax.persistence.*;

/**
 * Application user.
 * Can manage Resource when admin
 */
@Entity
@Table (name = "USER")
@NamedQueries({
	@NamedQuery( name = "User.findAll",
			query = "SELECT U FROM User AS U" )
})
public class User extends AbstractModel{

    @Id
    @GeneratedValue
    @Column (name = "ID")
    private int id;

    @Column (name = "LOGIN", unique = true, nullable = false)
    private String login;

    /**
     * Plain password
     */
    @Column (name = "PASSWORD", nullable = false)
    private String password;

    @Column (name = "LASTNAME", nullable = false)
    private String lastName;

    @Column (name = "FIRSTNAME", nullable = false)
    private String firstName;

    /**
     * Email address
     */
    @Column (name = "MAIL", nullable = false)
    private String mail;

    /**
     * Phone number
     */
    @Column (name = "PHONE", length = 12)
    private String phone;

    /**
     * Is this User administrator
     */
    @Column (name = "ISADMIN")
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

	@Override
	public String getFilterBy() {
		return this.firstName;
	}
}