package com.example.hello;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameOver {
	
	/**
	 * ��Ϸ����
	 * 
	 * @param g
	 */
	public static void show(Canvas canvas) {
		Paint paint = new Paint();
		paint.setTextSize(scalaFonts(34));
		paint.setColor(Color.WHITE);
		canvas.drawText("GAME��OVER!", 150, 400, paint);

		}

	/**
	 * ������Ļϵ��������ȡ���ִ�С
	 * 
	 * @return
	 */
	private static float scalaFonts(int size) {
		// ��δʵ��
		return size;
	}
	///**
	//* ���ر���ͼƬ
	//* http://bbs.3gstdy.com
	//* @param url
	//* @return
	//*/
	public static Bitmap getLoacalBitmap(String url) {
	    try {
	         FileInputStream fis = new FileInputStream(url);
	         return BitmapFactory.decodeStream(fis);
	    } catch (FileNotFoundException e) {
	         e.printStackTrace();
	         return null;
	    }
	}
}

//public void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.myimage);
//    ImageView image1 = (ImageView) findViewById(R.myImage.image);
//    //Bitmap bitmap = getLoacalBitmap("/aa/aa.jpg"); //�ӱ���ȡͼƬ
//    Bitmap bitmap =
//getHttpBitmap("http://blog.3gstdy.com/wp-content/themes/twentyten/images/headers/path.jpg");
////������ȡͼƬ
//    image1 .setImageBitmap(bitmap);	//����Bitmap
//}

///**
//* �ӷ�����ȡͼƬ
//*http://bbs.3gstdy.com
//* @param url
//* @return
//*/
//public static Bitmap getHttpBitmap(String url) {
//    URL myFileUrl = null;
//    Bitmap bitmap = null;
//    try {
//         Log.d(TAG, url);
//         myFileUrl = new URL(url);
//    } catch (MalformedURLException e) {
//         e.printStackTrace();
//    }
//    try {
//         HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
//         conn.setConnectTimeout(0);
//         conn.setDoInput(true);
//         conn.connect();
//         InputStream is = conn.getInputStream();
//         bitmap = BitmapFactory.decodeStream(is);
//         is.close();
//    } catch (IOException e) {
//         e.printStackTrace();
//    }
//    return bitmap;
//}
