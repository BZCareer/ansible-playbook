package com.bzcareer.monitord.core.model;

import org.springframework.data.annotation.Id;

public class Role {
	@Id
	private String id;
	private String name;
	private String group;

	
	
	public Role(String id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", group=" + group + "]";
	}

}
