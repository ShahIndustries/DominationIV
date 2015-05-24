package units;

import importing.ImportManager;

public class HowitzerUnit extends MovableUnit{
	
	public HowitzerUnit(int id, int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
		if(id == 0)
		{
			this.icon = ImportManager.howitzer;
		}
		else
		{
			this.icon = ImportManager.howitzerWhite;
		}
		this.maxHP = 1;
		this.hp = 1;
		this.attack = 1;
		this.playerOwnerID = id;
	}

}
