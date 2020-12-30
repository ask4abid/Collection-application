package com.finca.ccw.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CollectionTracking.
 */
@Entity
@Table(name = "collection_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CollectionTracking implements Serializable {
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
    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @NotNull
    @Column(name = "business_proposal", nullable = false)
    private String businessProposal;

    @NotNull
    @Column(name = "sub_proposal", nullable = false)
    private String subProposal;

    @NotNull
    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @NotNull
    @Column(name = "relation_id", nullable = false)
    private String relationId;

    @NotNull
    @Column(name = "account_no", nullable = false)
    private String accountNo;

    @NotNull
    @Column(name = "account_title", nullable = false)
    private String accountTitle;

    @NotNull
    @Column(name = "no_of_visits", nullable = false)
    private Integer noOfVisits;

    @NotNull
    @Column(name = "os_amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal osAmount;

    @NotNull
    @Column(name = "os_profit", precision = 21, scale = 2, nullable = false)
    private BigDecimal osProfit;

    @NotNull
    @Column(name = "od_days", nullable = false)
    private String odDays;

    @NotNull
    @Column(name = "loan_officer", nullable = false)
    private String loanOfficer;

    @NotNull
    @Column(name = "visited_by", nullable = false)
    private String visitedBy;

    @NotNull
    @Column(name = "ppt_date", nullable = false)
    private LocalDate pptDate;

    @NotNull
    @Column(name = "remakrs", nullable = false)
    private String remakrs;

    @ManyToOne
    @JsonIgnoreProperties(value = "collectionTrackings", allowSetters = true)
    private Employee employee;

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

    public CollectionTracking employeeID(String employeeID) {
        this.employeeID = employeeID;
        return this;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getUnitID() {
        return unitID;
    }

    public CollectionTracking unitID(String unitID) {
        this.unitID = unitID;
        return this;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public CollectionTracking employeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBusinessProposal() {
        return businessProposal;
    }

    public CollectionTracking businessProposal(String businessProposal) {
        this.businessProposal = businessProposal;
        return this;
    }

    public void setBusinessProposal(String businessProposal) {
        this.businessProposal = businessProposal;
    }

    public String getSubProposal() {
        return subProposal;
    }

    public CollectionTracking subProposal(String subProposal) {
        this.subProposal = subProposal;
        return this;
    }

    public void setSubProposal(String subProposal) {
        this.subProposal = subProposal;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public CollectionTracking mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRelationId() {
        return relationId;
    }

    public CollectionTracking relationId(String relationId) {
        this.relationId = relationId;
        return this;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public CollectionTracking accountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public CollectionTracking accountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
        return this;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public Integer getNoOfVisits() {
        return noOfVisits;
    }

    public CollectionTracking noOfVisits(Integer noOfVisits) {
        this.noOfVisits = noOfVisits;
        return this;
    }

    public void setNoOfVisits(Integer noOfVisits) {
        this.noOfVisits = noOfVisits;
    }

    public BigDecimal getOsAmount() {
        return osAmount;
    }

    public CollectionTracking osAmount(BigDecimal osAmount) {
        this.osAmount = osAmount;
        return this;
    }

    public void setOsAmount(BigDecimal osAmount) {
        this.osAmount = osAmount;
    }

    public BigDecimal getOsProfit() {
        return osProfit;
    }

    public CollectionTracking osProfit(BigDecimal osProfit) {
        this.osProfit = osProfit;
        return this;
    }

    public void setOsProfit(BigDecimal osProfit) {
        this.osProfit = osProfit;
    }

    public String getOdDays() {
        return odDays;
    }

    public CollectionTracking odDays(String odDays) {
        this.odDays = odDays;
        return this;
    }

    public void setOdDays(String odDays) {
        this.odDays = odDays;
    }

    public String getLoanOfficer() {
        return loanOfficer;
    }

    public CollectionTracking loanOfficer(String loanOfficer) {
        this.loanOfficer = loanOfficer;
        return this;
    }

    public void setLoanOfficer(String loanOfficer) {
        this.loanOfficer = loanOfficer;
    }

    public String getVisitedBy() {
        return visitedBy;
    }

    public CollectionTracking visitedBy(String visitedBy) {
        this.visitedBy = visitedBy;
        return this;
    }

    public void setVisitedBy(String visitedBy) {
        this.visitedBy = visitedBy;
    }

    public LocalDate getPptDate() {
        return pptDate;
    }

    public CollectionTracking pptDate(LocalDate pptDate) {
        this.pptDate = pptDate;
        return this;
    }

    public void setPptDate(LocalDate pptDate) {
        this.pptDate = pptDate;
    }

    public String getRemakrs() {
        return remakrs;
    }

    public CollectionTracking remakrs(String remakrs) {
        this.remakrs = remakrs;
        return this;
    }

    public void setRemakrs(String remakrs) {
        this.remakrs = remakrs;
    }

    public Employee getEmployee() {
        return employee;
    }

    public CollectionTracking employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CollectionTracking)) {
            return false;
        }
        return id != null && id.equals(((CollectionTracking) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CollectionTracking{" +
            "id=" + getId() +
            ", employeeID='" + getEmployeeID() + "'" +
            ", unitID='" + getUnitID() + "'" +
            ", employeeName='" + getEmployeeName() + "'" +
            ", businessProposal='" + getBusinessProposal() + "'" +
            ", subProposal='" + getSubProposal() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", relationId='" + getRelationId() + "'" +
            ", accountNo='" + getAccountNo() + "'" +
            ", accountTitle='" + getAccountTitle() + "'" +
            ", noOfVisits=" + getNoOfVisits() +
            ", osAmount=" + getOsAmount() +
            ", osProfit=" + getOsProfit() +
            ", odDays='" + getOdDays() + "'" +
            ", loanOfficer='" + getLoanOfficer() + "'" +
            ", visitedBy='" + getVisitedBy() + "'" +
            ", pptDate='" + getPptDate() + "'" +
            ", remakrs='" + getRemakrs() + "'" +
            "}";
    }
}
