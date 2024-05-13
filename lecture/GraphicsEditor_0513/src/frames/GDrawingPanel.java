package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import javax.swing.JPanel;

import shapetools.GShape;
import shapetools.GShape.EAnchor;
import shapetools.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;

	private enum EDrawingState {
		eIdle, e2PState, eNPState, 
	}
	private enum ETransformation {
		eDraw,eMove,eResize,eRotate
	}
	private EDrawingState eDrawingState;
	private ETransformation eTransformation;
	// component 부품
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;
	

	// constructors
	public GDrawingPanel() {
		//attributes
		this.setBackground(Color.gray); //graphics에서 getbackground해야함
		this.eDrawingState = EDrawingState.eIdle;
		this.eTransformation = null;
		//components
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		//dynamic components - 구조적 자식과 다름. 생겼다 없어졌다 함. 
		this.shapes = new Vector<GShape>();
	}

	public void initialize() {
		// TODO Auto-generated method stub

	}

	// setters and getters
	public void setShapeTool(GShape shapeTool) {
		// TODO Auto-generated method stub
		this.shapeTool = shapeTool;

	}

	public Vector<GShape> getShapes() {
		return this.shapes;
	}

	public void setShapes(Object object) {
		this.shapes = (Vector<GShape>)object;
	}

	// methods

	public void paint(Graphics graphics) {
		for (GShape shape : shapes) {
			shape.draw(graphics);
		}
	}

	private void startDrawing(int x, int y) {
		currentShape = shapeTool.clone();
		currentShape.setOrigin(x, y);
	}

	private void keepDrawing(int x, int y) {
		currentShape.movePoint(x, y);
		currentShape.drag(getGraphics());
	}

	private void continueDrawing(int x, int y) {
		currentShape.addPoint(x, y);
	}

	private void stopDrawing(int x, int y) {
		currentShape.addPoint(x, y);
		shapes.add(currentShape);
	}
	private EAnchor onShape(int x, int y) {
		EAnchor eAnchor = null;
		for(GShape shape : this.shapes) {
			eAnchor =shape.onShape(x,y);
			if(eAnchor != null) {
				break;
			}
			
		}
		return eAnchor;
	}

	private class MouseEventHandler implements MouseListener, MouseMotionListener {
		
		
		private void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if (shapeTool.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPState;
				}
			} else if (eDrawingState == EDrawingState.eNPState) {
				continueDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eNPState;
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eNPState) {
				stopDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eNPState) {
				keepDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eNPState;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if(onShape(e.getX(),e.getY())== null) {
					if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
						startDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.e2PState;
					}
				}else {
					//transforamtion
//					eTransformation = ETransformation.
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PState) {
				keepDrawing(e.getX(), e.getY());
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PState) {
				stopDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

}
