package com.springboot.files.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.springboot.files.entities.Account;
import com.springboot.files.repositories.ReadFileRepository;

@Service
@Transactional
public class ReadFileServiceImpl implements ReadFileService {

	@Autowired
	private ReadFileRepository readFileRepository;

	@Override
	public String test(String name) {
		return "Welome " + name;
	}

	@Override
	public List<Account> findAll() {
		return (List<Account>) readFileRepository.findAll();
	}

	@Override
	public boolean saveDataFromUploadFile(MultipartFile file) {
		boolean flag = false;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension.equalsIgnoreCase("json")) {
			flag = readDataFromJson(file);
		} else if (extension.equalsIgnoreCase("csv")) {
			flag = readDataFromCSV(file);
		} else if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			flag = readDataFromXL(file);
		}
		return flag;
	}

	private boolean readDataFromXL(MultipartFile file) {
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		rows.next(); // avoid first line since it contains headers
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		while (rows.hasNext()) {
			Row row = rows.next();
			Account account = new Account();
			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
				account.setAccountName(row.getCell(0).getStringCellValue());
			}
			if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
				account.setAccountNumber(row.getCell(1).getStringCellValue());
			}
			if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				String withdrawalAmount = NumberToTextConverter.toText(row.getCell(2).getNumericCellValue());
				account.setWithdrawalAmount(withdrawalAmount);
			}
			account.setFileType(extension);
			readFileRepository.save(account);
		}
		return true;
	}

	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			if (extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			} else if (extension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return workbook;
	}

	private boolean readDataFromCSV(MultipartFile file) {
		try {
			InputStreamReader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows = csvReader.readAll();
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			for (String[] row : rows) {
				Account account = new Account(row[0], row[1], row[2], extension);
				readFileRepository.save(account);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private boolean readDataFromJson(MultipartFile file) {
		try {
			InputStream is = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<Account> accounts = Arrays.asList(mapper.readValue(is, Account[].class));
			if (accounts != null && !accounts.isEmpty()) {
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				for (Account account : accounts) {
					account.setFileType(extension);
					readFileRepository.save(account);
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
