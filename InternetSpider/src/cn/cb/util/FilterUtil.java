package cn.cb.util;

import java.util.HashSet;
import java.util.Set;

public class FilterUtil {
	public Set<String> filterTitle = new HashSet<String>();
	
	public FilterUtil(){
		filterTitle.add("小说");
		filterTitle.add("电影");
		filterTitle.add("播");
	}
	
	public String filterTitle(String title){
		String ret =title;
		for(String filter:filterTitle){
			if(title.contains(filter)){
				return ret="reject";
			}
		}
		if(title.indexOf("?")!=-1){
			return ret =title.replaceAll("\\?", "");
		}
		return ret;
	}
}
