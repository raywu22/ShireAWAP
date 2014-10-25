package awap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OurBoard {
	private ArrayList<Block> boardBlocks;
	private List<Point> corners;
	private List<Point> allFilledPoints;
	private List<Block> availableBlocks;
	
	private int score;
	
	public OurBoard(List<Point> corner, ArrayList<Block> blocks,List<Point> filledPoints) {
		this.corners         = corner;
		this.boardBlocks     = blocks;
		this.allFilledPoints= filledPoints;
//		availableBlocks = totalDomain - blocks;
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
	
	public int getScore() {
		return score;
	}
}
