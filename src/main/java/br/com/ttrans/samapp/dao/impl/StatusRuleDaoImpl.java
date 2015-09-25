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

@Repository
public class StatusRuleDaoImpl implements StatusRuleDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(StatusRule rule, Authentication authentication) {
		rule.setInsert(authentication.getName());
		session.getCurrentSession().save(rule);

	}

	@Override
	public void edit(StatusRule rule, Authentication authentication) {
		rule.setUpdate(authentication.getName());
		session.getCurrentSession().update(rule);

	}

	@Override
	public void delete(StatusRule rule, Authentication authentication) {
		session.getCurrentSession().delete(rule);

	}
	
	@Override
	public StatusRule get(int id) {
		return (StatusRule) session.getCurrentSession().get(StatusRule.class, id);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOrderStatus> getAllowedStatus(Role role, ServiceOrderStatus curstatus){
		
		Criteria crit = session.getCurrentSession().createCriteria(StatusRule.class);
		
		crit.createAlias("rule.nxtstatus"	,"rule_nxtstatus",CriteriaSpecification.LEFT_JOIN);
		
		ProjectionList projList = Projections.projectionList();
		
		projList.add(Projections.property("rule_nxtstatus.id"));
		projList.add(Projections.property("rule_nxtstatus.desc"));
		
		crit.setProjection(projList);
		
		crit.add(Restrictions.eq("rule.role",role));
		crit.add(Restrictions.eq("rule.curstatus",curstatus));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceOrderStatus> getStatusByRole(Role role){

		Criteria crit = session.getCurrentSession().createCriteria(StatusRule.class);
		
		crit.setProjection(Projections.projectionList().
				add(Projections.groupProperty("nxtstatus")));		
		
		crit.add(Restrictions.eq("role",role));
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StatusRule> loadData() {

		return session.getCurrentSession().createCriteria(StatusRule.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
		
	}
}
