package com.ecommerceBackend.ecommerceBackend.repository;

import com.ecommerceBackend.ecommerceBackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
   public Category findByName(String Name);

   @Query("SELECT c from Category c Where c.name=:name And c.parentCategory.name=:parentCategoryName")
   public Category findByNameAndParent(@Param("name")String name, @Param("parentCategoryName")String parentCategoryName);
}
