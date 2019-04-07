package com.revature.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.controller.ReimbursementController;
import com.revature.models.Reimbursement;

@RestController
@RequestMapping("/reimbursements")
public class ReimbursementController {
	
private Logger log = Logger.getLogger(ReimbursementController.class);
	
	private List<Reimbursement> reimbursement = new ArrayList<>();
	
	public ReimbursementController() {
		reimbursement.add(new Reimbursement(1, 1, 100.50, "pending", "Travel"));
		reimbursement.add(new Reimbursement(1, 2, 1337.00, "accepted", "Travel"));
		reimbursement.add(new Reimbursement(2, 3, 9999, "denied", "Food"));
		reimbursement.add(new Reimbursement(1, 4, 1223.32 , "accepted", "Certifications"));
		reimbursement.add(new Reimbursement(3, 5, 43245.54, "pending" , "Travel"));
	}

	@GetMapping
	public List<Reimbursement> getAllReimbursements(){
		log.info("getting all reimbursements");
		return reimbursement;
	}
	
	@GetMapping("/{reimbursementId}")
	public Reimbursement getReimbursementById(@PathVariable("reimbursementId") Integer reimbursementId) {
		log.info("getting account by id: " + reimbursementId);
		for(Reimbursement a: reimbursement) {
			if(reimbursementId == a.getReimbursementId()) {
				return a;
			}
		}
		return null;
	}
	
	@GetMapping("/reimbursement/{reimbursementId}")
	public List<Reimbursement> getReimbursementByEmployeeId(@PathVariable("employeeId") Integer employeeId){
		return reimbursement.stream().filter(reim -> reim.getEmployeeId()==employeeId)
				.collect(Collectors.toList());
	}
	
}
