package br.com.ttrans.samapp.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Repository;

import br.com.ttrans.samapp.dao.StatusRuleDao;
import br.com.ttrans.samapp.model.Role;
import br.com.ttrans.samapp.model.ServiceOrderStatus;
import br.com.ttrans.samapp.model.StatusRule;

@SuppressWarnings("rawtypes")
@Repository
public class StatusRuleDaoImpl implements StatusRuleDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(StatusRule rule, Authentication authentication) {
		rule.setUsr_insert(authentication.getName());
		session.getCurrentSession().save(rule);

	}

	@Override
	public void edit(StatusRule rule, Authentication authentication) {
		rule.setUsr_update(authentication.getName());
		session.getCurrentSession().update(rule);

	}

	@Override
	public void delete(StatusRule rule, Authentication authentication) {
		rule.setUsr_update(authentication.getName());
		rule.setDeleted("*");
		session.getCurrentSession().update(rule);

	}
	
	@Override
	public StatusRule get(int id) {
		return (StatusRule) session.getCurrentSession().get(StatusRule.class, id);
		
	}
	
	@Override
	public List getAllowedStatus(Role role, ServiceOrderStatus curstatus){
		
		Criteria crit = session.getCurrentSession().createCriteria(StatusRule.class,"rule");
		
		crit.createAlias("rule.nxtstatus"	,"rule_nxtstatus",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("rule_nxtstatus.sos_id"));
		projList.add(Projections.property("rule_nxtstatus.sos_description"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("rule.deleted","*"));
		crit.add(Restrictions.ne("rule_nxtstatus.deleted","*"));
		
		crit.add(Restrictions.eq("rule.role",role));
		crit.add(Restrictions.eq("rule.curstatus",curstatus));
		
		List resultsList = crit.list();
		
		return resultsList;
	}

	@Override
	public List loadData() {

		Criteria crit = session.getCurrentSession().createCriteria(StatusRule.class,"rule");
		
		crit.createAlias("rule.role"		,"rule_role",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("rule.curstatus"	,"rule_curstatus",CriteriaSpecification.LEFT_JOIN);
		crit.createAlias("rule.nxtstatus"	,"rule_nxtstatus",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("rule.sru_id"));
		
		projList.add(Projections.property("rule_role.roleName"));
		projList.add(Projections.property("rule_curstatus.sos_description"));
		projList.add(Projections.property("rule_nxtstatus.sos_description"));
		
		projList.add(Projections.property("rule.sru_log_remark"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.ne("rule.deleted","*"));
		
		crit.add(Restrictions.ne("rule_curstatus.deleted","*"));
		crit.add(Restrictions.ne("rule_nxtstatus.deleted","*"));
		
		List resultsList = crit.list();
		
		return resultsList;
	}
}
