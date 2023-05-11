package id.bootcamp.java310.mipro.pasien.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.bootcamp.java310.mipro.pasien.dto.PasienDTO;
import id.bootcamp.java310.mipro.pasien.repositories.PasienRepository;
import id.bootcamp.java310.mipro.utils.Pagination;

@Service
public class PasienService {

	@Autowired
	private PasienRepository pr;

	public List<PasienDTO> getAllRelation() {
		// TODO Auto-generated method stub
		return pr.getAllRelation();
	}

	public List<PasienDTO> getNameByCustomerID(Long[] customerIDs) {
		// TODO Auto-generated method stub
		List<PasienDTO> data = new ArrayList<>();
		
		for (Long customerID : customerIDs) {

			List<PasienDTO> data2 = pr.getNameByCustomerID(customerID);
			data.addAll(data2);
		}
		
		return data;
	}

	public List<PasienDTO> getAllPasienByCustomerID(Long biodataID) {
		// TODO Auto-generated method stub
		return pr.getAllPasienByCustomerID(biodataID);
	}

	public List<PasienDTO> searchPasienPerCustomer(Long biodataID, String keyword) {
		// TODO Auto-generated method stub
		return pr.searchPasienPerCustomer(biodataID, keyword);
	}

	public Pagination<List<PasienDTO>> paginationPasienPerCustomer(Long biodataID, String keyword, int limit,
			int page) {
		// TODO Auto-generated method stub
		int totalData = pr.countTotalData(biodataID, keyword.trim());

		int offset = (limit * (page - 1));
		System.out.println(offset);

		List<PasienDTO> data = pr.paginationPasienPerCustomer(biodataID, keyword.trim(), limit, offset);
		int itemPerPage = data.size();

		Pagination<List<PasienDTO>> pagination = new Pagination<List<PasienDTO>>(totalData, page, itemPerPage, data);

		return pagination;
	}

	public Pagination<List<PasienDTO>> sortByPaginationPasienPerCustomer(Long biodataID, String keyword, int limit, int page,
			String sorter, String ascdesc) {
		// TODO Auto-generated method stub
		int totalData = pr.countTotalData(biodataID, keyword.trim());

		int offset = (limit * (page - 1));
		System.out.println(offset);
		
		List<PasienDTO> data = new ArrayList<>();
		
		if (ascdesc.equals("asc") && sorter.equalsIgnoreCase("name")) {
			data = pr.sortByPaginationPasienPerCustomerNameASC(biodataID, keyword.trim(), limit, offset);
		} else if (ascdesc.equals("desc") && sorter.equalsIgnoreCase("name")) {
			data = pr.sortByPaginationPasienPerCustomerNameDESC(biodataID, keyword.trim(), limit, offset);
		} else if (ascdesc.equals("asc") && sorter.equalsIgnoreCase("cc")) {
			data = pr.sortByPaginationPasienPerCustomerChatASC(biodataID, keyword.trim(), limit, offset);
		} else if (ascdesc.equals("desc") && sorter.equalsIgnoreCase("cc")) {
			data = pr.sortByPaginationPasienPerCustomerChatDESC(biodataID, keyword.trim(), limit, offset);
		} else if (ascdesc.equals("asc") && sorter.equalsIgnoreCase("ac")) {
			data = pr.sortByPaginationPasienPerCustomerAppointmentASC(biodataID, keyword.trim(), limit, offset);
		} else if (ascdesc.equals("desc") && sorter.equalsIgnoreCase("ac")) {
			data = pr.sortByPaginationPasienPerCustomerAppointmentDESC(biodataID, keyword.trim(), limit, offset);
		}

		int itemPerPage = data.size();

		Pagination<List<PasienDTO>> pagination = new Pagination<List<PasienDTO>>(totalData, page, itemPerPage, data);

		return pagination;
	}

	public void addPasien(String fullname, String dobStr, String gender, String golonganDarah, String rhesus,
			String height, String weight, String relationStr, Long parentBiodataID, Long createdBy) throws Exception {
		// TODO Auto-generated method stub
		BigDecimal heightDc = null;
		BigDecimal weightDc = null;
		Long golDar = null;

		if (fullname.equals("")) {
			throw new Exception("11-*Nama Lengkap harus diisi");
		}

		Boolean isNameInBioExists = pr.isNameInBioExists(fullname);
		if (isNameInBioExists) {
			Long biodataID = pr.getBioIDForNameValidation(fullname);
			Boolean isNameInCustExists = pr.isNameInCustExists(biodataID);
			if (isNameInCustExists) {
				Long customerID = pr.getCustIDForNameValidation(biodataID);
				Boolean isNameInMemberExists = pr.isNameInMemberExists(customerID, parentBiodataID);
				if (isNameInMemberExists) {
					throw new Exception("12-*Nama Lengkap sudah terdaftar");
				}
			}
		}

		if (dobStr.equals("")) {
			throw new Exception("21-*Tanggal Lahir harus diisi");
		}

		if (!(isValidDateFormat(dobStr))) {
			throw new Exception("22-*Sesuaikan format Tanggal Lahir (Contoh: 10 Agustus 1998)");
		}

		if (gender.equals("")) {
			throw new Exception("31-*Jenis Kelamin harus diisi");
		}

		if (golonganDarah.equals("")) {
			golonganDarah = null;
		} else {
			golDar = Long.parseLong(golonganDarah);
		}

		if (rhesus.equals("")) {
			rhesus = null;
		}

		if (height.equals("")) {
			height = null;
		} else if (height.substring(0, 1).contains("-")) {
			throw new Exception("51-*Tinggi Badan tidak boleh minus");
		} else {
			heightDc = new BigDecimal(height);
		}
		
		if (weight.equals("")) {
			weight = null;
		} else if (weight.substring(0, 1).contains("-")) {
			throw new Exception("52-*Berat Badan tidak boleh minus");
		} else {
			weightDc = new BigDecimal(weight);
		}

		if (relationStr.equals("")) {
			throw new Exception("41-*Relasi harus diisi");
		}

		Locale localeID = new Locale("id", "ID");
		String pattern = "dd MMMM yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, localeID);
		Date dob = sdf.parse(dobStr);

		Long relation = Long.parseLong(relationStr);

		Long biodataID = pr.insertBiodata(fullname, createdBy);

		Long customerID = pr.insertPasien(biodataID, dob, gender, golDar, rhesus, heightDc, weightDc, createdBy);

		pr.insertRelasi(customerID, relation, parentBiodataID, createdBy);

	}

