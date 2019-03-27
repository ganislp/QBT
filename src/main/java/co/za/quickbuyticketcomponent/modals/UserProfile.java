package co.za.quickbuyticketcomponent.modals;

import co.za.quickbuyticketcomponent.modals.AccountType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gVadlamuri on 12/28/2017.
 */
@Entity
@Table(name="UserProfile"
        , uniqueConstraints = {@UniqueConstraint(columnNames="Email")}
)
public class UserProfile  implements java.io.Serializable {


    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private AccountType accountType;
    private Date createdAt;
    private Date modifiedAt;
    private String isActive;
    private int isDeleted;


    public UserProfile() {
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "UserID", unique = true)
    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Column(name = "FirstName",  length = 50)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Column(name = "LastName",  length = 50)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "Email", unique = true,  length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt",  length = 23)
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedAt", length = 23)
    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


    @Column(name = "isActive",  length = 1)
    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }


    @Column(name = "IsDeleted",  length = 1)
    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }


    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AccountTypeId")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", accountType=" + accountType +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", isActive='" + isActive + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}