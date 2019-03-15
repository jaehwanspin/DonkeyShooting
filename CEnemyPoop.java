package Shooting20112858;

import java.awt.Color;
import java.awt.Dimension;

/********************************
 * 
 * 		플레이어 객체가 쏘는 똥 클래스 (CObject의 상속을 받음)
 *
 *******************************/

public class CEnemyPoop extends CObject
{
	/******************필드**********************/
	
	/******************메소드*********************/
	CEnemyPoop()
	{
		super();
		col = new CCollider(x, y, true, 20, Color.GREEN);
	}
	CEnemyPoop(int x, int y, boolean state, String imgPath)
	{
		super(false, x, y, state, imgPath);
		col = new CCollider(x, y, true, 20, Color.GREEN);
		this.tag = false;
	}
	
	public void update()	//미사일은 오른쪽으로 계속 이동함
	{
		//화면을 벗어나면 상태를 false로 만듦
		if(0 > x)
			setState(false);
		
		//업데이트 될때마다 5만큼 왼쪽으로 이동
		x -= 5;
		
		col.update(x + 30, y + 30);
	}
	
	public void hit()
	{
		CSound.play("src\\Shooting20112858\\resource\\hit.wav", false);
		System.out.println(this.getClass() + " 충돌함");
		this.state = false;
	}
}