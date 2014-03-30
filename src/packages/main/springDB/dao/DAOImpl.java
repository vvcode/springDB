package springDB.dao;

import hello.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DAOImpl implements DAO{
	private JdbcTemplate jdbcTemplate;

	public DAOImpl(JdbcTemplate jdbcTemplate ){
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public void printAll() {
        List<Customer> results = jdbcTemplate.query(
                "select * from customers",
                new RowMapper<Customer>() {
                    @Override
                    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Customer(rs.getLong("id"), rs.getString("first_name"),
                                rs.getString("last_name"));
                    }
                });

        for (Customer customer : results) {
            System.out.println(customer);
        }
	}
	@Override
	public void createTable() {
        System.out.println("Creating tables");
        jdbcTemplate.execute("drop table if exists customers ");
        jdbcTemplate.execute("create table customers(" +
                "id serial, first_name varchar(255), last_name varchar(255))");
	}
	@Override
   // @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,rollbackFor=Throwable.class)
	public void insert(String firstName, String lastname) {
		try {
			System.out.println(jdbcTemplate.getDataSource().getConnection().getAutoCommit() + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.printf("Inserting customer record for %s %s\n", firstName, lastname);
        jdbcTemplate.update(
                "INSERT INTO customers(first_name,last_name) values(?,?)",
                firstName, lastname);
//        try {
//        	jdbcTemplate.getDataSource().getConnection().e
//			jdbcTemplate.getDataSource().getConnection().commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        //this.dropTable();
	}
	@Override
	public void query(String firstName) {
		
        System.out.println(String.format("Querying for customer records where first_name = '%s':", firstName));
        List<Customer> results = jdbcTemplate.query(
                "select * from customers where first_name = ?", new Object[] { firstName },
                new RowMapper<Customer>() {
                    @Override
                    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Customer(rs.getLong("id"), rs.getString("first_name"),
                                rs.getString("last_name"));
                    }
                });

        for (Customer customer : results) {
            System.out.println(customer);
        }
		
	}
	@Override
	public void dropTable() {
        System.out.println("Droping tables");
        jdbcTemplate.execute("drop table aa");
	}

}
