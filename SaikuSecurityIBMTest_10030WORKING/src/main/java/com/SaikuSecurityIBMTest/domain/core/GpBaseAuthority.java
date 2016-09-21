package com.SaikuSecurityIBMTest.domain.core;

public class GpBaseAuthority  {


	/**
	*
	* For now this class is only used to make the compile succeed
	* and therefore it is not meant to be used - Dan Castillo 01/28/2015
	*
	*/


	public static int  ROLE_ADMIN = 100;
	public static int  ROLE_USER_OWNER = 2;
	public static int  ROLE_USER = 1;


	public int getRoleHierArchyPosition(String role_string) {

		if(role_string.equals("ROLE_ADMIN")) {
			return GpBaseAuthority.ROLE_ADMIN;
		}else if(role_string.equals("ROLE_USER_OWNER")) {
			return GpBaseAuthority.ROLE_USER_OWNER;
		}else if(role_string.equals("ROLE_USER")) {
			return GpBaseAuthority.ROLE_USER;
		} else {
			return 0;
		}
	}


}