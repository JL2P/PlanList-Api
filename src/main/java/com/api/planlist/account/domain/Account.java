package com.api.planlist.account.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.api.planlist.todo.domain.Todo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@Column(name = "account_id", nullable = false, unique = true)
	private String accountId;
	private String name;  //�떎紐�
	
	@Column(unique = true)
	private String email; // �씠硫붿씪
	private String password;
	private String birth;
	private String gender;
	private String address;
	private String phone;
	private String introduce;
	private int rating;
	private String usedAt;
	private String displayAt;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	@JsonManagedReference
    private List<Todo> todos= new ArrayList<Todo>();

}