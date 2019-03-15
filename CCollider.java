package Shooting20112858;

import java.awt.BasicStroke;
import java.awt.Color;

/********************************
 * 
 * 		충돌 클래스 (게임오브젝트들의 충돌여부를 검사)
 *
 *******************************/

public class CCollider extends CObject
{
	/******************필드**********************/
	private int r;	//충돌구체의 반지름
	private Color color;	//충돌구체의 색깔
	
	/******************메소드*********************/
	public CCollider()
	{
		super();
		this.r = 0;
		this.color = null;
	}
	public CCollider(int x, int y, boolean state, int r, Color color)
	{
		//충돌구체는 외부 이미지로 렌더링하지 않기 때문에 ImgPath에 null을 줌
		//충돌구체의 부모클래스끼리의 tag를 확인하는것이므로 충돌구체의 tag는 true를 줌
		super(true, x, y, state, null);
		this.r = r;
		this.color = color;
	}
	
	//게터 세터
	public int getR() { return this.r; }
	public void setR(int r) { this.r = r; }
	
	//업데이트에서 부모오브젝트의 좌표를 따라감
	public void update(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void drawImg()
	{
		/**************************
		 * 실제로 화면에 출력되지 않는다
		 * drawImg는 충돌구체의 위치를 확인하기 위한 메소드
		 * 배포버전에서는 이 메소드를 사용하지 않는다
		 *************************/
		CGraphics.buffg.setColor(color);
		CGraphics.buffg.fillOval((int)(x - r), (int)(y - r), 2 * r, 2 * r);
		CGraphics.buffg.setColor(color.darker());
		CGraphics.buffg.drawOval((int)(x - r), (int)(y - r), 2 * r, 2 * r);
		for(int i = 0; i < child.size(); i++)
			child.get(i).drawImg();
	}

	public static void checkCollision(CObject obj1, CObject obj2)
	{
		if (obj1.getTag() != obj2.getTag() &&
				!obj1.toString().equals(obj2.toString()) &&
				((obj1.getTag() && !obj2.getTag()) || (!obj1.getTag() && obj2.getTag())) &&
				obj1.getCollider() != null &&
				obj2.getCollider() != null)
			//각각의 태그가 적군, 아군이고 각각의 충돌구체가 null이 아닐때
		{
			System.out.println(obj1.toString()+", "+obj2.toString());
			int x1 = obj1.getCollider().getX();
			int y1 = obj1.getCollider().getY();
			int r1 = obj1.getCollider().getR();
			int x2 = obj2.getCollider().getX();
			int y2 = obj2.getCollider().getY();
			int r2 = obj2.getCollider().getR();
			
			/****************벡터길이 계산*****************/
			double dx = x1 - x2;
			double dy = y1 - y2;
			double dist = Math.sqrt(dx * dx + dy * dy);
			/******************************************/
			
			if(dist < (r1 + r2))	//각각 충돌구체들의 반지름을 합친게 벡터길이보다 크면
			{
				//두 오브젝트의 hit 메소드 호출
				obj1.hit();
				obj2.hit();
			}
			
			//부모자식들도 모두 충돌검사하도록 재귀루프를 돌림
			for(int i = 0; i < obj2.child.size(); i++)
			{
				System.out.println("자식검사");
				checkCollision(obj1, obj2.child.get(i));
			}
			for(int i = 0; i < obj1.child.size(); i++)
			{
				System.out.println("자식검사");
				checkCollision(obj2, obj1.child.get(i));
			}
			/*
			for(int i = 0; i < obj1.child.size(); i++)
			{
				for(int j = 0; j < obj2.child.size(); j++)
				{
					System.out.println("자식검사");
					if(!obj1.child.equals(obj2.child))
						checkCollision(obj1.child.get(i), obj1.child.get(j));
				}
			}*/
		}
	}
}