package awap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OurBoard {
	private List<BlockPlacement> boardBlockPlacements;
	private List<Point> corners;
	private List<Block> availableBlocks;
	/**
	 * 
	 * @param corner - gives list of already available corners
	 * @param blocks - list of blocks already used in our board
	 * @param filledPoints - ALL filled points on the board in the for List(isOurPoint,xCoord,yCoord) where
	 *                       isOurPoint = 0 if it is our point and isOurPoint = 1 if other point
	 */
	public OurBoard(List<Point> corner, List<BlockPlacement> blockPlacements) {
	    //TODO set this.corners to new available corners
		this.corners = corner;
		this.boardBlockPlacements = blockPlacements;
//		availableBlocks = totalDomain - blocks;
	}
	
	/**
	 * producer method
	 * @param b - block to be placed, ASSUME that it is always able to be added
	 * @param p - point where the block is placed
	 * @return OurBoard
	 */
	public OurBoard addBlock(Block blockToAdd, Point pointAddingTo,int rotation) {
	    BlockPlacement toAddBlockPlacement = new BlockPlacement(blockToAdd,pointAddingTo,rotation);
	    List<BlockPlacement> copyOfBlockPlacements = new ArrayList<>(this.getBlockPlacements());
	    copyOfBlockPlacements.add(toAddBlockPlacement);
	    return new OurBoard(this.getCorners(),copyOfBlockPlacements);
	}
	
	public List<BlockPlacement> getBlockPlacements(){
	    return this.boardBlockPlacements;
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
	public List<Block> getBlocksUsed() {
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
}
