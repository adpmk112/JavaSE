package javaSE.kmp.testingHibernate.utils;

import javaSE.kmp.testingHibernate.entity.Laptop;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javaSE.kmp.testingHibernate.entity.Person;
public class HibernateUtils { 

	private static SessionFactory sf;

	private static void buildSessionFactory()
	{
		Configuration config = new Configuration().configure().
				addAnnotatedClass(Person.class).addAnnotatedClass(Laptop.class);
		
		ServiceRegistry reg = new StandardServiceRegistryBuilder().
				applySettings(config.getProperties()).build();
		
		sf = config.buildSessionFactory(reg);
	}
	
	public static SessionFactory getSessionFactory() {
		if(sf == null){
			synchronized (HibernateUtils.class){
				if(sf == null) buildSessionFactory();
			}
		}
		return sf;
	}
}
