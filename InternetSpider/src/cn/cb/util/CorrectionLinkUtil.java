package cn.cb.util;

public class CorrectionLinkUtil {

	public CorrectionLinkUtil(String host) {
		super();
		pickHost(host);
	}

	private String host;
	private String protocal;

	public String correctionLink(String link) {
		String ret = null;
		if(!link.endsWith(".html")){
			if(link.equals(host)){
				ret = link; 
			}else{
				ret =null;
				return ret;
			}
			
		}
		else if (link.contains(protocal)) {
			if(link.contains(host)){
				ret =link;
			}else{
				ret = null;
			}
		}
		else{
			if(link.contains("https")){
				ret =null;
			}else{
				ret=host+link;
	
			}
		}
		
		return ret;
	}

	public void pickHost(String startLink) {
		String returnProtocal=startLink.substring(0,startLink.indexOf("//"));
		int domainStrIndex = startLink
				.indexOf("/", startLink.indexOf("//") + 2);
		if (domainStrIndex == -1) {
			this.host = startLink;
		} else {
			this.host = startLink.substring(0, domainStrIndex);
		}
		protocal=returnProtocal;
	}
}
