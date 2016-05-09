/**
 * 界面布局类
 */
package iborder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game2048View implements ActionListener{
    Block[] blocks;     //方块
    JPanel myJPanel;    //主面板
    JPanel jp1,jp2;     //子面板
    //  int moveFlag;               // 用于累计移动的次数
    boolean numFlag;        // 用于判断是否还能加入新的数字
    JLabel scroeValue;      //显示分数

    public Game2048View(JFrame myJFrame){
        blocks=new Block[16];
//      moveFlag=0;
        numFlag=true;
        this.myJPanel=(JPanel)myJFrame.getContentPane();
        setJp1();
        myJPanel.add(jp1,BorderLayout.NORTH);
        setJp2();
        myJPanel.add(jp2, BorderLayout.CENTER);
        myJFrame.addKeyListener(new Game2048Logic(this,myJFrame,blocks,numFlag,scroeValue));
    }

    public void addc(JPanel jp1,Component component, GridBagConstraints gbc,int gridwidth,int gridheight, int weightx,int weighty,int gridx,int gridy) {
        //此方法用来添加控件到容器中
        gbc.gridwidth=gridwidth;        //该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        gbc.gridheight=gridheight;      //该方法是设置组件垂直所占用的格子数
        gbc.weightx=weightx;                //该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.weighty=weighty;                //该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.gridx=gridx;
        gbc.gridy=gridy;
        gbc.fill=GridBagConstraints.BOTH;
        jp1.add(component,gbc);
    }

    public void setJp1() {
        GridBagLayout gbLayout=new GridBagLayout();
        jp1=new JPanel(gbLayout);

        JPanel Jtitle=new JPanel();
        JLabel title=new JLabel("2048");
        title.setFont(new Font("font", Font.PLAIN, 45));
        title.setHorizontalAlignment(JLabel.LEFT);
        Jtitle.add(title);
        jp1.add(Jtitle);

        JPanel Jscroe=new JPanel(new GridLayout(2, 1));
        JLabel scroe=new JLabel("Scroe");
        scroe.setFont(new Font("font", Font.PLAIN, 16));
        scroe.setHorizontalAlignment(JLabel.CENTER);
        scroeValue=new JLabel("0");
        scroeValue.setFont(new Font("font", Font.PLAIN, 16));
        scroeValue.setHorizontalAlignment(JLabel.CENTER);
        Jscroe.add(scroe);
        Jscroe.add(scroeValue);
        jp1.add(Jscroe);

        JPanel Jnoite=new JPanel();
        JLabel noite=new JLabel("方向键移动数字累加至2048");
        noite.setFont(new Font("font", Font.PLAIN, 14));
        noite.setHorizontalAlignment(JLabel.LEFT);
        Jnoite.add(noite);
        jp1.add(Jnoite);

        JPanel JnewGame=new JPanel();
        JButton newGame=new JButton("New Game");
        newGame.setHorizontalAlignment(JButton.CENTER);
        newGame.addActionListener(this);
        JnewGame.add(newGame);
        jp1.add(JnewGame);

        GridBagConstraints gbc=new GridBagConstraints();
        addc(jp1, Jtitle, gbc, 3, 2, 60, 60, 0, 0);
        addc(jp1, Jscroe, gbc, 0, 2, 40, 60, 3, 0);
        addc(jp1, Jnoite, gbc, 3, 1, 60, 40, 0, 2);
        addc(jp1, JnewGame, gbc, 0, 1, 40, 40, 3, 2);
    }

    public void setJp2() {
        addBlock();
        initBlock();
        initBlock();
    }

    /**
     * 添加方块
     */
    public void addBlock(){
        jp2=new JPanel();
        jp2.setLayout(new GridLayout(4, 4, 5, 5));
        for (int i = 0; i < blocks.length; i++) {
            blocks[i]=new Block();
            blocks[i].setHorizontalAlignment(JLabel.CENTER);// 不透明的标签
            blocks[i].setOpaque(true);// 设置控件不透明
            jp2.add(blocks[i]);
        }
    }

    /**
     * 初始化方块
     */
    public void initBlock(){
        while (numFlag) {
            int index=(int) (Math.random()*16);
            if (blocks[index].getText().trim().equals("")) {
                blocks[index].setValue("2");
                break;
            } else {
                continue;
            }
        }
    }

    /**
     * 获得第一个子面板的高度
     */
    public int getMyJPanelHeidth() {
        return jp1.getSize().height;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 自动生成的方法存根
        newGame();
    }

    /**
     * 重新开始游戏
     */
    public void newGame() {
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setValue("");
        }
        numFlag=true;
        scroeValue.setText("0");
        initBlock();
        initBlock();
    }
}