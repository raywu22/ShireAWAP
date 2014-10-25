package awap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Search {
	
	public static BlockPlacement searchForPoint(OurBoard currentState, Game game, Point goal) {
		OurBoard originalState = currentState;
		HashSet<OurBoard> visited = new HashSet<>(); // TODO: add equals and hashmap to ourboard
		
		List<OurBoard> agenda = new ArrayList<>();
		agenda.add(new OurBoard(game));
		
		// find a good placement of blocks
		while(agenda.get(0).getPoints().contains(goal)) {
			OurBoard m_board = agenda.remove(0);
			List<Point> corners = m_board.orderCornersForPath(goal);
			List<Block> blocks  = m_board.orderBlocksForPath();
			for(Point corner : corners) {
				for(Block available : blocks) {
					for(int rot = 0; rot < 4; rot++) {
						for(Point offset : available.getOffsets()) {
							Point placeAt = corner.subtract(offset);
							
							boolean valid = agenda.get(0).checkForConflicts(new BlockPlacement(available, placeAt, rot));
							if(valid) {
								OurBoard newState = m_board.addBlock(available, placeAt, rot);
								agenda.add(0, newState);
							}
						}
					}
				}
			}
		}
		
		if(agenda.get(0) != null) {
			// decide which to place first
			List<BlockPlacement> originalBlocksUsed = originalState.getBlocksUsed();
			int closestDist = Integer.MAX_VALUE;
			BlockPlacement blockToPlace = null;
			for(BlockPlacement block : agenda.get(0).getBlocksUsed()) {
				if(originalBlocksUsed.contains(block))
					continue;
				if(originalState.checkForConflicts(blockToPlace)) { //TODO: implement this
					return blockToPlace;
				}
			}
			
			return blockToPlace;
		}
		
		throw new RuntimeException();
		
	}
	
	
	public static BlockPlacement search(OurBoard currentState, Game game) {
		OurBoard originalState = currentState;
		HashSet<OurBoard> visited = new HashSet<>(); // TODO: add equals and hashmap to ourboard
		
		List<OurBoard> agenda = new ArrayList<>();
		
		OurBoard bestState = null;
		int bestScore = 0;
		
		// find a good placement of blocks
		while(currentState != null) {
			for(Point corner : currentState.getCorners()) {
				for(Block available : currentState.getBlocksAvailable()) {
					for(int rot = 0; rot < 4; rot++) {
						for(Point offset : available.getOffsets()) {
							Point placeAt = corner.subtract(offset);
							
							boolean valid = false;// TODO: currentState.canPlace(available, placeAt);
							if(valid) {
								OurBoard newState = currentState.addBlock(available, placeAt, rot);
								if(newState.getScore() > bestScore) {
									bestScore = newState.getScore();
									bestState = newState;
								}
								if(!visited.contains(newState)) {
									visited.add(newState);
									agenda.add(newState);
								}
							}
						}
					}
				}
			}
			
			if(agenda.size() > 0)
				currentState = agenda.remove(agenda.size() - 1);
			else
				currentState = null;
		}
		
		if(bestState != null) {
			// decide which to place first
			List<BlockPlacement> originalBlocksUsed = originalState.getBlockPlacements();
			int closestDist = Integer.MAX_VALUE;
			BlockPlacement blockToPlace = null;
			for(BlockPlacement block : bestState.getBlockPlacements()) {
				if(originalBlocksUsed.contains(block))
					continue;
				
				Point p = block.getLocation();
				int dist = game.mahattanDistanceToNearestCompetitor(p);
				
				if(dist < closestDist) {
					closestDist = dist;
					blockToPlace = block;
				}
			}
			
			return blockToPlace;
		}
		
		throw new RuntimeException();
	}
}
