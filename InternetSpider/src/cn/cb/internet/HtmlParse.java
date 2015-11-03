package cn.cb.internet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

import cn.cb.util.CorrectionLinkUtil;
import cn.cb.util.FilterUtil;
import cn.cb.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author icecookstar
 * 
 */
public class HtmlParse {

	public static Queue<HtmlNode> htQueue = new LinkedList<HtmlNode>();
	public static Set<HtmlNode> hashSet = new HashSet<HtmlNode>();
	static String host = "http://www.0022ku.com/list/index6_9.html";
	private static Log log = LogFactory.getLog(HtmlParse.class);
	private static Set<String> imgLinkSet = new HashSet<String>(); 
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		htmlParse();

	}

	public static void htmlParse() {
		List<String> list = new ArrayList<String>();
		MD5 md5 = new MD5();
		FilterUtil filterUtil = new FilterUtil();
		try {
			CorrectionLinkUtil correctionLinkUtil = new CorrectionLinkUtil(host);
			HtmlNode htmlNode = new HtmlNode();
			htmlNode.setLevel(1);
			htmlNode.setLink(/*correctionLinkUtil.correctionLink(host)*/ host);
			htQueue.offer(htmlNode);
			hashSet.add(htmlNode);
			while (!htQueue.isEmpty()) {
				log.info("q:"+htQueue.size());
				log.info("h:"+hashSet.size());
				if(imgLinkSet.size()>30){
					log.info("i:"+imgLinkSet.size());
					Thread.sleep(imgLinkSet.size()*1000);
					imgLinkSet.clear();
				}

				HtmlNode hNode = htQueue.poll();
				hNode.setTryCount(hNode.getTryCount() + 1);
				log.info(hNode.getLink() + "灏濊瘯鐨勬鏁� + hNode.getTryCount());
				if (hNode.getLevel() == 20) {
					htQueue.clear();
				}
				if (!hNode.isVisited()) {
					hNode.setVisited(true);
					String html = null;
					Document doc = null;
					try {
						html = ApiInvoke.get(hNode.getLink());
						doc = Jsoup.parse(html);
					} catch (Exception ex) {
						if (hNode.getTryCount() <= 5) {
							hNode.setVisited(false);
							htQueue.offer(hNode);
						} else {
							log.info(hNode.getLink() + "宸插皾璇�
									+ hNode.getTryCount() + "娆★紝涓嶅啀鍔犲叆闃熷垪");

						}

						continue;
					}
					Element titleElement = doc.select("title").get(0);
					String title = titleElement.text();
					if (StringUtils.isNotEmpty(title)) {
						String returnTitle = filterUtil.filterTitle(title);
						if (returnTitle == "reject") {
							log.info("鏍囬涓惈鏈夊皬璇达紝鐢靛奖绛夊瓧鏍凤紝鎷掔粷鍔犲叆闃熷垪" + title);
						}
						hNode.setTitle(returnTitle);
					} else {
						hNode.setTitle(md5.getMD5ofStr(UUID.randomUUID()
								.toString()));
					}

					Elements tagA = doc.select("a");
					List<HtmlNode> htmlNodelList = hNode.getAdjoinList();
					List<ImgLink> imgLinkList = hNode.getImgLinkList();

					for (Element element : tagA) {
						String returnLink =/* correctionLinkUtil
								.correctionLink(element.attr("href"))*/ element.attr("href");
						if (returnLink == null) {
							log.info("鍜屼富绔欑偣涓嶆槸鍚屼竴鍩熷悕鎷掔粷鍔犲叆" + element.attr("href"));
							continue;
						}
						HtmlNode htmlNodePer = new HtmlNode();
						htmlNodePer.setLink(returnLink);
						htmlNodePer.setLevel(hNode.getLevel() + 1);
						htmlNodelList.add(htmlNodePer);
						if (hashSet.add(htmlNodePer)) {
							if (StringUtils.isNotEmpty(hNode.getTitle())
									&& !(hNode.getTitle() == "reject")) {
								htQueue.offer(htmlNodePer);
							}

						}
					}

					if (hNode.getTitle() == "reject") {
						continue;
					}

					Elements elements = doc.select("img");
					int i = 1;

					for (Element element : elements) {

						String returnImgLink = element.attr("src");
						if (StringUtils.isEmpty(returnImgLink)) {
							continue;
						}
						log.info("鍙戠幇涓�釜鍥剧墖鏂囦欢" + returnImgLink);
						ImgLink imgLink = new ImgLink();
						imgLink.setLink(returnImgLink);
						imgLink.setId(i);
						AbstractWork work = new AbstractWork(imgLink, hNode);
						WorksMonitor.executorService.execute(work);
						imgLinkList.add(imgLink);
						imgLinkSet.add(imgLink.getLink());
						i++;
						
					}
				}

			}

			for (HtmlNode hNode : hashSet) {
				for (ImgLink imgLink : hNode.getImgLinkList()) {
					if (imgLink.isHasDownLoad()) {
						if (StringUtils.isNotEmpty(imgLink.getLink())) {
							AbstractWork work = new AbstractWork(imgLink, hNode);
							WorksMonitor.executorService.execute(work);
							WorksMonitor.executorService.execute(work);
						}
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
