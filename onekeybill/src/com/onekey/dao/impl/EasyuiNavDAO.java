package com.onekey.dao.impl;

import com.onekey.entity.EasyuiNav;
import com.onekey.util.BaseHibernateDAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * EasyuiNav entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.EasyuiNav
 * @author MyEclipse Persistence Tools
 */
public class EasyuiNavDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(EasyuiNavDAO.class);
	// property constants
	public static final String TEXT = "text";
	public static final String STATE = "state";
	public static final String ICON_CLS = "iconCls";
	public static final String URL = "url";
	public static final String NID = "nid";

	public void save(EasyuiNav transientInstance) {
		log.debug("saving EasyuiNav instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EasyuiNav persistentInstance) {
		log.debug("deleting EasyuiNav instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EasyuiNav findById(java.lang.Integer id) {
		log.debug("getting EasyuiNav instance with id: " + id);
		try {
			EasyuiNav instance = (EasyuiNav) getSession().get("com.EasyuiNav",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<EasyuiNav> findByExample(EasyuiNav instance) {
		log.debug("finding EasyuiNav instance by example");
		try {
			List<EasyuiNav> results = (List<EasyuiNav>) getSession()
					.createCriteria("com.EasyuiNav").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EasyuiNav instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from EasyuiNav as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<EasyuiNav> findByText(Object text) {
		return findByProperty(TEXT, text);
	}

	public List<EasyuiNav> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<EasyuiNav> findByIconCls(Object iconCls) {
		return findByProperty(ICON_CLS, iconCls);
	}

	public List<EasyuiNav> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<EasyuiNav> findByNid(Object nid) {
		return findByProperty(NID, nid);
	}

	public List findAll(int nid) {
		log.debug("finding all EasyuiNav instances");
		try {
			String queryString = "from EasyuiNav where nid='"+nid+"'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EasyuiNav merge(EasyuiNav detachedInstance) {
		log.debug("merging EasyuiNav instance");
		try {
			EasyuiNav result = (EasyuiNav) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EasyuiNav instance) {
		log.debug("attaching dirty EasyuiNav instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EasyuiNav instance) {
		log.debug("attaching clean EasyuiNav instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}