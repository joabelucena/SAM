package br.com.ttrans.samapp.security;

import java.security.NoSuchAlgorithmException;

/**
 * This class encrypts a String to MD5 or SHA1 hashType. Special thanks to
 * 'fitorec' from StackOverflow Java community.
 * 
 * @author fitorec
 * 
 * @see https://github.com/fitorec/java-apps-authentication
 * @see http://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
 * 
 * 
 * @param txt,
 *            text in plain format
 * @param hashType
 *            MD5 OR SHA1
 * @return hash in hashType
 */
public class Hash {

	public static String getHash(String txt, String hashType) throws NoSuchAlgorithmException {

		java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
		byte[] array = md.digest(txt.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();

	}

	public static String md5(String txt) throws NoSuchAlgorithmException {
		return Hash.getHash(txt, "MD5");
	}

	public static String sha1(String txt) throws NoSuchAlgorithmException {
		return Hash.getHash(txt, "SHA1");
	}
}
