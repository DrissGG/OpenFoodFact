package com.openfood.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Float energy;
	private Float graisse;
	private Float sucres;
	private Float fibres;
	private Float proteines;
	private Double sel;
	private Double calcium;
	private Double magnesium;
	private Double iron;
	private Double vitA;
	private Double vitD;
	private Double vitE;
	private Double vitC;
	private Double vitK;
	private Double vitB1;
	private Double vitB2;
	private Double vitPP;
	private Double vitB6;
	private Double vitB9;
	private Double vitB12;
	private Double fer;
	private Double betaCarotene;
	private Boolean presenceHuilePalme;
	
	@ManyToOne
	private Categorie category;
	
	@Enumerated(EnumType.STRING)
	private NutriScore nutriscore;
	
	@ManyToMany
	private List<Marque> marques = new ArrayList<>();
	
	@ManyToMany
	List<Ingredient> ingredients = new ArrayList<>();;
	
	@ManyToMany
	private List<Allergene> allergenes = new ArrayList<>();
	
	@ManyToMany
	private List<Additif> additifs = new ArrayList<>();
	
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
	public Float getEnergy() {
		return energy;
	}
	public void setEnergy(Float energy) {
		this.energy = energy;
	}
	public Float getGraisse() {
		return graisse;
	}
	public void setGraisse(Float graisse) {
		this.graisse = graisse;
	}
	public Float getSucres() {
		return sucres;
	}
	public void setSucres(Float sucres) {
		this.sucres = sucres;
	}
	public Float getFibres() {
		return fibres;
	}
	public void setFibres(Float fibres) {
		this.fibres = fibres;
	}
	public Float getProteines() {
		return proteines;
	}
	public void setProteines(Float proteines) {
		this.proteines = proteines;
	}
	public Double getSel() {
		return sel;
	}
	public void setSel(Double sel) {
		this.sel = sel;
	}
	public Double getCalcium() {
		return calcium;
	}
	public void setCalcium(Double calcium) {
		this.calcium = calcium;
	}
	public Double getMagnesium() {
		return magnesium;
	}
	public void setMagnesium(Double magnesium) {
		this.magnesium = magnesium;
	}
	public Double getIron() {
		return iron;
	}
	public void setIron(Double iron) {
		this.iron = iron;
	}
	public Double getVitA() {
		return vitA;
	}
	public void setVitA(Double vitA) {
		this.vitA = vitA;
	}
	public Double getVitD() {
		return vitD;
	}
	public void setVitD(Double vitD) {
		this.vitD = vitD;
	}
	public Double getVitE() {
		return vitE;
	}
	public void setVitE(Double vitE) {
		this.vitE = vitE;
	}
	public Double getVitC() {
		return vitC;
	}
	public void setVitC(Double vitC) {
		this.vitC = vitC;
	}
	public Double getVitK() {
		return vitK;
	}
	public void setVitK(Double vitK) {
		this.vitK = vitK;
	}
	public Double getVitB1() {
		return vitB1;
	}
	public void setVitB1(Double vitB1) {
		this.vitB1 = vitB1;
	}
	public Double getVitB2() {
		return vitB2;
	}
	public void setVitB2(Double vitB2) {
		this.vitB2 = vitB2;
	}
	public Double getVitPP() {
		return vitPP;
	}
	public void setVitPP(Double vitPP) {
		this.vitPP = vitPP;
	}
	public Double getVitB6() {
		return vitB6;
	}
	public void setVitB6(Double vitB6) {
		this.vitB6 = vitB6;
	}
	public Double getVitB9() {
		return vitB9;
	}
	public void setVitB9(Double vitB9) {
		this.vitB9 = vitB9;
	}
	public Double getVitB12() {
		return vitB12;
	}
	public void setVitB12(Double vitB12) {
		this.vitB12 = vitB12;
	}
	public Double getFer() {
		return fer;
	}
	public void setFer(Double fer) {
		this.fer = fer;
	}
	public Double getBetaCarotene() {
		return betaCarotene;
	}
	public void setBetaCarotene(Double betaCarotene) {
		this.betaCarotene = betaCarotene;
	}
	public Boolean getPresenceHuilePalme() {
		return presenceHuilePalme;
	}
	public void setPresenceHuilePalme(Boolean presenceHuilePalme) {
		this.presenceHuilePalme = presenceHuilePalme;
	}
	public Categorie getCategory() {
		return category;
	}
	public void setCategory(Categorie category) {
		this.category = category;
	}
	public NutriScore getNutriscore() {
		return nutriscore;
	}
	public void setNutriscore(NutriScore nutriscore) {
		this.nutriscore = nutriscore;
	}
	public List<Marque> getMarques() {
		return marques;
	}
	public void setMarques(List<Marque> marques) {
		this.marques = marques;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public List<Allergene> getAllergenes() {
		return allergenes;
	}
	public void setAllergenes(List<Allergene> allergenes) {
		this.allergenes = allergenes;
	}
	public List<Additif> getAdditifs() {
		return additifs;
	}
	public void setAdditifs(List<Additif> additifs) {
		this.additifs = additifs;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", name=" + name + ", energy=" + energy + ", graisse=" + graisse + ", sucres="
				+ sucres + ", fibres=" + fibres + ", proteines=" + proteines + ", sel=" + sel + ", calcium=" + calcium
				+ ", magnesium=" + magnesium + ", iron=" + iron + ", vitA=" + vitA + ", vitD=" + vitD + ", vitE=" + vitE
				+ ", vitC=" + vitC + ", vitK=" + vitK + ", vitB1=" + vitB1 + ", vitB2=" + vitB2 + ", vitPP=" + vitPP
				+ ", vitB6=" + vitB6 + ", vitB9=" + vitB9 + ", vitB12=" + vitB12 + ", fer=" + fer + ", betaCarotene="
				+ betaCarotene + ", presenceHuilePalme=" + presenceHuilePalme + ", category=" + category
				+ ", nutriscore=" + nutriscore + ", marques=" + marques + ", ingredients=" + ingredients
				+ ", allergenes=" + allergenes + ", additifs=" + additifs + "]";
	}
	
	
	
	
	
	

	
	
	
}
