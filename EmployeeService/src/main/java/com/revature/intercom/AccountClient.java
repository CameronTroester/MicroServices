package com.revature.intercom;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Account;

@FeignClient(name="reimbursement-service", fallback=AccountClientFallback.class)
public interface AccountClient {
	
	@GetMapping("reimbursements/employee/{employeeId}")
	public List<Account> getAccountsByEmployeeId(@PathVariable("employeeId") Integer employeeId);

}
