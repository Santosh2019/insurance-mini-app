package org.insurance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.insurance.bean.Plan;
import org.insurance.bean.PlanCategory;
import org.insurance.repository.PlanCategoryRepo;
import org.insurance.repository.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanCategoryRepo planCategoryRepo;

	@Autowired
	PlanRepo planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCategory> categories = planCategoryRepo.findAll();

		Map<Integer, String> categoresMap = new HashMap<>();

		categories.forEach(category -> categoresMap.put(category.getPlanCategoryId(), category.getCategoryName()));

		return categoresMap;
	}

	public boolean savePlan(Plan plan) {
		// TODO Auto-generated method stub

		Plan planSaved = planRepo.save(plan);

		/*
		 * if(planSaved!=null) {
		 * 
		 * return true; //this code is writte by fresher } else { return false; }
		 */
		/*
		 * return planSaved.getPlanId()!=null?true:false; //this code is written by one
		 * years of experience
		 * 
		 */
		return planSaved.getPlanId() != null; // return by 3+yrs experience developer
	}

	@Override
	public boolean updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		Plan isPlan = planRepo.save(plan);
		return isPlan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		// TODO Auto-generated method stub
		boolean status = false;

		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub

		List<Plan> planList = planRepo.findAll();

		return planList;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById != null) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean planStatusChagne(Integer planId, String status) {
		// TODO Auto-generated method stub
					Optional<Plan>findById=planRepo.findById(planId);
					if(findById.isPresent()) {
					Plan plan=findById.get();
					plan.setPlanActiveSw(status);
						planRepo.save(plan);
						return true;
					}
				return false;

	}
}
