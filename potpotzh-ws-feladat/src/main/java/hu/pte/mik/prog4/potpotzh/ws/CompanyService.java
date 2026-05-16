package hu.pte.mik.prog4.potpotzh.ws;

import jakarta.jws.WebService;

@WebService
public interface CompanyService {
    CompanyResponse getCompanyData(CompanyRequest request);
}