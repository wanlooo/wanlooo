package com.cn.xxx.web.junitTest;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class JunitContext {

	private SessionFactory sessionFactory;
	private Session session;
	protected ClassPathXmlApplicationContext ctx;
	
	@Before
	public void openSession() throws Exception {

		ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext-main.xml"});
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		session = sessionFactory.openSession();
		session.setFlushMode(FlushMode.MANUAL);
		TransactionSynchronizationManager.bindResource(sessionFactory,
				new SessionHolder(session));
	}

	@After
	public void closeSession() throws Exception {
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		session.close();
		sessionFactory.close();

	}
	
	
}
