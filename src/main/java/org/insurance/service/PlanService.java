package org.insurance.service;

import java.util.List;
import java.util.Map;

import org.insurance.bean.Plan;

public interface PlanService {

	public Map<Integer, String> getPlanCategories();

	public List<Plan> getAllPlans();

	public Plan getPlanById(Integer planId);

	public boolean savePlan(Plan plan);

	public boolean updatePlan(Plan plan);

	public boolean deletePlanById(Integer planId);

	public boolean planStatusChagne(Integer planId, String status);

}
