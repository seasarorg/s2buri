/*
 * �쐬��: 2005/12/20
 *
 */
package test.sampleservice;

import jp.starlogic.servicemanager.abst.AbstractGetRunService;

public class CounterService extends AbstractGetRunService {
    private int counter = 0;

    public void initService() {
        System.out.println("�n�܂��`o(^o^)o ���N���N");
    }
    
    public boolean canService() {
        return true;
    }

    public void execute() {
        counter = counter + 1;
        System.out.println("�J�E���^="+counter);
        if(counter==10) {
            throw new NullPointerException();
        }
    }

    public void destroyService() {
        System.out.println("��������܂������I�I");
        System.out.println("");
    }
    

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
