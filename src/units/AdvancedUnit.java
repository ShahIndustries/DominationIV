package units;

import importing.ImportManager;

public class AdvancedUnit extends MovableUnit{
	
	public AdvancedUnit(int id, int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
		if(id == 0)
		{
			this.icon = ImportManager.advancedUnit;
		}
		else
		{
			this.icon = ImportManager.advancedUnitWhite;
		}
		this.maxHP = 4;
		this.hp = 4;
		this.attack = 2;
		this.playerOwnerID = id;
	}

}
