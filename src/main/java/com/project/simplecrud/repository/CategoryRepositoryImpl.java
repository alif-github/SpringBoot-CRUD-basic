package com.project.simplecrud.repository;

import com.project.simplecrud.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryRepository")
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    JdbcTemplate databases;

    @Override
    public void saveCategory(Category category) {
        String sql = "INSERT INTO category(category) VALUES(?)";
        databases.update(sql,
                category.getCategory());
    }

    @Override
    public List<Category> findAllCategory() {
        String sql = "SELECT * FROM category";
        return databases.query(sql,
                (rs, rowNum) ->
                    new Category(
                            rs.getLong("idCategory"),
                            rs.getString("category")
                    ));
    }

    @Override
    public List<Category> findAllCategorySave() {
        String sql = "SELECT * FROM category ORDER BY idCategory DESC LIMIT 1";
        return databases.query(sql,
                (rs, rowNum) ->
                        new Category(
                                rs.getLong("idCategory"),
                                rs.getString("category")
                        ));
    }

    @Override
    public Category findByIdCategory(long idCategory) {
        String sql = "SELECT * FROM category WHERE idCategory ="+idCategory+"";
        return databases.queryForObject(sql,
                (rs, rowNum) ->
                    new Category(
                            rs.getLong("idCategory"),
                            rs.getString("category")
                    ));
    }

    @Override
    public List<Category> findByNameCategory(String category) {
        String sql = "SELECT * FROM category WHERE category LIKE ?";
        return databases.query(sql,
                new Object[]{"%"+category+"%"},
                (rs, rowNum) ->
                    new Category(
                            rs.getLong("idCategory"),
                            rs.getString("category")
                            ));
    }

    @Override
    public Category findByIdAndNameCategory(long idCategory, String category) {
        String sql = "SELECT * FROM category WHERE idCategory ="+idCategory+" AND category ='"+category+"'";
        return databases.queryForObject(sql,
                (rs, rowNum) ->
                        new Category(
                                rs.getLong("idCategory"),
                                rs.getString("category")
                        ));
    }

    @Override
    public void updateDataCategory(long idCategory, Category category) {
        String sql = "UPDATE category\n" +
                "SET category = ?\n" +
                "WHERE idCategory = ?;";
        databases.update(sql, category.getCategory(), idCategory);
    }

    @Override
    public void deleteAllCategory() {
        String sql = "DELETE FROM category";
        databases.execute(sql);
    }

    @Override
    public void deleteByIdCategory(long idCategory) {
        String sql = "DELETE FROM category WHERE idCategory="+idCategory+"";
        databases.execute(sql);
    }

    @Override
    public void deleteByNameCategory(String category) {
        String sql = "DELETE FROM category WHERE category='"+category+"'";
        databases.execute(sql);
    }
}
