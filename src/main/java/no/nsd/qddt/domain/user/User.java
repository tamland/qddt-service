package no.nsd.qddt.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import no.nsd.qddt.domain.agency.Agency;
import no.nsd.qddt.domain.authority.Authority;
import no.nsd.qddt.domain.category.Category;
import no.nsd.qddt.domain.comment.Comment;
import no.nsd.qddt.domain.concept.Concept;
import no.nsd.qddt.domain.instruction.Instruction;
import no.nsd.qddt.domain.instrument.Instrument;
import no.nsd.qddt.domain.controlconstruct.ControlConstruct;
import no.nsd.qddt.domain.othermaterial.OtherMaterial;
import no.nsd.qddt.domain.question.Question;
import no.nsd.qddt.domain.responsedomain.ResponseDomain;
import no.nsd.qddt.domain.code.Code;
import no.nsd.qddt.domain.study.Study;
import no.nsd.qddt.domain.surveyprogram.SurveyProgram;
import no.nsd.qddt.domain.topicgroup.TopicGroup;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Dag Østgulen Heradstveit
 */
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Entity
@Table(name = "USER_ACCOUNT")
public class User {

    @Id
    @Column(name = "id")
    @Type(type="pg-uuid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2", parameters = {
            @org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy") })
    private UUID id;

    @Column(name = "username")
    private String username;

    @JsonIgnore

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="user_authority",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="authority_id"))
    private Set<Authority> authorities = new HashSet<>();

    @JsonIgnore
	@OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<SurveyProgram> surveyPrograms = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Study> studies = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Instrument> instrument = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<ControlConstruct> controlConstructs = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Instruction> instructions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<TopicGroup> topicGroups = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<OtherMaterial> otherMaterials = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Concept> concepts = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<ResponseDomain> responseDomains = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Code> codes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy="modifiedBy", cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agency_id")
    private Agency agency;

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<SurveyProgram> getSurveyPrograms() {
        return surveyPrograms;
    }

    public void setSurveyPrograms(Set<SurveyProgram> surveyPrograms) {
        this.surveyPrograms = surveyPrograms;
    }

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Instrument> getInstrument() {
        return instrument;
    }

    public void setInstrument(Set<Instrument> instrument) {
        this.instrument = instrument;
    }

    public Set<ControlConstruct> getControlConstructs() {
        return controlConstructs;
    }

    public void setControlConstructs(Set<ControlConstruct> controlConstructs) {
        this.controlConstructs = controlConstructs;
    }

    public Set<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(Set<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<TopicGroup> getTopicGroups() {
        return topicGroups;
    }

    public void setTopicGroups(Set<TopicGroup> topicGroups) {
        this.topicGroups = topicGroups;
    }

    public Set<OtherMaterial> getOtherMaterials() {
        return otherMaterials;
    }

    public void setOtherMaterials(Set<OtherMaterial> otherMaterials) {
        this.otherMaterials = otherMaterials;
    }

    public Set<Concept> getConcepts() {
        return concepts;
    }

    public void setConcepts(Set<Concept> concepts) {
        this.concepts = concepts;
    }

    public Set<ResponseDomain> getResponseDomains() {
        return responseDomains;
    }

    public void setResponseDomains(Set<ResponseDomain> responseDomains) {
        this.responseDomains = responseDomains;
    }

    public Set<Code> getCodes() {
        return codes;
    }

    public void setCodes(Set<Code> codes) {
        this.codes = codes;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return (email != null ? !email.equals(user.email) : user.email != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + (password != null ? "[NOT NULL]":"[NULL]") + '\'' +
                ", email='" + email + '\'' +
//                ", agency='"  + (agency != null ? agency.getName(): "[NULL]") + '\'' +
                '}';
    }
}
