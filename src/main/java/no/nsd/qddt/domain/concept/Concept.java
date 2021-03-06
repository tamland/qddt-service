package no.nsd.qddt.domain.concept;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import no.nsd.qddt.domain.AbstractEntityAudit;
import no.nsd.qddt.domain.comment.Comment;
import no.nsd.qddt.domain.commentable.Commentable;
import no.nsd.qddt.domain.question.Question;
import no.nsd.qddt.domain.questionItem.QuestionItem;
import no.nsd.qddt.domain.refclasses.TopicRef;
import no.nsd.qddt.domain.topicgroup.TopicGroup;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * <ul class="inheritance">
 *     <li>A Concept consist of one or more QuestionItems.</li>
 *     <ul class="inheritance">
 *         <li>Every QuestionItem will have a Question.</li>
 *         <li>Every QuestionItem will have a ResponseDomain.</li>
 *     </ul>
 * </ul>
 * <br>
 * ConceptScheme: Concepts express ideas associated with objects and means of representing the concept
 *
 * @author Stig Norland
 * @author Dag Østgulen Heradstveit
 */

@Audited
@Entity
@Table(name = "CONCEPT")
public class Concept extends AbstractEntityAudit implements Commentable {


    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE}, orphanRemoval = true)
    @OrderColumn()
    @JoinColumn(name = "parent_id")
    private Set<Concept> children = new HashSet<>();

    @JsonBackReference(value = "TopicGroupRef")
    @ManyToOne()
    @JoinColumn(name = "topicgroup_id",updatable = false)
    private TopicGroup topicGroup;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "CONCEPT_QUESTION_ITEM",
            joinColumns = {@JoinColumn(name = "concept_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "questionItem_id", referencedColumnName = "id")})
    private Set<QuestionItem> questionItems = new HashSet<>();


    @Column(name = "label")
    private String label;

    @Column(name = "description", length = 10000)
    private String description;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Set<Comment> comments = new HashSet<>();

//    @Transient
//    @JsonSerialize
//    @JsonDeserialize
//    private TopicRef topicRef;


    public Concept() {

    }


    @PreRemove
    private void removeReferencesFromConcept(){
        System.out.println("Concept PreRemove");
        getQuestionItems().forEach(qi->qi.updateStatusQI(this));
        getQuestionItems().clear();
        if (getTopicGroup() != null)
            getTopicGroup().removeConcept(this);

    }

    @Override
    public UUID getId() {
        return super.getId();
    }


    public TopicGroup getTopicGroup() {
        return topicGroup;
    }


    public void setTopicGroup(TopicGroup topicGroup) {
        this.topicGroup = topicGroup;
    }


    public Set<QuestionItem> getQuestionItems() {
        return questionItems;
    }


    public void setQuestionItems(Set<QuestionItem> questions) {
        this.questionItems = questions;
    }


    public void addQuestion(Question question) {
        QuestionItem qi = new QuestionItem();
        qi.setQuestion(question);
        this.addQuestionItem(qi);
    }


    public void addQuestionItem(QuestionItem questionItem) {
        System.out.println("addQuestionItem...");
        if (!this.questionItems.contains(questionItem)) {
            questionItem.getConcepts().add(this);
            this.questionItems.add(questionItem);
            questionItem.setChangeKind(ChangeKind.UPDATED_HIERARCY_RELATION);
            questionItem.setChangeComment("QuestionItem Added");
        }
    }


    public Set<Concept> getChildren() {
        return children;
    }


    public void setChildren(Set<Concept> children) {
        this.children = children;
    }


    public void addChildren(Concept concept){
        this.setChangeKind(ChangeKind.UPDATED_HIERARCY_RELATION);
        setChangeComment("SubConcept added");
        this.children.add(concept);
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(String label) {
        this.label = label;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Set<Comment> getComments() {
        return comments;
    }


    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


    public void addComment(Comment comment) {
        comment.setOwnerId(this.getId());
        comments.add(comment);
    }

    public TopicRef getTopicRef() {
        try{
            return new TopicRef(getTopicGroup());
        } catch (Exception ex ) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Concept)) return false;
        if (!super.equals(o)) return false;

        Concept concept = (Concept) o;

        if (label != null ? !label.equals(concept.label) : concept.label != null) return false;
        return !(description != null ? !description.equals(concept.description) : concept.description != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Concept{" +
                " children=" + (children!= null ?  children.size() : "0") +
                ", topicGroup=" + (topicGroup!=null ? topicGroup.getName() :"null") +
                ", questionItems=" + (questionItems !=null ? questionItems.size() :"0") +
                ", comments=" + (comments != null ? comments.size() :"0") +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", name='" + super.getName() + '\'' +
                ", id ='" + super.getId() + '\'' +
                "} ";
    }
}

