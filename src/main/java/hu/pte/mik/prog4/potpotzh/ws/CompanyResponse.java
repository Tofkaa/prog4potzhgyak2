package hu.pte.mik.prog4.potpotzh.ws;

public class CompanyResponse {
    private String CompanyId;
    private double GECI;

    public double getGECI() {
        return GECI;
    }

    public void setGECI(double GECI) {
        this.GECI = GECI;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public CompanyResponse(String companyId,  double geci) {
        CompanyId = companyId;
        GECI = geci;
    }
}