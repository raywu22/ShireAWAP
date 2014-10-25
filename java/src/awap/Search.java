package awap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Search {
	
	public void search(OurBoard currentState) {
		HashSet<OurBoard> visited = new HashSet<>(); // TODO: add equals and hashmap to ourboard
		
		List<OurBoard> agenda = new ArrayList<>();
		
		OurBoard bestState = null;
		int bestScore = 0;
		
		while(currentState != null) {
			for(Point corner : currentState.getCorners()) {
				for(Block available : currentState.getBlocksAvailable()) {
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
			
			if(agenda.size() > 0)
				currentState = agenda.remove(agenda.size() - 1);
			else
				currentState = null;
		}
		
		
	}
}
