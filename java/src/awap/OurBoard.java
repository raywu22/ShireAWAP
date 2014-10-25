package awap;

import java.util.ArrayList;
import java.util.List;

public class OurBoard {
	Game originalState; // this is the state that the server has told us is the current state (before we start a search)
	
	private List<BlockPlacement> boardBlockPlacements;
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
	public OurBoard(List<Point> corner, List<BlockPlacement> blockPlacements, int score) {
	    //TODO set this.corners to new available corners
		this.corners = corner;
		this.boardBlockPlacements = blockPlacements;
		this.score = score;
	}
	
	public void setGame(Game game) {
		this.originalState = game;
	}
	
	/**
	 * producer method
	 * @param b - block to be placed, ASSUME that it is always able to be added
	 * @param p - point where the block is placed
	 * @return OurBoard
	 */
	public OurBoard addBlock(Block blockToAdd, Point pointAddingTo,int rotation) {
		BlockPlacement toAddBlockPlacement = new BlockPlacement(blockToAdd,pointAddingTo,rotation);
		return addBlock(toAddBlockPlacement);
	}
	
	public OurBoard addBlock(BlockPlacement blockPlacement) {
	    Point pointAddingTo = blockPlacement.getLocation();
	    Block blockToAdd = blockPlacement.getBlock();
	    List<BlockPlacement> copyOfBlockPlacements = new ArrayList<>(this.getBlockPlacements());
	    copyOfBlockPlacements.add(blockPlacement);
	    int scoreOfNewBlock = blockToAdd.getOffsets().size();
	    Block rotated = blockPlacement.getRotatedBlock();
	    for(List<Integer> bonus : originalState.getState().getBonusSquares()) {
		    for(Point offset : rotated.getOffsets()) {
		    	int x = offset.getX() + pointAddingTo.getX();
		    	int y = offset.getY() + pointAddingTo.getY();
		    	if(bonus.get(0) == x && bonus.get(1) == y)
		    		scoreOfNewBlock *= 3;
		    }
	    }
	    return new OurBoard(this.getCorners(),copyOfBlockPlacements, this.score + scoreOfNewBlock);
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
	public List<Block> getBlocksAvailable() {
		return originalState.getBlocks();
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
