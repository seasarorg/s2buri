/*
 * ì¬“ú: 2005/12/27
 *
 */
package test.sampleservice;


public class CountCronService {
    private int count = 0;
    
    public void countUp() {
        count = count+1;
        System.out.println("count="+count);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
