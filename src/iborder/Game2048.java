/**
 * ����
 */
package iborder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

@SuppressWarnings("serial")
public class Game2048 extends JFrame implements FocusListener{

    public Game2048() {
        // TODO �Զ����ɵĹ��캯�����
        Game2048View myGame2048View = new Game2048View(this);
        pack();
        int width = 400;
        int height = myGame2048View.getMyJPanelHeidth()+width;
        setSize(width, height);
        setMinimumSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addFocusListener(this);
        setVisible(true);
    }

    public static void main (String[] args) {
        // TODO �Զ����ɵķ������
        try
        {
            //����л��������뷨���°�����bug
            System.setProperty("sun.java2d.noddraw", "true");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            //���ء����á���ť
            UIManager.put("RootPane.setupButtonVisible", false);
        }
        catch(Exception e)
        {
            System.out.println("�������ʧ��");
        }
//      JFrame.setDefaultLookAndFeelDecorated(true);
        Game2048 myGame2048 = new Game2048();
        myGame2048.requestFocus();
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO �Զ����ɵķ������

    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO �Զ����ɵķ������
        this.requestFocus();
    }

}