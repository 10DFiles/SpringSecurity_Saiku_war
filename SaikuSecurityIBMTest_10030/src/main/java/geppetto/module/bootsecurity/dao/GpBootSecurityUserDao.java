package geppetto.module.bootsecurity.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.SaikuSecurityIBMTest.domain.core.GpUser;

import geppetto.module.bootsecurity.idao.IGpUserDao;


@Repository
@Transactional
public class GpBootSecurityUserDao implements IGpUserDao {

	private Log log = LogFactory.getLog(getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GpUser findUser(String username){
		try{

			String login = "select * from GpUser where username=:username";
			Query result = entityManager.createNativeQuery(login,GpUser.class)
					.setParameter("username", username);
			List<GpUser> list  = result.getResultList();
			System.out.println("size of user list : " + list.size());
			if (list.size() > 0) {
				GpUser user_from_db = list.get(0);
				return user_from_db;
			}

		}catch(DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GpUser insert(GpUser gpUser) throws Exception {

		entityManager.createNativeQuery(
			    "INSERT INTO gpuser (username, password,startdate,newuser) VALUES (?, ?, ?, ?)")
			    //.setParameter(1, keyHolder.getKey().longValue())
			    .setParameter(1, gpUser.getUsername())
			    .setParameter(2, gpUser.getPassword())
			    .setParameter(3, new Date())
			    .setParameter(4, true)
			    .executeUpdate();

		return gpUser;

	}
}
