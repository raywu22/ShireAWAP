package awap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OurBoard {
	Game originalState; // this is the state that the server has told us is the current state (before we start a search)
	
	private ArrayList<BlockPlacement> boardBlockPlacements;
	private List<Point> corners;
	private List<Block> availableBlocks;
	
	private int score;

	public OurBoard(Game game) {
		this.originalState = game;
		boardBlockPlacements = new ArrayList<>();
		corners = new ArrayList<>();
		availableBlocks = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param corner - gives list of already available corners
	 * @param blocks - list of blocks already used in our board
	 * @param filledPoints - ALL filled points on the board in the for List(isOurPoint,xCoord,yCoord) where
	 *                       isOurPoint = 0 if it is our point and isOurPoint = 1 if other point
	 */
	public OurBoard(List<Point> corner, ArrayList<BlockPlacement> blockPlacements) {
	    //TODO set this.corners to new available corners
		this.corners = corner;
		this.boardBlockPlacements = blockPlacements;
	}
	
	/**
	 * producer method
	 * @param b - block to be placed, ASSUME that it is always able to be added
	 * @param p - point where the block is placed
	 * @return OurBoard
	 */
	public OurBoard addBlock(Block blockToAdd, Point pointAddingTo) {
	    
	}
	
	/**
	 * 
	 * @return - New list of places where a block can be placed
	 */
	public List<Point> getCorners() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Point> getPoints() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<BlockPlacement> getBlocksUsed() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Block> getBlocksAvailable() {
		return null;
	}
	
	/**
	 * 
	 * @param goal - goal point
	 * @return
	 */
	public ArrayList<Point> orderCornersForPath(Point goal) {
		return null;
	}
	
	/**
	 * Order available blocks based on largest Manhattan distance
	 * @return
	 */
	public ArrayList<Point> orderBlocksForPath() {
		
		return null;
	}
	
	public boolean checkForConflicts(BlockPlacement placement) {
		boolean canPlace = originalState.canPlace(placement.getBlock().rotate(placement.getRotation()), placement.getLocation());
		if(!canPlace)
			return false;
		int x = placement.getLocation().getX();
		int y = placement.getLocation().getY();
		Block rotated = placement.getRotatedBlock();
		for(BlockPlacement otherBlocks : boardBlockPlacements) {
			int ox = otherBlocks.getLocation().getX();
			int oy = otherBlocks.getLocation().getY();
			Block otherRotated = otherBlocks.getRotatedBlock();
			for(Point offset : rotated.getOffsets()) {
				for(Point otherOffset : otherRotated.getOffsets()) {
					if((offset.getX() + x == otherOffset.getX() + ox) && (offset.getY() + y == otherOffset.getY() + oy)) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public int getScore() {
		return score;
	}
}
