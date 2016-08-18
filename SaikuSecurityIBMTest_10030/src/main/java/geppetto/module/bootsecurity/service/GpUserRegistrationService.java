package geppetto.module.bootsecurity.service;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SaikuSecurityIBMTest.domain.core.GpUser;
import com.SaikuSecurityIBMTest.service.GpBaseService;

import geppetto.module.bootsecurity.dao.GpBootSecurityUserDao;

/**
 * Date Created: September 22 2012
 *
 * @author Dan Castillo
 *
 *         The purpose of this class is to provide the business logic needed to
 *         register a new user to the system
 *
 *
 *
 */
@Service("GpUserRegistrationService")
public class GpUserRegistrationService extends GpBaseService{

	@Autowired
	private GpBootSecurityUserDao user_Dao;

	/**
	 * Registers a new user to the system - the method will enforce any required
	 * fields on the User class
	 *
	 * @param newuser
	 *            {@link com.npb.gp.domain.core.GpUser}
	 * @return com.npb.gp.domain.core.GpUser - with ID populated
	 * @throws Exception
	 *
	 *
	 */

	public GpUser registerUser(GpUser newuser) throws Exception {
		try {
			System.out
					.println("In  GpUserRegistrationService - registerUser()");

		} catch (Exception e) {
			logger.error("error occured in GpUserRegistrationService"
					+ " registerUser method()");
			logger.error("the user id is: " + newuser.getId()
					+ " the error is:");

			e.printStackTrace();
			Locale lc = new Locale(newuser.getLanguagepreference());
			throw new Exception(ctx.getMessage(
					"GpUserRegistrationService.error.unknown", null, lc));
		}

		return null;

	}

	/**
	 * Registers a new user to the system - the method only requires that a user
	 * supply an email and password
	 *
	 * @param newuser
	 *            - type: com.npb.gp.domain.core.GpUser
	 * @return com.npb.gp.domain.core.GpUser - with ID populated
	 * @throws Exception
	 */
	public GpUser lightRegisterUser(GpUser newuser, GpBootSecurityUserDao obj) throws Exception {
		GpUser newgpuser=null;

		try {
			System.out
					.println("In  GpUserRegistrationService - lightRegisterUser()");

			Date saltdate = new Date();
			String thesalt = new Long(saltdate.getTime()).toString();// save
																		// this
																		// to
																		// the
																		// DB

			System.out
					.println("In  GpUserRegistrationService - lightRegisterUser()"
							+ " thesalt is: " + thesalt);

			ShaPasswordEncoder enc = new ShaPasswordEncoder(256); // 256 s/b
																	// configurable
			/*String encrypted = enc.encodePassword(newuser.getPassword(),
					thesalt); // save this to DB

			System.out
					.println("In  GpUserRegistrationService - lightRegisterUser()"

							+ " the encrypted password is: " + encrypted);*/

			//newuser.setPassword(newuser.getPassword());
			if(user_Dao == null){
				user_Dao = obj;
			}

			this.user_Dao.insert(newuser);

		} catch (Exception e) {
			logger.error("error occured in GpUserRegistrationService"
					+ " lightRegisterUser method()");
			logger.error("the user id is: " + newuser.getId()
					+ " the error is:");
			e.printStackTrace();
			Locale lc = new Locale(newuser.getLanguagepreference());
			throw new Exception(ctx.getMessage(
					"GpUserRegistrationService.error.unknown", null, lc));
		}

		// save in DB
		return newgpuser;

	}

}
