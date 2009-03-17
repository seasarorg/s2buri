/*
 * 作成日: 2004/11/07
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
package org.escafe.buri.exception;

/**
 * @author makotan
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
public class BuriOGNLRuntimeException extends BuriException {

    /**
     * 
     */
    private static final long serialVersionUID = -1950910688122464073L;

    /**
     * @param messageCode
     */
    public BuriOGNLRuntimeException(String messageCode) {
        super(messageCode);
    }

    /**
     * @param messageCode
     * @param args
     */
    public BuriOGNLRuntimeException(String messageCode, Object[] args) {
        super(messageCode, args);
    }

    /**
     * @param messageCode
     * @param args
     * @param cause
     */
    public BuriOGNLRuntimeException(String messageCode, Object[] args, Throwable cause) {
        super(messageCode, args, cause);
    }
}
