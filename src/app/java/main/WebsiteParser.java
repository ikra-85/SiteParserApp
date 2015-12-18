package app.java.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteParser extends JTabbedPane{
	private static final long serialVersionUID = 5071683955638085156L;
	Document doc;
	JScrollPane spane;
	public WebsiteParser() {
		try {
			doc = Jsoup.connect("http://google.com").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		getLinks();
		addTab("links", spane);
		addTab("Imges", new ImageGrabber(doc));
	}
	
	private void getLinks() {
		Elements links = doc.getElementsByTag("a");
		JPanel linkPanel = new JPanel();
		linkPanel.setLayout(new GridLayout(links.size(), 1));
		for (Element link : links) {
			String l = link.attr("href");
			if (l.length()>0) {
				if (l.length()<4) l = doc.baseUri()+l.substring(1);
				else if (!l.substring(0, 4).equals("http"))
					 l = doc.baseUri()+l.substring(1);
				JLabel label = new JLabel(l);
				
				linkPanel.add(label);
				
			}
		}
		spane = new JScrollPane(linkPanel);
		spane.setPreferredSize(new Dimension(350, 350));
	}
	
public static void main(String[] args) {
	JFrame frame = new JFrame("WebParser");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	WebsiteParser wp = new WebsiteParser();
	frame.add(wp);
	frame.setVisible(true);
	frame.setSize(400, 400);
}
}
