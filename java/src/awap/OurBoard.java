package awap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OurBoard {
	public ArrayList<Block> boardBlocks;
	public List<Point> corners;
	public List<Block> availableBlocks;
	
	public OurBoard(List<Point> corner, ArrayList<Block> blocks) {
		this.corners         = corner;
		this.boardBlocks     = blocks;
//		availableBlocks = totalDomain - blocks;
	}
	
	/**
	 * 
	 * @param b - block to be placed
	 * @param p - point where the block is placed
	 * @return OurBoard
	 */
	public OurBoard addBlock(Block b, Point p) {
		
		
		
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
