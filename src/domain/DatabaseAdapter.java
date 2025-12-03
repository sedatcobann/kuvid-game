package domain;

public interface DatabaseAdapter {
	
	
	public boolean save(String username);
	public boolean load(String username);
}
