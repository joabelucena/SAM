package br.com.ttrans.samapp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Menu {
	@Id
	private int id;
	private String text;
	private String iconCls;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Menu parent;
	
	@OneToMany(mappedBy="parent",targetEntity=Menu.class,fetch=FetchType.EAGER)
	private List<Menu> children;
	
	/*
	@JoinTable(name="permissions",
			joinColumns=@JoinColumn(name="menu_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private List<Role> roles;
	*/
	private String className;
	public Menu(){}
	public Menu(int id, String text, String iconCls, Menu parent,
			List<Menu> children, List<Role> roles, String className) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parent = parent;
		this.children = children;
		//this.roles = roles;
		this.className = className;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	/*
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	*/
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", children=" + children + "]";
	}
	
}
