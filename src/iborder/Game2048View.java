/**
 * ���沼����
 */
package iborder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game2048View implements ActionListener{
    Block[] blocks;     //����
    JPanel myJPanel;    //�����
    JPanel jp1,jp2;     //�����
    //  int moveFlag;               // �����ۼ��ƶ��Ĵ���
    boolean numFlag;        // �����ж��Ƿ��ܼ����µ�����
    JLabel scroeValue;      //��ʾ����

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
        //�˷���������ӿؼ���������
        gbc.gridwidth=gridwidth;        //�÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
        gbc.gridheight=gridheight;      //�÷��������������ֱ��ռ�õĸ�����
        gbc.weightx=weightx;                //�÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
        gbc.weighty=weighty;                //�÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
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
        JLabel noite=new JLabel("������ƶ������ۼ���2048");
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
     * ��ӷ���
     */
    public void addBlock(){
        jp2=new JPanel();
        jp2.setLayout(new GridLayout(4, 4, 5, 5));
        for (int i = 0; i < blocks.length; i++) {
            blocks[i]=new Block();
            blocks[i].setHorizontalAlignment(JLabel.CENTER);// ��͸���ı�ǩ
            blocks[i].setOpaque(true);// ���ÿؼ���͸��
            jp2.add(blocks[i]);
        }
    }

    /**
     * ��ʼ������
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
     * ��õ�һ�������ĸ߶�
     */
    public int getMyJPanelHeidth() {
        return jp1.getSize().height;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO �Զ����ɵķ������
        newGame();
    }

    /**
     * ���¿�ʼ��Ϸ
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