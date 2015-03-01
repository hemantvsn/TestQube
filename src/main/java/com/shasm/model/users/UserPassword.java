package com.shasm.model.users;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserPassword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;
	private String digest;
	private String salt;
	private String digestAlgorithm = "SHA1";

	public UserPassword(String salt) {
		setSalt(salt);
	}

	public String getDigest() {
		return digest;
	}

	public String getDigestAlgorithm() {
		return digestAlgorithm;
	}

	public int getId() {
		return this.id;
	}

	public User getUser() {
		return user;
	}

	private String makeDigest(String password) {

		if ((password != null) && !password.isEmpty()
				&& (digestAlgorithm != null)) {
			// String salt= user.getSalt();
			if ((salt == null) || salt.isEmpty()) {
				return null;
			}
			/* return Util.makeHexDigest(salt + password, digestAlgorithm); */
		}

		return null;
	}

	public boolean matches(String password) {
		// If we don't have a digest set, match fails.
		if ((digest == null) || digest.isEmpty()) {
			return false;
		}

		return digest.equals(makeDigest(password));
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public void setDigestAlgorithm(String digestAlgorithm) {
		this.digestAlgorithm = digestAlgorithm;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
		if (user != null) {
			this.id = user.getId();
		}
	}

	public void setPassword(String password) {
		if (user == null) {
			throw new IllegalStateException(
					"Cannot set password for user that does not exist. Kindly check whether user was set.");
		}
		digest = makeDigest(password);
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
