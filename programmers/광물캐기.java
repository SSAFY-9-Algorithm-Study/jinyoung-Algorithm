package programmers;

import java.util.*;

public class 광물캐기 {
    String[] minerals;

	public int solution(int[] picks, String[] minerals) {

		int pickSum = Arrays.stream(picks).sum() * 5;

		if (pickSum < minerals.length) {
			this.minerals = new String[pickSum];

			for (int i = 0; i < pickSum; i++) {
				this.minerals[i] = minerals[i];
			}

		} else {
			this.minerals = minerals;
		}

		int answer = calculate(picks, this.minerals);

		return answer;
	}
    
    public int calculate(int[] picks, String[] minerals) {
        
        int[][] mineralValue = new int[(int)Math.ceil((double)minerals.length / 5)][5];
        
        for (int i = 0; i < minerals.length; i++) {
            
            if (minerals[i].equals("diamond")) {
                mineralValue[i / 5][i % 5] = 25;
                continue;
            } 
            
            if (minerals[i].equals("iron")) {
                mineralValue[i / 5][i % 5] = 5;
                continue;
            } 
            
            if (minerals[i].equals("stone")){
                mineralValue[i / 5][i % 5] = 1;
            }

        }

        Arrays.sort(mineralValue, (o1, o2) -> {
            return Integer.compare(Arrays.stream(o2).sum(), Arrays.stream(o1).sum());
        });
        
        int answer = 0;
        int index = -1;
        for (int i = 0; i < picks.length; i++) {
            int pick = picks[i];
            
            while (pick-- > 0) {
                
                index++;
                if (index >= mineralValue.length) break;
                
                if (i == 0) {
                    
                    for (int j = 0; j < 5; j++) {

                        if (mineralValue[index][j] != 0) {
                            answer += 1;
                        }
                    }
                    continue;
                } 
                
                if (i == 1) {
                    
                    for (int j = 0; j < 5; j++) {
                        
                        if (mineralValue[index][j] != 0) {
                            answer += (int) Math.ceil((double)mineralValue[index][j] / 5);
                        }
                        
                    }
                    continue;
                } 
                
                if (i == 2) {
                    
                    for (int j = 0; j < 5; j++) {

                        if (mineralValue[index][j] != 0) {
                            answer += mineralValue[index][j] ;
                        }
                    }
                }
                
            }
            
        }
        
        return answer;
    }

}
