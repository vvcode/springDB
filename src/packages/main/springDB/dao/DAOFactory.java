package springDB.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.JdbcTemplate;

public class DAOFactory {
	private static JdbcTemplate jdbcTemplate;
	public  DAO getBiasingAuditDAO(){
		return new DAOImpl(jdbcTemplate);
	}
	@Required
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	

}
