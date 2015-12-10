package com.lftechnology.lfkhoj.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.lftechnology.lfkhoj.expert.Expert;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for Domain object
 * 
 *
 */

//@Data
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name="domain.topic", query="select d from Domain d where d.topic=:topic")})
@Entity
@Table(name = "Domain")
public class Domain {
	 
	/**
	 * This is auto generating primary key for domain table.
	 */
	@Id
	@TableGenerator(name = "orgId", pkColumnName = "seq", pkColumnValue = "value", table = "ids")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "orgId")
	private int domainId;
	
	/**
	 * Topic field stores domain topic i.e css, java, php
	 */
	private String topic;

	@ManyToMany(mappedBy="domains", fetch=FetchType.LAZY)
	private Set<Expert> experts = new HashSet<Expert>();
}
