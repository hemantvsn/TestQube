package com.shasm.model.users;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@Entity
public class UserPassword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Users user;
	private String digest;
	private String salt;
	private String digestAlgorithm = "SHA1";

	public UserPassword(String salt) {
		setSalt(salt);
	}

	public UserPassword() {
		
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

	public Users getUser() {
		return user;
	}

	private String makeDigest(String userEnteredPwd) {

		if (StringUtils.isEmpty(userEnteredPwd) || StringUtils.isEmpty(salt) || StringUtils.isEmpty(digestAlgorithm)) {
			throw new RuntimeException("Incomplete data for generating digest");			
		}
		//strength EX: 1, 256, 384, 512
		int strength =0;
		switch (digestAlgorithm) {
		
		case "SHA1":
			strength = 1;
			break;

		case "SHA256":
			strength = 256;
			break;

		default:
			throw new RuntimeException("No matching encoder found");
		}
		
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(strength);
		return encoder.encodePassword(userEnteredPwd, salt);
		
	}

	public boolean matches(String password) {
		// If we don't have a digest set, match fails.
		if (StringUtils.isEmpty(digest)) {
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

	public void setUser(Users user) {
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
