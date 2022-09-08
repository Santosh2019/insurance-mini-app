package org.insurance.controller;

import java.util.List;
import java.util.Map;

import org.insurance.bean.Plan;
import org.insurance.constants.AppConstants;
import org.insurance.props.AppProperties;
import org.insurance.service.PlanServiceImpl;
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

	private PlanServiceImpl planServiceImpl;

	private Map<String, String> messages;

	public PlanController(PlanServiceImpl planServiceImpl, AppProperties appProps) {
		this.planServiceImpl = planServiceImpl;
		this.messages = appProps.getMessages();
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getAllCategories() {

		Map<Integer, String> categories = planServiceImpl.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		String respMsg = AppConstants.EMPTY_STR;

		boolean isSaved = planServiceImpl.savePlan(plan);

		if (isSaved) {

			respMsg = AppConstants.PLAN_SAVE_SUCCESS;

		} else {

			respMsg = AppConstants.PLAN_DELETATION_FAIL;
		}
		return new ResponseEntity<String>(respMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getallPlans() {
		List<Plan> isList = planServiceImpl.getAllPlans();
		return new ResponseEntity<>(isList, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {

		Plan plan = planServiceImpl.getPlanById(planId);

		return new ResponseEntity<Plan>(plan, HttpStatus.OK);
	}

	@PostMapping("/plan/{planId}")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		{
			String respMsg = AppConstants.EMPTY_STR;

			boolean isUpdated = planServiceImpl.updatePlan(plan);

			if (isUpdated) {

				respMsg = AppConstants.PLAN_UPDATED_SUCCESS;
			} else {
				respMsg = AppConstants.PLAN_UPDATION_FAIL;
			}

			return new ResponseEntity<String>(respMsg, HttpStatus.OK);
		}
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(Integer planId) {

		String respMsg = AppConstants.EMPTY_STR;
		boolean isDeleted = planServiceImpl.deletePlanById(planId);
		if (isDeleted) {

			respMsg = AppConstants.PLAN_DELETED_SUCCESS;
		} else {
			respMsg = AppConstants.PLAN_DELETATION_FAIL;
		}
		return new ResponseEntity<String>(respMsg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId/{status}")
	public ResponseEntity<String> statusChange(@RequestParam Integer planId, @PathVariable String status) {

		String respMsg = AppConstants.EMPTY_STR;
		boolean isStatusChange = planServiceImpl.planStatusChagne(planId, status);

		if (isStatusChange) {
			respMsg = AppConstants.STATUS_CAHNGE_SUCCESS;
		} else {
			respMsg = AppConstants.STATUS_CHANGE_FAIL;
		}
		return new ResponseEntity<String>(respMsg, HttpStatus.OK);
	}

}
