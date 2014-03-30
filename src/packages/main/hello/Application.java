package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import springDB.dao.DAOFactory;

public class Application {
	private DAOFactory daoFactory ;
    public DAOFactory getDaoFactory() {
		return daoFactory;
	}
    @Transactional(rollbackFor=Exception.class)
    public void transaction(){
    	daoFactory.getBiasingAuditDAO().insert("cc12", "bb");
    	daoFactory.getBiasingAuditDAO().dropTable();
    }


	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/config.xml");
		Application app = context.getBean("application", Application.class);
		try{
		app.transaction();
		}catch(Exception e){
			
		}

    }
	

}