package hu.pte.mik.prog4.potpotzh.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompanyResponse")
public class CompanyResponse {

    @XmlElement(required = true)
    private String companyId;

    @XmlElement(required = true)
    private Long soldProducts;

    public CompanyResponse() {}

    public CompanyResponse(String companyId, Long soldProducts) {
        this.companyId = companyId;
        this.soldProducts = soldProducts;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Long soldProducts) {
        this.soldProducts = soldProducts;
    }
}