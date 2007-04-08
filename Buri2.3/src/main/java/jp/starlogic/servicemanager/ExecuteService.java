/*
 * 作成日: 2005/12/20
 *
 */
package jp.starlogic.servicemanager;

public interface ExecuteService extends Runnable{
    void setServiceName(String serviceName);
    void execute(OneService service);
    void terminate();
    void clear();
}
