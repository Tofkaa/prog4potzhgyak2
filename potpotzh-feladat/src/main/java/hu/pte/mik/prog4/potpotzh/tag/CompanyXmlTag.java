package hu.pte.mik.prog4.potpotzh.tag;

import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import hu.pte.mik.prog4.potpotzh.service.CompanyService;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class CompanyXmlTag extends SimpleTagSupport {

    private String companyId;

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (companyId == null || companyId.trim().isEmpty()) {
            return;
        }

        try {
            CompanyService service = new CompanyService();
            // A te service-edben a metódus neve findById
            CompanyEntity company = service.findById(Long.parseLong(companyId));

            if (company != null) {
                String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<company>\n" +
                        "    <id>" + company.getId() + "</id>\n" +
                        "    <name>" + company.getCompanyName() + "</name>\n" +
                        "    <foundedYear>" + company.getEstYear() + "</foundedYear>\n" +
                        "    <famousProduct>" + company.getKnownProducts() + "</famousProduct>\n" +
                        "</company>";

                String escapedXml = xml.replace("<", "&lt;").replace(">", "&gt;");
                getJspContext().getOut().write("<pre style='background-color: #f4f4f4; padding: 10px;'>" + escapedXml + "</pre>");
            }
        } catch (Exception e) {
            getJspContext().getOut().write("<p style='color: red;'>Hiba történt az XML generálásakor.</p>");
        }
    }
}