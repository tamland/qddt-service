package no.nsd.qddt.domain.concept;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import no.nsd.qddt.domain.AbstractEntityAudit;
import no.nsd.qddt.domain.author.Author;
import no.nsd.qddt.domain.authorable.Authorable;
import no.nsd.qddt.domain.comment.Comment;
import no.nsd.qddt.domain.commentable.Commentable;
import no.nsd.qddt.domain.question.Question;
import no.nsd.qddt.domain.questionItem.QuestionItem;
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
public class Concept extends AbstractEntityAudit implements Commentable, Authorable {


    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH}, orphanRemoval = true)
    @OrderColumn()
    @JoinColumn(name = "parent_id")
    private Set<Concept> children = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "topicgroup_id", updatable = false)
    private TopicGroup topicGroup;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinTable(name = "CONCEPT_QUESTION_ITEM",
            joinColumns = {@JoinColumn(name = "concept_id")},
            inverseJoinColumns = {@JoinColumn(name = "questionItem_id")})
    private Set<QuestionItem> questionItems = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinTable(name = "CONCEPT_AUTHORS",
            joinColumns = {@JoinColumn(name ="concept_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<>();


    @Column(name = "label")
    private String label;

    @Column(name = "description", length = 10000)
    private String description;

    @Transient
    private Set<Comment> comments = new HashSet<>();

    public Concept() {

    }

    @PreUpdate
    private void checkAuthor(){
        authors.forEach(a->a.addConcept(this));

    }

    @PreRemove
    private void removeReferencesFromConcept(){
//        getChildren().forEach(C-> this.getTopicGroup().addConcept(C));
//        getAuthors().forEach( A->A.removeConcept(this));
//        getQuestionItems().forEach(QI->QI.removeFromConcept(this));
//        getComments().forEach(C -> C.removeChildren());
        if (getTopicGroup() != null)
            getTopicGroup().removeConcept(this);
        getComments().clear();
        getChildren().clear();
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
            questionItem.setChangeComment("Added to Concept");
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
        setChangeComment("Concept added");
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


    @Override
    public void addAuthor(Author user) {
        authors.add(user);
    }

    @Override
    public Set<Author> getAuthors() {
        return authors;
    }

    @Override
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Concept)) return false;
        if (!super.equals(o)) return false;

        Concept concept = (Concept) o;

//        if (parent != null ? !parent.equals(concept.parent) : concept.parent != null) return false;
        if (children != null ? !children.equals(concept.children) : concept.children != null) return false;
//        if (topicGroup != null ? !topicGroup.equals(concept.topicGroup) : concept.topicGroup != null) return false;
//        if (questions != null ? !questions.equals(concept.questions) : concept.questions != null) return false;
        if (label != null ? !label.equals(concept.label) : concept.label != null) return false;
        if (description != null ? !description.equals(concept.description) : concept.description != null) return false;
        return !(comments != null ? !comments.equals(concept.comments) : concept.comments != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
//        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (children != null ? children.size() : 0);
//        result = 31 * result + (topicGroup != null ? topicGroup.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Concept{" +
                "label='" + label + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }


}

