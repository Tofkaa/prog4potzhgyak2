package hu.pte.mik.prog4.potpotzh.service;

import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import hu.pte.mik.prog4.potpotzh.repository.CompanyRepository;
import hu.pte.mik.prog4.potpotzh.ws.CompanyRequest;
import hu.pte.mik.prog4.potpotzh.ws.CompanyResponse;
import hu.pte.mik.prog4.potpotzh.ws.CompanyService_Service;
import jakarta.xml.ws.BindingProvider;

import java.util.List;

public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService() {
        this.companyRepository = new CompanyRepository();
    }

    public List<CompanyEntity> findAll(){
        return this.companyRepository.listAll();
    }

    public CompanyEntity findById(Long id){
        return this.companyRepository.findById(id);
    }

    public CompanyEntity save(CompanyEntity entity){
        return this.companyRepository.save(entity);
    }

    public CompanyResponse getCompanyData(CompanyRequest request){

        CompanyService_Service service = new CompanyService_Service();


        hu.pte.mik.prog4.potpotzh.ws.CompanyService port = service.getSoapCompanyServicePort();

        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://localhost:8081/potpotzh_ws_feladat_war_exploded/ws/company-data");

        request.setCompanyId(request.getCompanyId());

        CompanyResponse response = port.getCompanyData(request);
        return response;

    }

}
