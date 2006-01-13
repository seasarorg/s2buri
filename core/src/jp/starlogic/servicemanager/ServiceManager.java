/*
 * ì¬“ú: 2005/12/20
 *
 */
package jp.starlogic.servicemanager;

public interface ServiceManager {
    void startup();
    void terminate();
    
    void executeService(String serviceName);
}
