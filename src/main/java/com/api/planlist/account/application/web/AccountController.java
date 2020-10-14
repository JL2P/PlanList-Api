package com.api.planlist.account.application.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.api.planlist.account.application.dto.AccountSigninDto;
import com.api.planlist.account.application.dto.AccountSignupDto;
import com.api.planlist.account.domain.Account;
import com.api.planlist.account.domain.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"Account"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts/")
public class AccountController {

	private final AccountService accountService;
	
	@GetMapping()
	public List<Account> getAccounts() {
		return accountService.getAccountList();
	}
	
	@GetMapping("{accountId}/")
	public Account getAccount(@PathVariable String accountId) {
		return accountService.getAccount(accountId);
	}
	
	@ApiOperation(value ="회원가입 기능", notes = "AccountSignupDto타입을 이용하여 데이터를 받아온다.")
	@PostMapping("signup/")
	public Account addAccount(@RequestBody AccountSignupDto accountSignupDto) {
		Account newAccount = accountSignupDto.toDomain();
		return accountService.addAccount(newAccount);
	}
	
	@ApiOperation(value = "수정 기능", notes = "로그인 되어 있는 회원 정보를 수정한다.")
	@PutMapping("edit/")
	public Account modifyAccount(@RequestBody AccountSignupDto accountSignupDto) {
		Account newModifyAccount = accountSignupDto.toDomain();
		return accountService.modifyAccount(newModifyAccount);
	}
	
	@DeleteMapping("signout/{accountId}/")
	public void deleteAccount(@PathVariable String accountId) {
		accountService.deleteAccount(accountId);
	}
	//로그인
	@ApiOperation(value = "로그인", notes = "회원 로그인.")
	@PostMapping("signin/")
	public Account signinAccount(@RequestBody AccountSigninDto AccountSigninDto){
		Account newSigninAccount = AccountSigninDto.toDomain();
		return accountService.signinAccount(newSigninAccount);
	}
	//auth
	@ApiOperation(value = "내 정보", notes = "로그인 되어 있는 회원 정보를 조회한다.")
	@PostMapping("auth/")
	public Account authAccount(@RequestBody AccountSignupDto accountSignupDto) {
		Account newAuthAccount = accountSignupDto.toDomain();
		return accountService.authAccount(newAuthAccount);
	}
}
