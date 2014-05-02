package generic_soldier;
import java.io.IOException;
import Scripts.cosmeticSprite;
public class BulletHole extends cosmeticSprite{
	public BulletHole(int x,int y, int height,int width) throws IOException{
		this.assetPath="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\bullethole.jpg";
	
		this.x=x;
		this.y=y;

		this.height=height;
		this.width=width;
		this.defaultImage=get_image(assetPath);
	}
}
