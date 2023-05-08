package com.openfood.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Marque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany(mappedBy = "marques")
	private List<Produit> produits = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Produit> getProduit() {
		return produits;
	}

	public void setProduit(List<Produit> produit) {
		this.produits = produit;
	}

	@Override
	public String toString() {
		return "Marque [id=" + id + ", name=" + name + ", produit=" + produits + "]";
	}
	
}
