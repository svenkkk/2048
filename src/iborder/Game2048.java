/**
 * 主类
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
        // TODO 自动生成的构造函数存根
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
        // TODO 自动生成的方法存根
        try
        {
            //解决切换中文输入法导致白屏的bug
            System.setProperty("sun.java2d.noddraw", "true");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            //隐藏“设置”按钮
            UIManager.put("RootPane.setupButtonVisible", false);
        }
        catch(Exception e)
        {
            System.out.println("设置外观失败");
        }
//      JFrame.setDefaultLookAndFeelDecorated(true);
        Game2048 myGame2048 = new Game2048();
        myGame2048.requestFocus();
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO 自动生成的方法存根
        this.requestFocus();
    }

}