package springDB.dao;


public interface DAO {
	void printAll();
	void createTable();
	void dropTable();
	void insert(String firstName, String lastname);
	void query(String firstName);
	

}
