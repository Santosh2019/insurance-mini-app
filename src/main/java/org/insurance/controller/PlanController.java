package org.insurance.controller;

import java.util.List;
import java.util.Map;

import org.insurance.bean.Plan;
import org.insurance.service.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {

	@Autowired
	PlanServiceImpl planServiceImpl;

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getAllCategories() {

		Map<Integer, String> categories = planServiceImpl.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);

	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String respMsg = "";
		boolean isSaved = planServiceImpl.savePlan(plan);
		if (isSaved) {
			respMsg = "Plan Saved";
		} else {

			respMsg = "plan not Saved";
		}
		return new ResponseEntity<>(respMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getallPlans() {
		List<Plan> isList = planServiceImpl.getAllPlans();
		return new ResponseEntity<>(isList, HttpStatus.OK);
	}

	
	
	  @GetMapping("/plan/{planId}")
	  public ResponseEntity<Plan>editPlan(@PathVariable Integer planId) {
	  
	  Plan plan = planServiceImpl.getPlanById(planId);
	  
	  return new ResponseEntity<>(plan, HttpStatus.OK); }
	  
	  

	@PostMapping("/plan/{planId}")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		{

			String responseMsg;
			boolean isUpdated = planServiceImpl.updatePlan(plan);

			if (isUpdated) {

				responseMsg = "Plan updated";
			} else {
				responseMsg = "Plan not Updated";
			}

			return new ResponseEntity<>(responseMsg, HttpStatus.OK);
		}
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(Integer planId) {

		String responseMsg = "";
		boolean isDeleted = planServiceImpl.deletePlanById(planId);
		if (isDeleted) {
			responseMsg = "Plan Deleted";
		} else {
			responseMsg = "Plan Not Deleted";
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);

	}

	@PutMapping("/status-change/{planId/{status}")
	public ResponseEntity<String> statusChange(@RequestParam Integer planId, @PathVariable String status) {

		String responseMsg = "";
		boolean isStatusChange = planServiceImpl.planStatusChagne(planId, status);

		if (isStatusChange) {
			responseMsg = "Status Change Successfully";
		} else {
			responseMsg = "Status Not Changed";
		}

		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

}
