package awap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class OurBoard {
	private ArrayList<Block> boardBlocks;
	private List<Point> corners;
	private List<Point> allFilledPoints;
	private List<Block> availableBlocks;
	
	public OurBoard(List<Point> corner, ArrayList<Block> blocks,List<Point> filledPoints, List<Block> avalBlocks) {
		this.corners         = corner;
		this.boardBlocks     = blocks;
		this.allFilledPoints = filledPoints;
		this.availableBlocks = avalBlocks;
	}
	
	/**
	 * producer method
	 * @param b - block to be placed, assume that it is always able to be added
	 * @param p - point where the block is placed
	 * @return OurBoard
	 */
	public OurBoard addBlock(Block blockToAdd, Point pointAddingTo) {
	    
		return null;
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
		return null;
	}
	
	
	/**
	 * Order available blocks based on largest Manhattan distance
	 * @return
	 */
	public ArrayList<Block> orderBlocksForPath() {
		return null;
	}
}
