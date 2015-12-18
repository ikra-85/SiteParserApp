package app.java.main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageGrabber extends JScrollPane{
	private static final long serialVersionUID = 4053213877055923700L;
	Elements images;
	Document doc;
	JPanel panel;
	
	public ImageGrabber(Document doc) {
		this.doc = doc;
		images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		panel = new JPanel(new GridLayout(images.size(), 1));
		grabImages();
		setViewportView(panel);
		setPreferredSize(new Dimension(400, 400));
	}

	private void grabImages() {

		for (Element image : images) {
			String l = image.attr("src");
			if (l.length()>0) {
				if (l.length()<4) l = doc.baseUri()+l.substring(1);
				else if (!l.substring(0, 4).equals("http"))
					 l = doc.baseUri()+l.substring(1);
				JLabel label = new JLabel(l);
				
				panel.add(label);
				
			}
		}
	}
}
