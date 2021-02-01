package com.project.simplecrud.model;

public class Category {
    private long idCategory;
    private String category;

    public Category() {
    }

    public Category(Long idCategory,String category) {
        this.idCategory = idCategory;
        this.category = category;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Category category1 = (Category) o;
        if (idCategory!=category1.idCategory)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (idCategory ^ (idCategory >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "category{" +
                "idCategory=" + idCategory +
                ", category='" + category + '\'' +
                '}';
    }
}
