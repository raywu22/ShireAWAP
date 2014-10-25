package awap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Search {
	
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
								OurBoard newState = currentState.addBlock(available, placeAt);
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
			List<BlockPlacement> originalBlocksUsed = originalState.getBlocksUsed();
			int closestDist = Integer.MAX_VALUE;
			BlockPlacement blockToPlace = null;
			for(BlockPlacement block : bestState.getBlocksUsed()) {
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
