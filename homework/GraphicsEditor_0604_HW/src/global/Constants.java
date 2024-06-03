package global;

import shapetools.GLine;
import shapetools.GOval;
import shapetools.GPolygon;
import shapetools.GRectangle;
import shapetools.GShape;

public class Constants {
	public enum EShapeButtons {
		eRactangle("rectangle", new GRectangle()), // 스펠링 안틀릴 수 있음 
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
	public static class GMainFrame{
		public final static int WIDTH= 400; // int가 아니라 String이 되면 리터럴이라고 함. 변하지 않는 글자 = 리터럴, 변하지 않는 숫자 = 상수
		public final static int HEIGHT= 600; // 한번 값을 선언하면 바뀌지 않은 것이 final, 
		//local이 달라지면, 언어가 달라짐, 시간이 달라짐,계량단위( 센티미터, 인지, 기준좌표계나 지도set이 달라짐 )
		//이걸 enum으로 할 수도 있음
	}
	public static class GMenuBar{
		public enum EMenu{
			eFile("파일"),
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
