package awap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class OurBoard {
	private ArrayList<BlockPlacement> boardBlockPlacements;
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
		ArrayList<Block>   orderedBlocks = new ArrayList<Block>();
		ArrayList<Integer> manhattanDist = new ArrayList<Integer>();
		for(Block block : availableBlocks) {
			List<Point> blockPoints = block.getOffsets();
			List<Integer> xs = new ArrayList<Integer>();
			List<Integer> ys = new ArrayList<Integer>();
			int max = 0;
			for (Point p1 : blockPoints) {
				for(Point p2: blockPoints) {
					if(max < Math.abs(p1.getX() - p2.getX()) +
							 Math.abs(p1.getY() - p2.getY())) {
						max = Math.abs(p1.getX() - p2.getX()) +
								 Math.abs(p1.getY() - p2.getY());
					}
				}
			}
			if(manhattanDist.isEmpty()) {
				manhattanDist.add(max);
				orderedBlocks.add(block);
			}
			else {
				for(int i = 0; i < manhattanDist.size(); i++) {
					if(manhattanDist.get(i) < max) {
						manhattanDist.add(i, max);
						orderedBlocks.add(i, block);
					}
				}
			}	
		}
		return orderedBlocks;
	}
	
	public int getScore() {
		return score;
	}
}
