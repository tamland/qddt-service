package no.nsd.qddt.utils.builders;


import no.nsd.qddt.domain.category.Category;

/**
 * @author Stig Norland
 */
public class CategoryBuilder {
    private String category;
    private String tag;

    public CategoryBuilder setCategory(String name) {
        this.category = name;
        return this;
    }

    public CategoryBuilder setLabel(String label) {
        this.tag = label;
        return this;
    }


    public Category createCode() {
        Category category = new Category();
        category.setName(this.category);
        category.setLabel(tag);
        return category;
    }

}