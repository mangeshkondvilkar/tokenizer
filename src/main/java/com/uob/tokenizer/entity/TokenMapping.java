package com.uob.tokenizer.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A JPA entity class to mapped to TOKEN_MAPPING table in DB.
 *
 * @author Mangesh K
 * @since March 2018
 *
 */
@Entity
@Table(name = "TOKEN_MAPPING")
public class TokenMapping implements Serializable {

	private static final long serialVersionUID = 2181947005979062426L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TOKEN_NAME")
	private String tokenName;

	@Column(name = "CIPHER_TEXT")
	private BigInteger cipherText;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public BigInteger getCipherText() {
		return cipherText;
	}

	public void setCipherText(BigInteger cipherText) {
		this.cipherText = cipherText;
	}

}
