package hu.pte.mik.zhgyak3.tag;


import hu.pte.mik.zhgyak3.model.Pet;
import hu.pte.mik.zhgyak3.service.PetService;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class PetGlobalIdentifierTag extends SimpleTagSupport {

    private final PetService petService = new PetService();
    private Pet pet;

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = this.getJspContext().getOut();
        out.println(String.format("%s", this.petService.getGlobalIdentifier(pet)));
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}

