package cc.mobireach.api.model;
// Generated 24-Oct-2016 18:15:56 by Hibernate Tools 5.2.0.Beta1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Store generated by hbm2java
 */
@Entity
@Table(name = "Store", catalog = "estorywo_webapp")
public class Store implements java.io.Serializable {

	private Integer storeId;
	private Brand brand;
	private String storeName;
	private long locationLong;
	private long localtionLat;
	private BigDecimal rewardRate;
	private Set<Operator> operators = new HashSet<Operator>(0);
	private Set<LoyaltyTransaction> loyaltyTransactions = new HashSet<LoyaltyTransaction>(0);

	public Store() {
	}

	public Store(Brand brand, String storeName, long locationLong, long localtionLat, BigDecimal rewardRate) {
		this.brand = brand;
		this.storeName = storeName;
		this.locationLong = locationLong;
		this.localtionLat = localtionLat;
		this.rewardRate = rewardRate;
	}

	public Store(Brand brand, String storeName, long locationLong, long localtionLat, BigDecimal rewardRate,
			Set<Operator> operators, Set<LoyaltyTransaction> loyaltyTransactions) {
		this.brand = brand;
		this.storeName = storeName;
		this.locationLong = locationLong;
		this.localtionLat = localtionLat;
		this.rewardRate = rewardRate;
		this.operators = operators;
		this.loyaltyTransactions = loyaltyTransactions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "storeId", unique = true, nullable = false)
	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brandId", nullable = false)
	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Column(name = "storeName", nullable = false, length = 45)
	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Column(name = "locationLong", nullable = false)
	public long getLocationLong() {
		return this.locationLong;
	}

	public void setLocationLong(long locationLong) {
		this.locationLong = locationLong;
	}

	@Column(name = "localtionLat", nullable = false)
	public long getLocaltionLat() {
		return this.localtionLat;
	}

	public void setLocaltionLat(long localtionLat) {
		this.localtionLat = localtionLat;
	}

	@Column(name = "rewardRate", nullable = false, precision = 3)
	public BigDecimal getRewardRate() {
		return this.rewardRate;
	}

	public void setRewardRate(BigDecimal rewardRate) {
		this.rewardRate = rewardRate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set<Operator> getOperators() {
		return this.operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "store")
	public Set<LoyaltyTransaction> getLoyaltyTransactions() {
		return this.loyaltyTransactions;
	}

	public void setLoyaltyTransactions(Set<LoyaltyTransaction> loyaltyTransactions) {
		this.loyaltyTransactions = loyaltyTransactions;
	}

}
