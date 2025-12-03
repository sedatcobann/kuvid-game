package domain;

public class DatabaseAdapterClass implements DatabaseAdapter{

	Controller controller;
	private int saveOpt;
	private int loadOpt;
	DatabaseAdapter adapter;

	public DatabaseAdapterClass(Controller c) {
		this.controller = c;
		this.saveOpt=-1;
		this.loadOpt=-1;	
	}

	public void setSaveOpt(int saveOpt) {
		this.saveOpt = saveOpt;
	}

	public void setLoadOpt(int loadOpt) {
		this.loadOpt = loadOpt;
	}

	@Override
	public boolean save(String username) {
		if(saveOpt == 0) {
			adapter = new LocalDatabase(controller);
		}else if(saveOpt == 1) {
			adapter = new MongoDatabase(controller);
		}else {
			return false;
		}
		adapter.save(username);
		return true;
	}

	@Override
	public boolean load(String username) {
		if(loadOpt == 0) {
			adapter = new LocalDatabase(controller);
		}else if(loadOpt == 1) {
			adapter = new MongoDatabase(controller);
		}else {
			return false;
		}
		adapter.load(username);
		return true;
	}

}
