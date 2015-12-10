package com.lftechnology.lfkhoj.expert;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

import com.lftechnology.lfkhoj.domain.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Expert{

	@Id
	@TableGenerator(name = "orgIds", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "orgIds")
	private int expertId;
	private int hipChatId;

	@ManyToMany (fetch=FetchType.LAZY)
	@JoinTable(name = "domain_expert", joinColumns = {
			@JoinColumn(name = "expertId", referencedColumnName = "expertId") }, inverseJoinColumns = {
					@JoinColumn(name = "domainId", referencedColumnName = "domainId") })
	private Set<Domain> domains = new HashSet<Domain>();

	
}
