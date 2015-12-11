package com.bookstore.app.utils;

import com.bookstore.app.data.entites.Book;
import com.bookstore.app.data.entites.BookCategory;
import com.bookstore.app.data.entites.User;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This class will create a database connection through Hibernate. The class
 * will instantiate two objects: - SessionFactory - AnnotationConfiguration
 * 
 * @author Claudiu Dinu
 * 
 */
public final class HibernateUtil implements IUtil {

	/**
	 * This static variable is used to create a session that allows database
	 * connection.
	 */
	private static SessionFactory sessionFactory;

	/**
	 * The logging object.
	 */
	private static Logger logger = Logger.getLogger(HibernateUtil.class);

	/**
	 * This static variable is used to add the annotated classes and configure
	 * Hibernate.
	 */
	private static Configuration config;

	/**
	 * Hibernate start session massage.
	 */
	public static final String SESSION_START = "Hibernate session has started.";

	/**
	 * Hibernate stop session massage.
	 */
	public static final String SESSION_STOP = "Hibernate session has closed.";

	/**
	 * Hibernate start transaction massage.
	 */
	public static final String TRANSACTION_START = "Transaction has started.";

	/**
	 * Hibernate transaction successfully commited massage.
	 */
	public static final String TRANSACTION_COMMITED_SUCCESSFULLY = "Transaction"
			+ " succesfully commited.";

	/**
	 * Hibernate transaction roll back massage.
	 */
	public static final String TRANSACTION_ROLLBACK = "Rollback has performed.";

	/**
	 * an empty private constructor.
	 */
	private HibernateUtil() {
		// No implementation needed
	}

	/**
	 * Static initialization block. This block will add the annotated classes
	 * and create a database connection with Hibernate.
	 */
	static {
		try {
			config = new Configuration();
			config.addAnnotatedClass(User.class);
			config.addAnnotatedClass(Book.class);
			config.addAnnotatedClass(BookCategory.class);
			config.configure(IUtil.HIBERNATE_CONFIG_FILE_PATH);

			sessionFactory = config.buildSessionFactory();

		} catch (Exception ex) {
			logger.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * This method will return the SessionFactory object.
	 * 
	 * @return the SessionFactory object.
	 */
	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}
}
