package Shooting20112858;

import java.awt.Color;
import java.util.ArrayList;

/********************************
 * 
 * 		직접 조종하는 Player 클래스 (CObject의 상속을 받음)
 *
 *******************************/

public class CPlayer extends CObject
{
	/******************필드**********************/
	private long firingTimer;	//사격 타이머
	private long firingDelay;	//연사속도
	
	/******************메소드*********************/
	CPlayer()
	{
		super();
		firingTimer = System.nanoTime();
		firingDelay = 200;
		col = new CCollider(x, y, true, 40, Color.black);
	}
	CPlayer(int x, int y, boolean state, String imgPath)
	{
		super(true, x, y, state, imgPath);
		firingTimer = System.nanoTime();
		firingDelay = 400;
		col = new CCollider(x, y, true, 40, Color.black);
	}
	
	public void update()	//업데이트 메소드에서 키를 입력받아 위치를 움직이고 똥을 쏘게됨
	{	
		for(int i = 0; i < child.size(); i++)
		{
			child.get(i).update();
			
			if(!child.get(i).getState()) child.remove(i);	//똥이 화면을 벗어나면 똥 객체를 삭제함
			//System.out.println("똥의개수 : " + child.size());
		}
		for(int i = 0; i < child.size(); i++)
		{
			if(!child.get(i).getState() || child.get(i).getX() > CGraphics.WIDTH)
				child.get(i).setState(false);
		}
		
		//플레이어가 화면 밖으로 벗어나지 못하도록 가둠
		if(x <= 0) x = 0;
		if(x >= CGraphics.WIDTH - 100) x = CGraphics.WIDTH - 100;
		if(y <= 0) y = 0;
		if(y >= CGraphics.HEIGHT - 90) y = CGraphics.HEIGHT - 90;
		
		if(CInput.getKeyUp()) y -= 10;
		if(CInput.getKeyDown()) y += 10;
		if(CInput.getKeyLeft()) x -= 10;
		if(CInput.getKeyRight()) x += 10;
		//스페이스바를 누르면 자식객체가 생성된다 (똥이 생성됨)
		if(CInput.getKeySpace())
		{
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if(elapsed > firingDelay)
			{
				//자식객체에 새로운 똥객체를 생성한다
				child.add(new CPoop(x + 80, y + 30, true,
						"src\\Shooting20112858\\resource\\shit.png"));
				//배변 사운드 재생
				CSound.play("src\\Shooting20112858\\resource\\poop.wav", false);
				
				firingTimer = System.nanoTime();
			}
		}
		
		col.update(x + 80, y + 60);
	}
	
	public void hit()
	{
		CFramework.WoL = 2;
		//피격 사운드를 재생시킴
		CSound.play("src\\Shooting20112858\\resource\\hit.wav", false);
		setState(false);
	}
}