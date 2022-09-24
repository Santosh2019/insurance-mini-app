package org.insurance.service;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.insurance.bean.Plan;
import org.insurance.bean.PlanCategory;
import org.insurance.repository.PlanCategoryRepo;
import org.insurance.repository.PlanRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Autowired
	private PlanRepo planRepo;

	private Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categories = planCategoryRepo.findAll();
		Map<Integer, String> categoresMap = new HashMap<>();
		categories.forEach(category -> categoresMap.put(category.getPlanCategoryId(), category.getCategoryName()));
		return categoresMap;
	}

	public boolean savePlan(Plan plan) {
		Plan planSaved = planRepo.save(plan);
		return planSaved.getPlanId() != null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan isPlan = planRepo.save(plan);
		return isPlan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			logger.error("Exception Occured");
		}
		return status;
	}

	@Override
	public List<Plan> getAllPlans() {
		List<Plan> planList = planRepo.findAll();
		return planList;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById != null) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean planStatusChagne(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			Plan plan = findById.get();
			plan.setPlanActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}
}
