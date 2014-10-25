package awap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class OurBoard {
	private List<BlockPlacement> boardBlockPlacements;
	private List<Point> corners;
	private List<Block> availableBlocks;
	private int score;

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
		ArrayList<Point> orderedPoints = new ArrayList<Point>();
		ArrayList<Integer> orderedDist = new ArrayList<Integer>();
		for(Point corner : corners) {
			int dist = Math.abs((goal.getX() - corner.getX()) + (goal.getY() - goal.getY()));
//			distMap.put(corner, dist);
			if(orderedDist.isEmpty()) {
				orderedDist.add(dist);
				orderedPoints.add(corner);
			}
			else {
				for(int i = orderedDist.size()-1; i >= 0; i--) {
					if(orderedDist.get(i) > dist) {
						orderedDist.add(i, dist);
						orderedPoints.add(i, corner);
					}
				}
			}	
		}
		return orderedPoints;
	}
	
	
	/**
	 * Order available blocks based on largest Manhattan distance
	 * @return
	 */
	public ArrayList<Block> orderBlocksForPath() {
		for(Block block : availableBlocks) {
			
		}
		return null;
	}
	
	public int getScore() {
		return score;
	}
}
