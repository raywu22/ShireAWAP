package awap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OurBoard {
	Game originalState; // this is the state that the server has told us is the current state (before we start a search)
	
	private List<BlockPlacement> boardBlockPlacements;
	private List<Point> corners;
	private List<Block> availableBlocks;
	private List<Point> pointsCovered;
	private int score;

	public OurBoard(Game game) {
		this.originalState = game;
		boardBlockPlacements = new ArrayList<>();
		corners = new ArrayList<>();
		availableBlocks = new ArrayList<>();
		
		corners.add(game.getStartCorner());
		pointsCovered = new ArrayList<Point>();
	}
	
	/**
	 * 
	 * @param corner - gives list of already available corners
	 * @param blocks - list of blocks already used in our board
	 * @param filledPoints - ALL filled points on the board in the for List(isOurPoint,xCoord,yCoord) where
	 *                       isOurPoint = 0 if it is our point and isOurPoint = 1 if other point
	 */

	public OurBoard(List<Point> corner, List<BlockPlacement> blockPlacements, int score, List<Point> points) {
	    //TODO set this.corners to new available corners
		this.corners = corner;
		this.boardBlockPlacements = blockPlacements;
		this.score = score;
		this.pointsCovered = points;
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
	    List<Point> pointsToAdd = new ArrayList<Point>();
	    for(Point o : blockToAdd.getOffsets()) {
	    	pointsToAdd.add(o.add(blockPlacement.getLocation()));
	    }
	    pointsToAdd.addAll(pointsCovered);
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
	    return new OurBoard(this.getCorners(),copyOfBlockPlacements, this.score + scoreOfNewBlock, pointsToAdd);
	}
	/**
	 * @return block placements in our board
	 */
	public List<BlockPlacement> getBlockPlacements(){
	    return this.boardBlockPlacements;
	}
	
	/**
	 * @return - New list of places where a block can be placed
	 */
	public List<Point> getCorners() {
		return corners;
	}
	/**
	 * updates corners to valid new corners
	 */
	public void updateCorners(){
	    BlockPlacement recentBlockPlacement = this.getBlockPlacements().get(this.getBlockPlacements().size()-1);
	    Block individualBlock = recentBlockPlacement.getBlock();
	    List<Point> possibleCorners = new ArrayList<>();
	    Set<Point> allSides = new HashSet<>();
	    for (Point side:individualBlock.getOffsets()){
            allSides.add(new Point(side.getX()+1,side.getY()));
            allSides.add(new Point(side.getX(),side.getY()+1));
            allSides.add(new Point(side.getX()-1,side.getY()));
            allSides.add(new Point(side.getX(),side.getY()-1));
	    }
	    for (Point offset:individualBlock.getOffsets()){
	        possibleCorners.add(new Point(offset.getX()+1,offset.getY()+1));
	        possibleCorners.add(new Point(offset.getX()+1,offset.getY()-1));
	        possibleCorners.add(new Point(offset.getX()-1,offset.getY()+1));
	        possibleCorners.add(new Point(offset.getX()-1,offset.getY()-1));
	    }
	    for (int i=possibleCorners.size()-1;i==-1;i--){
	        if (allSides.contains(possibleCorners.get(i)) || individualBlock.getOffsets().contains(possibleCorners.get(i))){
	            possibleCorners.remove(i);
	        }
	    }
	    List<Point> newPossibleCorners = new ArrayList<>();
	    for (Point n:possibleCorners){
	        newPossibleCorners.add(new Point(n.getX()+recentBlockPlacement.getLocation().getX(),n.getY()+recentBlockPlacement.getLocation().getY()));
	    }
	    
	    List<Point> updatedCorners = new ArrayList<>(corners);

	    for (Point k:newPossibleCorners){
	        if (!updatedCorners.contains(k)){
	            updatedCorners.add(k);
	        }
	    }
	    Set<Point> badPoints = new HashSet<>();
	    
	    for (BlockPlacement placement:this.getBlockPlacements()){
	        for (Point offset:placement.getBlock().getOffsets()){
	            Point pointToAdd = placement.getLocation();
	            badPoints.add(new Point(pointToAdd.getX()+offset.getX(),pointToAdd.getY()+offset.getY()));
	        }
	    }
	    Set<Point> badPointsWithSides = new HashSet<>(badPoints);
	    for (Point bad:badPoints){
	        badPointsWithSides.add(new Point(bad.getX()+1,bad.getY()));
            badPointsWithSides.add(new Point(bad.getX(),bad.getY()+1));
            badPointsWithSides.add(new Point(bad.getX()-1,bad.getY()));
            badPointsWithSides.add(new Point(bad.getX(),bad.getY()-1));
	    }
	    for (int i=0;i<originalState.getState().getDimension();i++){
	        for (int j=0;j<originalState.getState().getDimension();j++){
	            if (originalState.getState().getBoard().get(i).get(j)!=-1){
	                badPointsWithSides.add(new Point(i,j));
	            }
	        }
	    }
	    for (int v=updatedCorners.size()-1;v==-1;v--){
	        if (badPointsWithSides.contains(updatedCorners.get(v))){
	            updatedCorners.remove(v);
	        }
	    }
	    this.corners=updatedCorners;
	}
	/**
	 * @return points covered by our grid
	 */
	public List<Point> getPoints() {
		return pointsCovered;
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
