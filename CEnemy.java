package Shooting20112858;

import java.awt.Color;

/********************************
 * 
 * 		적 말 클래스 (CObject의 상속을 받음)
 *
 *******************************/

public class CEnemy extends CObject
{
	/******************필드**********************/
	private long firingTimer;	//사격 타이머
	private long firingDelay;	//연사속도
	/******************메소드*********************/
	public CEnemy()
	{
		super();
		col = new CCollider(x, y, true, 55, Color.BLUE);
		firingTimer = System.nanoTime();
		firingDelay = 3000;
		tag = false;
	}
	public CEnemy(int x, int y, boolean state)
	{
		super(false, x, y, state, "src\\Shooting20112858\\resource\\enemy_ass.png");
		col = new CCollider(x, y, true, 50, Color.BLUE);
		firingTimer = System.nanoTime();
		firingDelay = 3000;
	}
	
	public void update()
	{
		//자식객체 (똥)의 업데이트를 실행함
		for(int i = 0; i < child.size(); i++)
		{
			child.get(i).update();
			if(!child.get(i).getState())//자식의 상태가 false면 자식 삭제
				child.remove(i);
		}
				
		//업데이트 될때마다 왼쪽으로 3만큼 이동
		x -= 1;
		
		long elapsed = (System.nanoTime() - firingTimer) / 1000000;
		if(elapsed > firingDelay)
		{
			//자식객체에 새로운 똥객체를 생성한다
			child.add(new CEnemyPoop(x + 30, y + 30, true,
					"src\\Shooting20112858\\resource\\enemy_poop.png"));
			//System.out.println("똥을쌈");
			firingTimer = System.nanoTime();
		}
		
		col.update(x + 85, y + 70);
	}
	
	public void hit()
	{
		CSound.play("src\\Shooting20112858\\resource\\hit.wav", false);
		System.out.println(this.getClass() + " 충돌함");
		CFramework.score += 10;
		setState(false);
	}
}
