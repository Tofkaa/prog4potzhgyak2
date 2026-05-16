package hu.pte.mik.prog4.potpotzh.api;


import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import hu.pte.mik.prog4.potpotzh.service.CompanyService;
import hu.pte.mik.prog4.potpotzh.ws.CompanyResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(){
        this.companyService = new CompanyService();
    }

    @GET
    public Response listAll(){
        List< CompanyEntity> companies = this.companyService.findAll();
        return Response.status(Response.Status.OK).entity(companies).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        CompanyEntity company = this.companyService.findById(id);

        if(company != null){
            return Response.status(Response.Status.OK).entity(company).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response create(CompanyCreationRequest request){

        CompanyEntity company = new CompanyEntity(null, request.getCompanyName(), request.getEstYear(), request.getCountry(), request.getKnownProducts());
        CompanyEntity newCompany = this.companyService.save(company);

        if(newCompany != null){
            return Response.status(Response.Status.CREATED).entity(newCompany).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response update(CompanyUpdateRequest request){
        CompanyEntity company = new CompanyEntity(request.getId(), request.getCompanyName(), request.getEstYear(), request.getCountry(), request.getKnownProducts());
        CompanyEntity newCompany = this.companyService.save(company);
        if(newCompany != null){
            return Response.status(Response.Status.OK).entity(newCompany).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}/getdata")
    public Response getCompanyData(@PathParam("id") Long id){
        try{
            CompanyEntity companyResponse = this.companyService.findById(id);

            return Response.status(Response.Status.OK).entity(companyResponse).build();


        } catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}
