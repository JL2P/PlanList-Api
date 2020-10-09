package com.api.planlist.account.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.api.planlist.todo.domain.Todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@Column(name = "user_id", nullable = false)
	private String accountId;
	private String name;  //실명
	private String email; // 이메일
	private String password;
	private int age;
	private String gender;
	private String address;
	private String phone;
	private String introduce;
	private int rating;
	private String usedAt;
	private String displayAt;
	
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Todo> todos;

}