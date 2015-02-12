package br.com.ttrans.samapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OrderBy;

@Entity
public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String text;
	private String iconCls;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Menu parent;
	
	@OneToMany(mappedBy="parent",targetEntity=Menu.class,fetch=FetchType.EAGER)
	@OrderBy(clause="id")
	private Set<Menu> children;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="permissions",
			joinColumns=@JoinColumn(name="menu_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	@OrderBy(clause="role_id")
	private Set<Role> roles;
	
	private String className;
	public Menu(){}
	public Menu(int id, String text, String iconCls, Menu parent,
			Set<Menu> children, Set<Role> roles, String className) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parent = parent;
		this.children = children;
		this.roles = roles;
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
	public Set<Menu> getChildren() {
		return children;
	}
	public void setChildren(Set<Menu> children) {
		this.children = children;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