	public void updatePasien(Long biodataID, String fullname, Long customerID, String dobStr, String gender,
			String golonganDarah, String rhesus, String height, String weight, String relationStr, Long parentBiodataID,
			Long modifiedBy) throws Exception {
		// TODO Auto-generated method stub
		BigDecimal heightDc = null;
		BigDecimal weightDc = null;
		Long golDar = null;

		if (fullname.equals("")) {
			throw new Exception("11-*Nama Lengkap harus diisi");
		}

		Boolean isNameInBioExists = pr.isNameInBioExists(biodataID, fullname);
		if (isNameInBioExists) {
			Long biodataIDValidation = pr.getBioIDForNameValidation(fullname);
			Boolean isNameInCustExists = pr.isNameInCustExists(biodataIDValidation);
			if (isNameInCustExists) {
				Long customerIDValidation = pr.getCustIDForNameValidation(biodataIDValidation);
				Boolean isNameInMemberExists = pr.isNameInMemberExists(customerIDValidation, parentBiodataID);
				if (isNameInMemberExists) {
					throw new Exception("12-*Nama Lengkap sudah terdaftar");
				}
			}
		}

		if (dobStr.equals("")) {
			throw new Exception("21-*Tanggal Lahir harus diisi");
		}

		if (!(isValidDateFormat(dobStr))) {
			throw new Exception("22-*Sesuaikan format Tanggal Lahir (Contoh: 10 Agustus 1998)");
		}

		if (gender.equals("")) {
			throw new Exception("31-*Jenis Kelamin harus diisi");
		}

		if (golonganDarah.equals("")) {
			golonganDarah = null;
		} else {
			golDar = Long.parseLong(golonganDarah);
		}

		if (rhesus.equals("")) {
			rhesus = null;
		}

		if (height.equals("")) {
			height = null;
		} else if (height.substring(0, 1).contains("-")) {
			throw new Exception("51-*Tinggi Badan tidak boleh minus");
		} else {
			heightDc = new BigDecimal(height);
		}
		
		if (weight.equals("")) {
			weight = null;
		} else if (weight.substring(0, 1).contains("-")) {
			throw new Exception("52-*Berat Badan tidak boleh minus");
		} else {
			weightDc = new BigDecimal(weight);
		}

		if (relationStr.equals("")) {
			throw new Exception("41-*Relasi harus diisi");
		}

		Locale localeID = new Locale("id", "ID");
		String pattern = "dd MMMM yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, localeID);
		Date dob = sdf.parse(dobStr);

		Long relation = Long.parseLong(relationStr);

		pr.updateBiodata(biodataID, fullname, modifiedBy);

		pr.updatePasien(customerID, dob, gender, golDar, rhesus, heightDc, weightDc, modifiedBy);

		pr.updateRelasi(customerID, relation, parentBiodataID, modifiedBy);

	}

	public void deletePasien(Long customerID, Long deletedBy) {
		// TODO Auto-generated method stub
		pr.deletePasienInCustMemberTable(customerID, deletedBy);

		Long biodataID = pr.deletePasienInCustomerTable(customerID, deletedBy);

		pr.deletePasienInBiodataTable(biodataID, deletedBy);
	}

	public void deletePasienses(Long[] customerIDs, Long deletedBy) {
		// TODO Auto-generated method stub
		pr.deletePasiensesInCustMemberTable(customerIDs, deletedBy);

		Long[] biodataIDs = pr.deletePasiensesInCustomerTable(customerIDs, deletedBy);

		pr.deletePasienInBiodataTable(biodataIDs, deletedBy);
	}

	public boolean isValidDateFormat(String dob) {
		/*
		 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
		 */
		Locale localeID = new Locale("id", "ID");

		String pattern = "dd MMMM yyyy";

		SimpleDateFormat sdfrmt = new SimpleDateFormat(pattern, localeID);
		sdfrmt.setLenient(false);
		/*
		 * Create Date object parse the string into date
		 */
		try {
			Date dobDate = sdfrmt.parse(dob);
			System.out.println(dobDate + " is valid date format");
		}
		/* Date format is invalid */
		catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		/* Return true if date format is valid */
		return true;
	}

}
