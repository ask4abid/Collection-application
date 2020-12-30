package com.finca.ccw.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "employee_id", nullable = false)
    private String employeeID;

    @NotNull
    @Column(name = "unit_id", nullable = false)
    private String unitID;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy = "employee")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CollectionTracking> collectionTrackings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public Employee employeeID(String employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getUnitID() {
        return unitID;
    }

    public Employee unitID(String unitID) {
        this.unitID = unitID;
        return this;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Employee emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLogin() {
        return login;
    }

    public Employee login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<CollectionTracking> getCollectionTrackings() {
        return collectionTrackings;
    }

    public Employee collectionTrackings(Set<CollectionTracking> collectionTrackings) {
        this.collectionTrackings = collectionTrackings;
        return this;
    }

    public Employee addCollectionTracking(CollectionTracking collectionTracking) {
        this.collectionTrackings.add(collectionTracking);
        collectionTracking.setEmployee(this);
        return this;
    }

    public Employee removeCollectionTracking(CollectionTracking collectionTracking) {
        this.collectionTrackings.remove(collectionTracking);
        collectionTracking.setEmployee(null);
        return this;
    }

    public void setCollectionTrackings(Set<CollectionTracking> collectionTrackings) {
        this.collectionTrackings = collectionTrackings;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", employeeID='" + getEmployeeID() + "'" +
            ", unitID='" + getUnitID() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", login='" + getLogin() + "'" +
            "}";
    }
}
