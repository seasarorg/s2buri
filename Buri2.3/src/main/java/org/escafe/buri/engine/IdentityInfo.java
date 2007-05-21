/*
 * 作成日: 2006/03/24
 *
 */
package org.escafe.buri.engine;

/**
 * ID情報を保持するクラスです。
 * <p>
 * ID情報は、ID番号とID文字列で構成されます。
 * アプリケーションによって、
 * <ul>
 * <li>ID番号のみ使用する</li>
 * <li>ID文字列のみ使用する</li>
 * <li>両方とも使用する</li>
 * </ul>
 * の3種類から自由に使用方法を選択することができます。
 * </p>
 * 
 * @author $Author$
 */
public class IdentityInfo {

    private Long idNumber;
    private String idString;

    public IdentityInfo() {
    }

    public IdentityInfo(Long idNumber, String idString) {
        this.idNumber = idNumber;
        this.idString = idString;
    }

    public IdentityInfo(Long idNumber) {
        this.idNumber = idNumber;
    }

    public IdentityInfo(String idString) {
        this.idString = idString;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    @Override
    public String toString() {
        return "[idString=" + idString + "/idNumber=" + idNumber + "]";
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((idNumber == null) ? 0 : idNumber.hashCode());
        result = PRIME * result + ((idString == null) ? 0 : idString.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final IdentityInfo other = (IdentityInfo) obj;
        if (idNumber == null) {
            if (other.idNumber != null) return false;
        } else if (!idNumber.equals(other.idNumber)) return false;
        if (idString == null) {
            if (other.idString != null) return false;
        } else if (!idString.equals(other.idString)) return false;
        return true;
    }
}
