package id.bootcamp.java310.mipro.nominal_dompet_elektronik.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.nominal_dompet_elektronik.dto.WalletDTO;
import id.bootcamp.java310.mipro.nominal_dompet_elektronik.repositories.WalletRepository;

@Service
public class WalletService {

	@Autowired
	private WalletRepository wr;

	public List<WalletDTO> getAllNominals() {
		// TODO Auto-generated method stub
		return wr.getAllNominals();
	}

	public Long insert(String nominalStr, Long createBy) throws Exception {
		// TODO Auto-generated method stub
		
		if (nominalStr.equals("")) {
			throw new Exception("11-Nominal tidak boleh kosong!");
		}
		
		Boolean isNominalExists = wr.isNominalExists(nominalStr);
		if (isNominalExists) {
			throw new Exception("12-Nominal sudah ada!");
		}

		int nominal = Integer.parseInt(nominalStr);

		if (nominal <= 0) {
			throw new Exception("13-Nominal tidak boleh kurang dari sama dengan nol!");
		}
		
		return wr.insertNominal(nominalStr, createBy, new Date());
	}

	public Long update(Long id, String nominalStr, Long modifyBy) throws Exception {
		// TODO Auto-generated method stub
		
		if (nominalStr.equals("")) {
			throw new Exception("11-Nominal tidak boleh kosong!");
		}

		Boolean isNominalExists = wr.isNominalExists(nominalStr, id);
		if (isNominalExists) {
			throw new Exception("12-Nominal sudah ada!");
		}

		int nominal = Integer.parseInt(nominalStr);
		
		if (nominal <= 0) {
			throw new Exception("13-Nominal tidak boleh kurang dari sama dengan nol!");
		}
		
		return wr.updateNominal(id, nominalStr, modifyBy, new Date());
	}

	public Long delete(Long id, Long deleteBy) {
		// TODO Auto-generated method stub
		return wr.deleteNominal(id, deleteBy, new Date());
	}

	public List<WalletDTO> searchNominal(String nominal) {
		// TODO Auto-generated method stub
		if (nominal.equals("")) {
			return wr.getAllNominals();
		} else {
			return wr.searchNominal(nominal);			
		}
	}
	
}
