package units;

import importing.ImportManager;

public class BaseUnit extends Unit{
	
	public BaseUnit(int id, int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
		if(id == 0)
		{
			this.icon = ImportManager.base;
		}
		else
		{
			this.icon = ImportManager.baseWhite;
		}
		this.maxHP = 10;
		this.hp = 10;
		this.playerOwnerID = id;
	}
	
}
