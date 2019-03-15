package Shooting20112858;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

/********************************
 * 
 * 		GUI 그래픽 클래스
 *
 *******************************/

public class CGraphics
{
	/******************필드**********************/
	//창의 크기를 설정한 전역상수 1280 x 960의 크기
	public final static int WIDTH = 1280; 
	public final static int HEIGHT = 600;
	
	//더블버퍼링을 위한 전역필드
	public static Image buffImage;
	public static Graphics buffg;
		
	//이미지 생성을 위한 툴킷
	public static Toolkit tk = Toolkit.getDefaultToolkit();
	
	/******************메소드*********************/
	public static void drawImage(Image img, int x, int y) //이미지를 그리는 메소드
	{
		buffg.drawImage(img, x, y, null);
	}
	
	public static void drawText(String text, int x, int y, int size)	//텍스트를 해당 좌표에 그리는 메소드
	{
		buffg.setColor(Color.WHITE);
		buffg.setFont(new Font("Defualt", Font.BOLD, size));
		// 폰트설정을합니다. 기본폰트, 굵게, 사이즈20
		buffg.drawString(text, x, y);
	}
}
