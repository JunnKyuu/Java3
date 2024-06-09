package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.Vector;

import javax.swing.*;

import shapetools.GShape;
import shapetools.GShape.EAnchors;
import shapetools.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {

	@Serial
	private static final long serialVersionUID = 1L;

	private enum EDrawingState {
		eIdle,
		e2PState,
		eNPState,
		eTransformation;
	}

	private enum ETransformation {
		eDraw,
		eMove,
		eResize,
		eRotate
	}

	private EDrawingState eDrawingState;
	private ETransformation eTransformation;

	// component
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;
	private Color color;
	private BufferedImage bufferedImage;
	private Graphics2D graphics;

	// constructors
	public GDrawingPanel() {
		// set attributes
		this.setBackground(Color.WHITE);
		this.eDrawingState = EDrawingState.eIdle;
		this.eTransformation = null;

		// components
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);

		// dynamic components
		this.shapes = new Vector<>();
	}

	public void initialize() {
	}

	public void setShapeTool(GShape shapeTool) {
		this.shapeTool = shapeTool;
	}

	public Vector<GShape> getShapes() {
		return this.shapes;
	}

	public void setShapes(Object object) {
		this.shapes = (Vector<GShape>) object;
	}

	public void paint(Graphics graphics) {
		for (GShape shape : shapes) {
			shape.draw(graphics);
		}
	}

	// drawing
	private void startDrawing(int x, int y) {
		bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) bufferedImage.getGraphics();
		graphics.setBackground(this.getBackground());
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());

		currentShape = shapeTool.clone();
		currentShape.setOrigin(x, y);
		currentShape.setColor(color); // 도형 색상 설정
		graphics.setColor(color);
	}
	private void keepDrawing(int x, int y) {
		currentShape.movePoint(x, y);
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());

		for (GShape shape : shapes) {
			shape.draw(graphics); // 기존 도형들을 이미지 버퍼에 그림
		}
		currentShape.drag(graphics); // 새로운 도형을 이미지 버퍼에 그림

		Graphics2D g2d = (Graphics2D) getGraphics();
		g2d.drawImage(bufferedImage, 0, 0, this); // 이미지 버퍼에서 화면으로 복사
		g2d.dispose();
	}
	private void continueDrawing(int x, int y) {
		currentShape.addPoint(x, y);
	}
	private void stopDrawing(int x, int y) {
		currentShape.addPoint(x, y);
		shapes.add(currentShape);
		currentShape.setColor(color); // 도형 색상 설정
		currentShape.setSelected(this.getGraphics(), x, y);
	}

	// moving
	private void startMoving(MouseEvent e) {
		if (currentShape != null) {
			currentShape.startMove(getGraphics(), e.getX(), e.getY());
		}
	}

	public void keepMoving(MouseEvent e) {
		if (currentShape != null) {
			Graphics2D tempGraphics = (Graphics2D) bufferedImage.getGraphics();
			tempGraphics.setColor(color); // 도형의 색상으로 설정
			tempGraphics.setBackground(this.getBackground());
			tempGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());

			for (GShape shape : shapes) {
				shape.draw(tempGraphics); // 기존 도형들을 이미지 버퍼에 그림
			}
			currentShape.keepMove(tempGraphics, e.getX(), e.getY());

			Graphics2D g2d = (Graphics2D) getGraphics();
			g2d.drawImage(bufferedImage, 0, 0, this); // 이미지 버퍼에서 화면으로 복사
			g2d.dispose();
		}
	}

	private void stopMoving(MouseEvent e) {
		if (currentShape != null) {
			currentShape.stopMove(getGraphics(), e.getX(), e.getY());
			currentShape.setColor(color); // 도형 색상 설정
			repaint();
		}
	}



	private GShape onShape(int x, int y) {
		for (GShape shape : this.shapes) {
			boolean isShape = shape.onShape(x, y);
			if (isShape) {
				return shape;
			}
		}
		return null;
	}

	private void changeCursor(int x, int y) {
		GShape gShape = this.onShape(x, y);
		if (gShape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else {
			this.setCursor(gShape.getCursor());
		}
	}

	// mouse event
	private class MouseEventHandler implements MouseListener, MouseMotionListener {
		private void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if (shapeTool.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPState;
				} else {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2PState;
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
			} else if (eDrawingState == EDrawingState.e2PState) {
				keepDrawing(e.getX(), e.getY());
			} else {
				changeCursor(e.getX(), e.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				currentShape = onShape(e.getX(), e.getY());
				if (currentShape == null) {
					if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
						startDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.e2PState;
					}
				} else {
					currentShape.setColor(color);
					if (currentShape.getSelectedAnchor().equals(EAnchors.eMM)) {
						startMoving(e);
					} else if (currentShape.getSelectedAnchor().equals(EAnchors.eRR)) {
						// rotate
					} else {
						// resize
						currentShape.startResize(getGraphics(), e.getX(), e.getY());
					}
					eDrawingState = EDrawingState.eTransformation;
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PState) {
				keepDrawing(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eTransformation) {
				currentShape.setColor(color);
				if (currentShape.getSelectedAnchor().equals(EAnchors.eMM)) {
					keepMoving(e);
				} else if (currentShape.getSelectedAnchor().equals(EAnchors.eRR)) {
					// rotate
				} else {
					// resize
					currentShape.keepResize(getGraphics(), e.getX(), e.getY());
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PState) {
				stopDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			} else if (eDrawingState == EDrawingState.eTransformation) {
				if (currentShape.getSelectedAnchor().equals(EAnchors.eMM)) {
					stopMoving(e);
				} else if (currentShape.getSelectedAnchor().equals(EAnchors.eRR)) {
					// rotate
				} else {
					// resize
					currentShape.stopResize(getGraphics(), e.getX(), e.getY());
				}
				currentShape.setColor(color);
				eDrawingState = EDrawingState.eIdle;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) { }
		@Override
		public void mouseExited(MouseEvent e) { }
	}

	public void actionPerformed(ActionEvent e) {
		color = JColorChooser.showDialog(null, "Select Color", Color.BLACK); // 다이얼로그 창 제목 변경
		if (color != null) {
			if (currentShape != null) {
				currentShape.setColor(color); // currentShape에 색상 할당
				repaint(); // 선택한 색상으로 다시 그리기
			} else {
				if (shapeTool != null) {
					shapeTool.setColor(color);
					repaint();
				}
			}
		}
	}
}


