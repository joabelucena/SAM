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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String roleName;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "role", targetEntity = Users.class, fetch = FetchType.EAGER)
	@JsonManagedReference(value="role")
	private List<Users> users;
	
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_features",
			joinColumns=@JoinColumn(name="roleId"),
			inverseJoinColumns=@JoinColumn(name="featureId"))
	@OrderBy(clause="featureId")
	private Set<SystemFeature> features;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_menu",
			joinColumns=@JoinColumn(name="roleId"),
			inverseJoinColumns=@JoinColumn(name="menuId"))
	@OrderBy(clause="menuId")
	private Set<Menu> menus;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_services_stations",
			joinColumns=@JoinColumn(name="roleId"),
			inverseJoinColumns=@JoinColumn(name="stationId"))
	@OrderBy(clause="stationId")
	private Set<ServiceStation> stations;
	

	public Role(){}

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

	public Set<ServiceStation> getStations() {
		return stations;
	}

	public void setStations(Set<ServiceStation> stations) {
		this.stations = stations;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
	
	

	
}
