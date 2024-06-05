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
		
		public String getText() { return text; }
		public GShape getShapeTool () { return this.shapeTool; }
	}

	public final static int NUM_POINTS = 20;

	public static class GMainFrame{
		public final static int WIDTH= 1000;
		public final static int HEIGHT= 600;
	}

	public static class GMenuBar{
		public enum EMenu{
			eFile("File"),
			eEdit("Edit");
			
			private String text;
			private EMenu(String text) { this.text = text; }
			public String getText() { return this.text; }
		}
	}
}
