package com.betrybe.agrix.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Fertilizer entity.
 */
@Entity
@Table(name = "fertilizers")
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String brand;

  private String composition;

  @ManyToMany
  @JoinTable(
      name = "crops_fertilizers",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id")
  )
  private List<Crop> crops;

  public Fertilizer() {
  }

  /**
   * Fertilizer constructor.
   *
   * @param id of a fertilizer.
   * @param name of a fertilizer.
   * @param brand of a fertilizer.
   * @param composition of a fertilizer.
   * @param crops of a fertilizer.
   */
  public Fertilizer(Long id, String name, String brand, String composition, List<Crop> crops) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
    this.crops = crops;
  }

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

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getComposition() {
    return composition;
  }

  public void setComposition(String composition) {
    this.composition = composition;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void setCrops(Crop crop) {
    this.crops.add(crop);
  }
}
