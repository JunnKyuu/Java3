package global;

import shapeTools.GRectangleTool;
import shapeTools.GShapeTool;

public class Constants {
	public enum EShapeButtons {
		// 여기에 도구 자체를 만들어서 전달
		eRectangle("rectangle", new GRectangleTool()), 
		eOval("oval", new GRectangleTool()), 
		eLine("line", new GRectangleTool()), 
		ePolygon("polygon", new GRectangleTool()); 
		
		private String text;
		private GShapeTool shapeTool;
		private EShapeButtons(String text, GShapeTool shapeTool) {
			this.text = text;
			this.shapeTool = shapeTool;
		}
		
		public String getText() {
			return this.text;
		}
		
		public GShapeTool getShapeTool() {
			return this.shapeTool;
		}
	}
}
