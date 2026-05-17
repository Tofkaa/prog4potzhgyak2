package hu.pte.mik.prog4.potpotzh.api;

public class CompanyCreationRequest {


    //private Long id;
    private String companyName;
    private Long estYear;
    private String country;
    private String knownProducts;

    //public Long getId() {
       // return id;
    //}

    //public void setId(Long id) {
      //  this.id = id;
    //}

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
}
