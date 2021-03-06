package no.nsd.qddt.domain.instruction;

import no.nsd.qddt.domain.AbstractEntityAudit;
import no.nsd.qddt.domain.controlconstructinstruction.ControlConstructInstruction;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stig Norland
 */

@Entity
@Audited
@Table(name = "INSTRUCTION", uniqueConstraints = {@UniqueConstraint(columnNames = {"name","description","agency_id"},name = "UNQ_INSTRUCTION_NAME")})
public class Instruction extends AbstractEntityAudit {

    //TODO ArrayList dosn't work with Enver
    @JsonBackReference(value = "controlConstructInstructionRef")
    @OneToMany(mappedBy = "instruction")
    private List<ControlConstructInstruction> controlConstructInstructions =new ArrayList<>();

    @Column(name = "description", length = 2000,nullable = false)
    private String description;


    public Instruction() {
    }

//    public List<ControlConstruct> getControlConstructsPost() {
//        return controlConstructsPost;
//    }
//
//    public void setControlConstructsPost(List<ControlConstruct> controlConstructsPost) {
//        this.controlConstructsPost = controlConstructsPost;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        setName(description.toUpperCase().replace(' ','_').substring(0,description.length()>25?25:description.length()));
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instruction)) return false;
        if (!super.equals(o)) return false;

        Instruction that = (Instruction) o;

        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
