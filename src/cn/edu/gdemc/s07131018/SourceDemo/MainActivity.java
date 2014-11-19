package cn.edu.gdemc.s07131018.SourceDemo;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv1, tv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);

	}

	public void ReadFile(View view) {
		InputStream is = getResources().openRawResource(R.raw.raw_file);
		try {
			byte [] buffer = new byte[is.available()];
			while(is.read(buffer)!=-1){
				tv1.setText(new String(buffer,"gbk"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ReadXml(View view) {
		XmlPullParser xmlPullParser = getResources().getXml(R.xml.people);
		String msg = "";
		try {
			while (xmlPullParser.next() != xmlPullParser.END_DOCUMENT) {
				String str = xmlPullParser.getName();
				if (str != null && str.equals("person")) {
					int count = xmlPullParser.getAttributeCount();
					for (int i = 0; i < count; i++) {
						String attribute = xmlPullParser.getAttributeName(i);
						if (attribute != null && attribute.equals("name")) {
							msg+="姓名:"+xmlPullParser.getAttributeValue(i);
						} else if (attribute != null && attribute.equals("age")) {
							msg+="年龄:"+xmlPullParser.getAttributeValue(i);
						} else if (attribute != null && attribute.equals("height")) {
							msg+="身高:"+xmlPullParser.getAttributeValue(i);
						}
						msg+="\n";
					}
				}

			}
			tv2.setText(msg);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
