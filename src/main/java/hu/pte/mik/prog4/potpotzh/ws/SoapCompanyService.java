package hu.pte.mik.prog4.potpotzh.ws;

import jakarta.jws.WebService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@WebService(endpointInterface = "hu.pte.mik.prog4.potpotzh.ws.CompanyService", serviceName = "CompanyService")
public class SoapCompanyService implements CompanyService {

    private final Map<String, Double> map = new HashMap<>();


    @Override
    public CompanyResponse getCompanyData(CompanyRequest request) {
        return new CompanyResponse(request.getCompanyId(), this.map.computeIfAbsent(request.getCompanyId(),
                companyId -> (double) ThreadLocalRandom.current().nextLong(100000000, 10000000000L)));

    }
}