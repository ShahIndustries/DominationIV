package units;

import importing.ImportManager;

public class MainUnit extends MovableUnit{
	
	public MainUnit(int id, int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
		if(id == 0)
		{
			this.icon = ImportManager.mainUnit;
		}
		else
		{
			this.icon = ImportManager.mainUnitWhite;
		}
		this.maxHP = 2;
		this.hp = 2;
		this.attack = 1;
		this.playerOwnerID = id;
	}

}
