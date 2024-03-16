import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GShapeToolBar extends JToolBar {
    private static final long serialVersionUID = 1L;

    private JRadioButton rectangleButton;
    private JRadioButton ovalButton;
    private JRadioButton lineButton;
    private JRadioButton polygonButton;

    private GDrawingPanel drawingPanel; // 그림판 변수 추가

    public GShapeToolBar() {
        this.setFloatable(false);

        ButtonGroup buttonGroup = new ButtonGroup();

        this.rectangleButton = new JRadioButton("rectangle");
        this.ovalButton = new JRadioButton("oval");
        this.lineButton = new JRadioButton("line");
        this.polygonButton = new JRadioButton("polygon");

        buttonGroup.add(rectangleButton);
        buttonGroup.add(ovalButton);
        buttonGroup.add(lineButton);
        buttonGroup.add(polygonButton);

        this.add(rectangleButton);
        this.add(ovalButton);
        this.add(lineButton);
        this.add(polygonButton);

        // 각 버튼에 대한 ActionListener 추가
        rectangleButton.addActionListener(new ShapeButtonListener());
        ovalButton.addActionListener(new ShapeButtonListener());
        lineButton.addActionListener(new ShapeButtonListener());
        polygonButton.addActionListener(new ShapeButtonListener());
    }

    // 그림판을 설정하는 메서드
    public void setDrawingPanel(GDrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    // 버튼 클릭 시 호출될 ActionListener 구현
    private class ShapeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selectedButton = (JRadioButton) e.getSource(); // 선택된 버튼 가져오기
            String shape = selectedButton.getText(); // 선택된 버튼의 텍스트 (도형 이름) 가져오기
            drawingPanel.setCurrentShape(shape); // GDrawingPanel에 현재 선택된 도형 설정
        }
    }
}
