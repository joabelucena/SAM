package br.com.ttrans.samapp.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OrderBy;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String roleName;
	
	@OneToMany(mappedBy = "role")
	private List<Users> users;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_features",
			joinColumns=@JoinColumn(name="roleId"),
			inverseJoinColumns=@JoinColumn(name="featureId"))
	@OrderBy(clause="featureId")
	private Set<SystemFeature> features;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_menu",
			joinColumns=@JoinColumn(name="roleId"),
			inverseJoinColumns=@JoinColumn(name="menuId"))
	@OrderBy(clause="menuId")
	private Set<Menu> menus;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_services_stations",
			joinColumns=@JoinColumn(name="roleId"),
			inverseJoinColumns=@JoinColumn(name="stationId"))
	@OrderBy(clause="stationId")
	private Set<Menu> stations;

	public Role(){}
	public Role(int id, String roleName, List<Users> users,
			Set<SystemFeature> features, Set<Menu> menus, Set<Menu> stations) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.users = users;
		this.features = features;
		this.menus = menus;
		this.stations = stations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public Set<SystemFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<SystemFeature> features) {
		this.features = features;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public Set<Menu> getStations() {
		return stations;
	}

	public void setStations(Set<Menu> stations) {
		this.stations = stations;
	}
}
