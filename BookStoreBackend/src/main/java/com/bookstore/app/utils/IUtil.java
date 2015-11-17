package com.bookstore.app.utils;

/**
 * This interface define the path to several configuration files.
 * 
 * @author <a href="mailto:cdinu@pentalog.fr">Claudiu Dinu</a>
 * 
 */
public interface IUtil {

	/**
	 * This variable verify if <code>HibernateUtil</code> class need to load
	 * configuration file in scope of testing or in scope of production.
	 */
	public static String TEST_SCOPE = "test_scope";

	/**
	 * This is the constant that represent the path to the hibernate
	 * configuration file.
	 */
	public static final String HIBERNATE_CONFIG_FILE_PATH = "/config/database/hibernate.cfg.xml";

	/**
	 * This is the constant that represent the path to the hibernate test
	 * configuration file.
	 */
	public static final String HIBERNATE_CONFIG_TEST_FILE_PATH = "/config.database/.hibernate.cfg.test.xml";

}
