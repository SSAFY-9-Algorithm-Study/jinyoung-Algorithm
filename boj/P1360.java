package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P1360 {
	static final String TYPE = "type";
	static final String UNDO = "undo";
	
	static class Editor {
		String command, text;
		int time;
		
		public Editor(String command, String text, int time) {
			this.command = command;
			this.text = text;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		Stack<Editor> inputs = new Stack<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			String text = st.nextToken();
			int time = Integer.parseInt(st.nextToken());
			inputs.push(new Editor(command, text, time));
		}
		
		Editor lastEdit = inputs.pop();
		String text = "";
		int time = lastEdit.time;
		
		if (lastEdit.command.equals("undo")) {
			time -= Integer.parseInt(lastEdit.text);
		} else {
			text = lastEdit.text;
		}

		StringBuffer sb = new StringBuffer(text);
		
//		System.out.println(command + " " + text + " " + time);
		for (int i = 1; i < N; i++) {
			Editor edit = inputs.pop();
			
			if (time <= edit.time) {
				continue;
			}
			
			if (edit.command.equals(TYPE)) {
				sb.append(edit.text);
			} else {
				time = edit.time - Integer.parseInt(edit.text);
			}
			
		}
		
		System.out.println(sb.reverse());
	}
}
