package Shooting20112858;

import java.awt.Color;
import java.awt.Dimension;

/********************************
 * 
 * 		플레이어 객체가 쏘는 똥 클래스 (CObject의 상속을 받음)
 *
 *******************************/

public class CPoop extends CObject
{
	/******************필드**********************/
	
	/******************메소드*********************/
	CPoop()
	{
		super();
		col = new CCollider(x, y, true, 20, Color.GREEN);
	}
	CPoop(int x, int y, boolean state, String imgPath)
	{
		super(true, x, y, state, imgPath);
		col = new CCollider(x, y, true, 20, Color.GREEN);
	}
	
	public void update()	//미사일은 오른쪽으로 계속 이동함
	{
		//화면을 벗어나면 상태를 false로 만듦
		if(CGraphics.WIDTH < x)
			setState(false);
		
		//업데이트 될때마다 10만큼 오른쪽으로 이동
		x += 30;
		
		col.update(x + 30, y + 30);
	}
	
	public void hit()
	{
		this.setState(false);
		System.out.println(this.getClass() + " 충돌함");
	}
}