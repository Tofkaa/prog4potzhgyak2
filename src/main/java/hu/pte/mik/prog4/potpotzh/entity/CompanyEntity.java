package hu.pte.mik.prog4.potpotzh.entity;

import java.util.Objects;

public class CompanyEntity {

    private Long id;
    private String companyName;
    private Long estYear;
    private String country;
    private String knownProducts;

    public CompanyEntity(Long id, String companyName, Long estYear, String country, String knownProducts) {
        this.id = id;
        this.companyName = companyName;
        this.estYear = estYear;
        this.country = country;
        this.knownProducts = knownProducts;
    }

    public CompanyEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getEstYear() {
        return estYear;
    }

    public void setEstYear(Long estYear) {
        this.estYear = estYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getKnownProducts() {
        return knownProducts;
    }

    public void setKnownProducts(String knownProducts) {
        this.knownProducts = knownProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(companyName, that.companyName) && Objects.equals(estYear, that.estYear) && Objects.equals(country, that.country) && Objects.equals(knownProducts, that.knownProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, estYear, country, knownProducts);
    }
}
