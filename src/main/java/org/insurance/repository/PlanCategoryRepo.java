package org.insurance.repository;

import org.insurance.bean.PlanCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{

}
