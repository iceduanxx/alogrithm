package cn.cb.internet;

import java.util.ArrayList;
import java.util.List;

public class HtmlNode {
	private boolean visited;
	private String link;
	private List<HtmlNode> adjoinList = new ArrayList<HtmlNode>();
	private List<ImgLink> imgLinkList = new ArrayList<ImgLink>();
	private int level;
	private int tryCount;
	private String title;

	private boolean hasDownLoad;

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {

		this.link = link;

	}

	public void addHtmlNodeList(HtmlNode node) {
		adjoinList.add(node);
	}

	public void addImgLinkList(ImgLink imgLink) {

		imgLinkList.add(imgLink);
	}
	
	

	public List<ImgLink> getImgLinkList() {
		return imgLinkList;
	}

	public void setImgLinkList(List<ImgLink> imgLinkList) {
		this.imgLinkList = imgLinkList;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HtmlNode other = (HtmlNode) obj;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		return true;
	}

	public List<HtmlNode> getAdjoinList() {
		return adjoinList;
	}

	public int getTryCount() {
		return tryCount;
	}

	public void setTryCount(int tryCount) {
		this.tryCount = tryCount;
	}

}
