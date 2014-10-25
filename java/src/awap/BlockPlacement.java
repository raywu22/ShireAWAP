package awap;

public class BlockPlacement {
	Block block;
	Point location;
	int rotation;
	
	public BlockPlacement(Block block, Point location, int rotation) {
		this.block = block;
		this.location = location;
		this.rotation = rotation;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public Block getRotatedBlock() {
		return block.rotate(rotation);
	}
}
