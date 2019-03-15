package Shooting20112858;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*************************************
 * 
 * 			키보드 입력 클래스
 *
 ************************************/

public class CInput implements KeyListener
{
	/******************필드**********************/
	//키검사 전역필드
	private static boolean KeyUp = false;
	private static boolean KeyDown = false;
	private static boolean KeyLeft = false;
	private static boolean KeyRight = false;
	private static boolean KeySpace = false;
	
	/******************메소드*********************/
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	//키가 눌렸을때
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP :
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN :
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT :
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT :
			KeyRight = true;
			break;
		case KeyEvent.VK_SPACE :
			KeySpace = true;
			break;
		}
		
	}
	
	//키가 떼졌을때
	@Override
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP :
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN :
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT :
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT :
			KeyRight = false;
			break;
		case KeyEvent.VK_SPACE :
			KeySpace = false;
			break;
		}
	}
	
	//키가 눌렸는지 검사하는 전역메소드 (각각 화살표 방향키와 스페이스바가 있다)
	public static boolean getKeyUp()
	{
		return KeyUp;
	}
	public static boolean getKeyDown()
	{
		return KeyDown;
	}
	public static boolean getKeyLeft()
	{
		return KeyLeft;
	}
	public static boolean getKeyRight()
	{
		return KeyRight;
	}
	public static boolean getKeySpace()
	{
		return KeySpace;
	}
}