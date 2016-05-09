/**
 * ҵ���߼���
 */
package iborder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Game2048Logic implements KeyListener{
    Block[] blocks;
    boolean numFlag;        // �����ж��Ƿ��ܼ����µ�����
    JLabel scroeValue;      //��ʾ����
    int blocksarr[]=new int[4];     //����һ��/�з����е���ֵ
    JFrame myJFrame;
    int scroe=0;
    Game2048View game2048View;

    //��ʼ�������¼�
    public Game2048Logic(Game2048View game2048View, JFrame myJFrame, Block[] blocks,boolean numFlag,JLabel scroeValue) {
        // TODO �Զ����ɵĹ��캯�����
        this.blocks=blocks;
        this.numFlag=numFlag;
        this.scroeValue=scroeValue;
        this.myJFrame=myJFrame;
        this.game2048View=game2048View;
    }

    //��ʼ����ť�¼�
    public Game2048Logic() {
        // TODO �Զ����ɵĹ��캯�����
    }

    public boolean getnumFlag() {
        return numFlag;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO �Զ����ɵķ������
        int[] blocksarr=getBlock();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                colBlock("up");
                hasEmptyBlock();
                if (Arrays.equals(blocksarr, getBlock())) {
                } else {
                    refershBlock();
                }
                isGameFail("up");
                break;
            case KeyEvent.VK_DOWN:
                colBlock("down");
                hasEmptyBlock();
                if (Arrays.equals(blocksarr, getBlock())) {
                } else {
                    refershBlock();
                }
                isGameFail("down");
                break;
            case KeyEvent.VK_LEFT:
                rowBlock("left");
                hasEmptyBlock();
                if (Arrays.equals(blocksarr, getBlock())) {
                } else {
                    refershBlock();
                }
                isGameFail("left");
                break;
            case KeyEvent.VK_RIGHT:
                rowBlock("right");
                hasEmptyBlock();
                if (Arrays.equals(blocksarr, getBlock())) {
                } else {
                    refershBlock();
                }
                isGameFail("right");
                break;
            default:
                break;
        }
        scroeValue.setText(""+scroe);
        win();
    }

    /**
     * ��ֱ���򷽿��ƶ�������
     */
    public void colBlock(String direction){
        int tmp1=0;
        int tmp2=0;
        int index=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[tmp1].getText().trim().equals("")) {
                    tmp1+=4;
                    if (tmp1>=16) {
                        break;
                    } else {
                        continue;
                    }
                } else {
                    blocksarr[index]=Integer.parseInt(blocks[tmp1].getText().trim());
                    index+=1;
                    tmp1+=4;
                    if (tmp1>=16 || index>=4) {
                        break;
                    } else {
                        continue;
                    }
                }
            }
            switch (direction) {
                case "up":
                    blocksarr=handleBlocksarr(blocksarr);
                    break;
                case "down":
                    blocksarr=reverseArr(handleBlocksarr(reverseArr(blocksarr)));
                    break;
                default:
                    break;
            }

            for (int n = 0; n < blocksarr.length; n++) {
                if (blocksarr[n]==0) {
                    blocks[tmp2].setText("");
                    blocks[tmp2].setBackground(Color.gray);
                } else {
                    blocks[tmp2].setValue(blocksarr[n]+"");
                }
                tmp2+=4;
            }
            index=0;
            tmp1=i+1;
            tmp2=i+1;
            //�������blockarr
            for (int n = 0; n < blocksarr.length; n++) {
                blocksarr[n]=0;
            }
        }
    }

    /**
     * ˮƽ���򷽿��ƶ�������
     */
    public void rowBlock(String direction) {
        int tmp1=0;
        int tmp2=0;
        int index=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[tmp1].getText().trim().equals("")) {
                    tmp1+=1;
                    if (tmp1>=16) {
                        break;
                    } else {
                        continue;
                    }
                } else {
                    blocksarr[index]=Integer.parseInt(blocks[tmp1].getText().trim());
                    index+=1;
                    tmp1+=1;
                    if (tmp1>=16 || index>=4) {
                        break;
                    } else {
                        continue;
                    }
                }
            }

            switch (direction) {
                case "left":
                    blocksarr=handleBlocksarr(blocksarr);
                    break;
                case "right":
                    blocksarr=reverseArr(handleBlocksarr(reverseArr(blocksarr)));
                    break;
                default:
                    break;
            }

            for (int n = 0; n < blocksarr.length; n++) {
                if (blocksarr[n]==0) {
                    blocks[tmp2].setText("");
                    blocks[tmp2].setBackground(Color.gray);
                } else {
                    blocks[tmp2].setValue(blocksarr[n]+"");
                }
                tmp2+=1;
            }
            index=0;
            //�������blockarr
            for (int n = 0; n < blocksarr.length; n++) {
                blocksarr[n]=0;
            }
        }
    }

    /**
     * ��������һ������
     */
    public int[] handleBlocksarr(int[] blocksarr) {
        int index=0;
        int[] result=new int[4];
        for (int i = 0; i < blocksarr.length; i++) {
            //����
            if (blocksarr[i]!=0) {
                result[index]=blocksarr[i];
                index++;
            }
        }
        if (index==0 || index==1) {
            for (int i = index; i < result.length; i++) {
                result[i]=0;
            }
        } else {
            for (int i = 0; i < blocksarr.length; i++) {
                blocksarr[i]=0;
            }
            switch (index) {
                case 2:
                    if (result[0]==result[1]) {
                        blocksarr[0]=result[0]+result[1];
                        scroe+=result[0]*2;
                    } else {
                        blocksarr=result;
                    }
                    break;
                case 3:
                    if (result[0]==result[1]) {
                        blocksarr[0]=result[0]+result[1];
                        scroe+=result[0]*2;
                        blocksarr[1]=result[2];
                    } else {
                        if (result[1]==result[2]) {
                            blocksarr[0]=result[0];
                            blocksarr[1]=result[1]+result[2];
                            scroe+=result[1]*2;
                        } else {
                            blocksarr=result;
                        }
                    }
                    break;
                case 4:
                    if (result[0]==result[1]) {
                        blocksarr[0]=result[0]+result[1];
                        scroe+=result[0]*2;
                        if (result[2]==result[3]) {
                            blocksarr[1]=result[2]+result[3];
                            scroe+=result[2]*2;
                        } else {
                            blocksarr[1]=result[2];
                            blocksarr[2]=result[3];
                        }
                    } else {
                        if (result[1]==result[2]) {
                            blocksarr[0]=result[0];
                            blocksarr[1]=result[1]+result[2];
                            scroe+=result[1]*2;
                            blocksarr[2]=result[3];
                        } else {
                            blocksarr[0]=result[0];
                            blocksarr[1]=result[1];
                            if (result[2]==result[3]) {
                                blocksarr[2]=result[2]+result[3];
                                scroe+=result[2]*2;
                            } else {
                                blocksarr=result;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            result=blocksarr;
        }
        return result;
    }

    /**
     * ��ת����eg:45000 --> 00054
     */
    public int[] reverseArr(int[] arr) {
        int[] tmp=new int[arr.length];
        int index=arr.length-1;
        for (int i = 0; i < arr.length; i++) {
            tmp[index]=arr[i];
            index--;
        }
        return tmp;
    }

    /**
     * ˢ�·��飬����³��ֵ�2��4
     */
    public void refershBlock(){
        if (numFlag==false) {
        }
        while (numFlag) {
            int index=(int) (Math.random()*16);
            if (blocks[index].getText().trim().equals("")) {
                if (Math.random()<0.8) {
                    blocks[index].setValue("2");
                } else {
                    blocks[index].setValue("4");
                }
                break;
            } else {
                continue;
            }
        }
    }

    /**
     * �ж��Ƿ��пյķ���
     */
    public void hasEmptyBlock() {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getText().trim().equals("")) {
                this.numFlag=true;
                break;
            } else {
                this.numFlag=false;
            }
        }
    }

    /**
     * �ж���Ϸ�Ƿ�ʧ��
     */
    public void isGameFail(String direction) {
        boolean result=true;    //true����ʧ�� false����û��ʧ��
        int tmp=0;
        if (numFlag == false) { // ��ʾû�пյķ���
            switch (direction) {
                case "up":
                case "down":
                    for (int i = 0; i < 4; i++) {
                        tmp=i*4;
                        for (int j = 0; j < 3; j++) {
                            if (blocks[tmp].getText().trim().equals(blocks[tmp+1].getText().trim())) {
                                result = false; //��Ϸδʧ��
                                break;
                            } else {
                                tmp++;
                            }
                        }
                        if (result==false) {
                            break;
                        }
                    }
                    break;
                case "left":
                case "right":
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (blocks[tmp].getText().trim().equals(blocks[tmp+4].getText().trim())) {
                                result = false; //��Ϸδʧ��
                                break;
                            } else {
                                tmp+=4;
                                if (tmp>=16) {
                                    break;
                                } else {
                                    continue;
                                }
                            }
                        }
                        tmp=i+1;
                        if (result==false) {
                            break;
                        }
                    }
                    break;
                default:
                    break;
            }
        } else {
            result=false;
        }
        if (result==true) {
            JOptionPane.showMessageDialog( null , "Game Over",null , JOptionPane.ERROR_MESSAGE) ;
            game2048View.newGame();
        } else {
        }
    }

    /**
     * �ж���Ϸ�Ƿ�ɹ�,���ɹ��ۼ���2048
     */
    public void win() {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getText().trim().equals("2048")) {
                JOptionPane.showMessageDialog( null , "YOU ARE WIN",null , JOptionPane.ERROR_MESSAGE) ;
                game2048View.newGame();
                break;
            }
        }
    }

    /**
     * ���ȫ���������ݣ����ڶԱ�
     */
    public int[] getBlock() {
        int[] blocksarr=new int[16];
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].getText().trim().equals("")) {
                blocksarr[i]=0;
            } else {
                blocksarr[i]=Integer.parseInt(blocks[i].getText().trim());
            }
        }
        return blocksarr;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO �Զ����ɵķ������
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO �Զ����ɵķ������
    }

}