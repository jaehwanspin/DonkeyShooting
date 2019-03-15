package Shooting20112858;

import java.util.Random;

/********************************
 * 
 * 		적 말 부대 클래스 (적 트룹을 일정시간마다 y축 랜덤으로 생성해줌)
 * 		(CObject의 상속을 받음)
 *
 *******************************/

public class CEnemyTroop extends CObject
{
	/******************필드**********************/
	private long createTimer;	//적 생성 타이머
	private long createDelay;	//적 생성 속도
	
	/******************메소드*********************/
	public CEnemyTroop()
	{
		super(false, CGraphics.WIDTH, CGraphics.HEIGHT / 2, true, null);
		createTimer = System.nanoTime();
		createDelay = 400;	//2초에 한마리씩 생성함
		this.col = null;
	}
	public CEnemyTroop(int x, int y, boolean state)
	{
		//적군 생성기 용도로 사용하므로 tag를 true상태로 초기화함
		super(true, x, y, state, null);
		createTimer = System.nanoTime();
		createDelay = 400;
		this.col = null;
	}
	
	public void update()
	{
		for(int i = 0; i < child.size(); i++)
		{
			child.get(i).update();
			if(!child.get(i).getState() || child.get(i).getY() + 120 > CGraphics.HEIGHT
					|| child.get(i).getY() - 20 < 0)
				child.remove(i);
		}
		
		long elapsed = (System.nanoTime() - createTimer) / 1000000;
		if(elapsed > createDelay)
		{
			//일정시간마다 새로운 적 객체를 Y축 랜덤으로 생성한다
			
			child.add(new CEnemy(CGraphics.WIDTH,
					(int)(Math.random() * CGraphics.HEIGHT), true));
			
			createTimer = System.nanoTime();
		}
	}
}