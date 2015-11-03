package cn.cb.internet;

public class ImgLink {

	private String link;
	private boolean hasDownLoad;
	private int id;
	private int tryCount;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String title;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isHasDownLoad() {
		return hasDownLoad;
	}

	public void setHasDownLoad(boolean hasDownLoad) {
		this.hasDownLoad = hasDownLoad;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTryCount() {
		return tryCount;
	}

	public void setTryCount(int tryCount) {
		this.tryCount = tryCount;
	}
	
	

}
