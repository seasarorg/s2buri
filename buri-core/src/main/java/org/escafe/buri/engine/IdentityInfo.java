/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.escafe.buri.engine;

/**
 * ID情報を保持するクラスです。
 * <p>
 * ID情報は、ID番号とID文字列で構成されます。アプリケーションによって、
 * <ul>
 * <li>ID番号のみ使用する</li>
 * <li>ID文字列のみ使用する</li>
 * <li>両方とも使用する</li>
 * </ul>
 * の3種類から自由に使用方法を選択することができます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/24
 */
public class IdentityInfo {

	/**
	 * ID番号
	 */
	private Long idNumber;
	/**
	 * ID文字列
	 */
	private String idString;

	/**
	 * ID情報のコンストラクタです。
	 * <p>
	 * ID番号、ID文字列を指定せずにインスタンスを生成します。
	 * </p>
	 */
	public IdentityInfo() {
	}

	/**
	 * ID情報のコンストラクタです。
	 * <p>
	 * ID番号、ID文字列を指定してインスタンスを生成します。
	 * </p>
	 * 
	 * @param idNumber ID番号
	 * @param idString ID文字列
	 */
	public IdentityInfo(Long idNumber, String idString) {
		this.idNumber = idNumber;
		this.idString = idString;
	}
	/**
	 * ID情報のコンストラクタです。
	 * <p>
	 * ID番号を指定してインスタンスを生成します。
	 * </p>
	 * 
	 * @param idNumber ID番号
	 */
	public IdentityInfo(Long idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * ID情報のコンストラクタです。
	 * <p>
	 * ID文字列を指定してインスタンスを生成します。
	 * </p>
	 * 
	 * @param idString ID文字列
	 */
	public IdentityInfo(String idString) {
		this.idString = idString;
	}

	/**
	 * ID番号を返します。
	 * 
	 * @return ID番号
	 */
	public Long getIdNumber() {
		return idNumber;
	}

	/**
	 * ID番号を登録します。
	 * 
	 * @param idNumber ID番号
	 */
	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * ID文字列を返します。
	 * 
	 * @return ID文字列
	 */
	public String getIdString() {
		return idString;
	}

	/**
	 * ID文字列を登録します。
	 * 
	 * @param idString ID文字列
	 */
	public void setIdString(String idString) {
		this.idString = idString;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[idString=" + idString + "/idNumber=" + idNumber + "]";
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((idNumber == null) ? 0 : idNumber.hashCode());
		result = PRIME * result
				+ ((idString == null) ? 0 : idString.hashCode());
		return result;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final IdentityInfo other = (IdentityInfo) obj;
		if (idNumber == null) {
			if (other.idNumber != null) {
				return false;
			}
		} else if (!idNumber.equals(other.idNumber)) {
			return false;
		}
		if (idString == null) {
			if (other.idString != null) {
				return false;
			}
		} else if (!idString.equals(other.idString)) {
			return false;
		}
		return true;
	}
}
