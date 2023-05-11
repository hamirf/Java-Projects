package id.bootcamp.java310.mipro.nominal_dompet_elektronik.entities;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import id.bootcamp.java310.mipro.global_entities.BaseProperties;
import id.bootcamp.java310.mipro.nominal_dompet_elektronik.dto.WalletDTO;

@NamedNativeQueries(value = {
		@NamedNativeQuery(name = "get_all_nominals", query = "select * "
				+ "FROM public.m_wallet_default_nominal "
				+ "where is_delete = false "
				+ "order by nominal asc", 
				resultSetMapping = "get_all_nominals_result"),
		@NamedNativeQuery(name = "search_nominal", query = "select * "
				+ "FROM public.m_wallet_default_nominal "
				+ "where is_delete = false AND nominal = CAST (:nominal as INTEGER) "
				+ "order by nominal asc", 
				resultSetMapping = "get_all_nominals_result")
})

@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name = "get_all_nominals_result", classes = @ConstructorResult(targetClass = WalletDTO.class, columns = {
				@ColumnResult(name = "id", type = Long.class), 
				@ColumnResult(name = "nominal", type = Integer.class), 
		}))
})

@Entity
@Table(name = "m_wallet_default_nominal")
public class M_Wallet_Default_Nominal extends BaseProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	private Integer nominal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNominal() {
		return nominal;
	}

	public void setNominal(Integer nominal) {
		this.nominal = nominal;
	}			
	
}
