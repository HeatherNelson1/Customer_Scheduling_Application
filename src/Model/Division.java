package Model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * FirstLevelDivisions class holds the first level divsion
 */
public class Division {
    private int divisionId;
    private String division;
    private LocalDate createdDate;
    private String createdBy;
    private Timestamp lastUpdateDate;
    private String lastUpdatedBy;
    private int countryId;

    public Division(int divisionId, String division, LocalDate createdDate, String createdBy, Timestamp lastUpdateDate, String lastUpdatedBy, int countryId)
    {
        this.divisionId = divisionId;
        this.division = division;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastUpdateDate = lastUpdateDate;
        this.countryId = countryId;
    }

    @Override
    public String toString(){
        return division;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getDivCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
