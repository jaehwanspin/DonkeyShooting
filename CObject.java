package Shooting20112858;

import java.awt.Image;
import java.util.ArrayList;

/********************************
 * 
 * 		게임오브젝트 부모 클래스
 *
 *******************************/

public class CObject
{
	/******************필드**********************/
	//오브젝트의 태그, 아군이면 true, 적군이면 false
	protected boolean tag;
	//오브젝트의 좌표
	protected int x, y;
	//오브젝트의 불리언 상태
	protected boolean state;
	//오브젝트의 이미지
	protected Image img;
	//자식 오브젝트
	protected ArrayList<CObject> child;
	//충돌구체
	protected CCollider col;
	/******************메소드*********************/
	public CObject()
	{
		this(true, CGraphics.WIDTH / 2, CGraphics.HEIGHT / 2, false, "");
		col = null;
	}
	public CObject(boolean tag, int x, int y, boolean state, String imgPath)
	{
		this.tag = tag;
		this.x = x;
		this.y = y;
		this.state = state;
		img = CGraphics.tk.getImage(imgPath);	//파일경로에서 이미지를 가져옴 
		child = new ArrayList<CObject>();
	}
	
	//세터 게터
	public void setX(int x) { this.x = x; }
	public int getX() { return this.x; }
	public void setY(int y) { this.y = y; }
	public int getY() { return this.y; }
	public void setTag(boolean tag) { this.tag = tag; }
	public boolean getTag() { return this.tag; }
	public void setState(boolean state) { this.state = false; }
	public boolean getState() { return this.state; }
	public void setCollider(CCollider col) { this.col = col; }
	public CCollider getCollider() { return this.col; }
	//업데이트 메소드
	public void update()
	{
		//자식객체의 업데이트 메소드도 전부 돌려줌
		for(int i = 0; i < child.size(); i++)
			child.get(i).update();
		
		if(col != null)
			//충돌구체가 null이 아니면 충돌구체를 업데이트해서 자신의 좌표를 따라가게 함
			col.update(x, y);
	}
	
	//이미지파일을 그려주는 메소드
	public void drawImg()
	{
		CGraphics.drawImage(img, x, y);
		//자식객체의 drawImg()메소드까지 전부 돌려줌
		for(int i = 0; i < child.size(); i++)
			child.get(i).drawImg();
		//충돌구체가 널이 아니면 충돌구체를 화면에 그림
		/*if(col != null)
			col.drawImg();*/
	}
	
	//충돌시 이벤트
	public void hit()
	{
		System.out.println(this.getClass() + " 충돌함");
	}
}