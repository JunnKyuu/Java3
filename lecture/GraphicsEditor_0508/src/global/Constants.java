package global;

import shapetools.GLine;
import shapetools.GOval;
import shapetools.GPolygon;
import shapetools.GRectangle;
import shapetools.GShape;

public class Constants {
	public enum EShapeButtons {
		eRactangle("rectangle", new GRectangle()), 
		eOval("oval", new GOval()),
		eLine("line", new GLine()), 
		ePolygon("polygon", new GPolygon());
	
		private String text;
		private GShape shapeTool;
		private EShapeButtons(String text, GShape shapeTool){
			this.text = text;
			this.shapeTool = shapeTool;
		}

		public String getText() {
			return text;
		}
		public GShape getShapeTool () {
			return this.shapeTool;
		}
	}
	public final static int NUM_POINTS = 20;
	
	public static class GMainFrame {
		public final static int WIDTH = 400;  // final은 한 번 값이 할당되면 변하지 않는다는 의미
		public final static int HEIGHT = 600; 
	}
	
	public static class GMenuBar {
		public enum EMenu {
			// ""안에 글씨만 따로 파일에 모을 것임. string 또는 json 파일을 읽어서 가져올 것
			eFile("파일"), // File에는 4개의 매개변수가 있음. 아이콘, 툴팁, ,,,
			eEdit("편집");
			
			private String text;
			
			private EMenu(String text) {
				this.text = text;	
			}
			
			public String getText() {
				return this.text;
			}
		}
	}	
}
