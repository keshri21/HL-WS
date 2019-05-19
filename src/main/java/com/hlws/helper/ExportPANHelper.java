package com.hlws.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.hlws.enums.PANDataColumn;
import com.hlws.enums.PaymentInstructionColumn;
import com.hlws.model.Account;
import com.hlws.model.Pan;
import com.hlws.model.Vehicle;
import com.hlws.util.XlsUtil;

@Component
public class ExportPANHelper {
	
	public Integer generateXlsData(List<Pan> pans) throws IOException{
		try(
				Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
			Sheet sheet = workbook.createSheet("PAN data");
			int rowIndex = 0;
			Row row = sheet.createRow(rowIndex);
			Cell cell = null;
			int colIndex = 0;
			int noOfColumns = PANDataColumn.values().length;
			for (PANDataColumn column : PANDataColumn.values()) {
				sheet.setColumnWidth(colIndex, column.getWidth());
				cell = row.createCell(colIndex);
				cell.setCellValue(column.getValue());				
				cell.setCellStyle(XlsUtil.getBoldStyle(workbook));
				colIndex++;
			}
			
			for(Pan pan : pans) {
				//uncomment this loop if vehicle also needs to be exported as part of PAN data
				//for(Vehicle vehicle : pan.getVehicles()) {
					colIndex = 0;
					rowIndex++;
					row = sheet.createRow(rowIndex);
					Account account = pan.getPrimaryAccount();
					while(colIndex < noOfColumns) {
						PANDataColumn column = PANDataColumn.values()[colIndex];
						switch (column) {
						case PAN:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(pan.getPanNo());
							break;
						case PAN_HOLDER:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(pan.getPanHolderName());
							break;
						case TDS:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(pan.isTds() ? "YES" : "NO");
							break;
						case CITY:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(pan.getCity());
							break;
						case STATE:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(pan.getState());
							break;
						case ACCOUNT_NUMBER:
							cell = row.createCell(colIndex, CellType.NUMERIC);
							cell.setCellValue(null != account ? account.getAccountNo() : "NA");
							break;
						case ACCOUNT_HOLDER:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(null != account ? account.getAccountHoldername() : "");
							break;
						case BANK:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(null != account ? account.getBankName() : "");
							break;
						case BRANCH:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(null != account ? account.getBranchName() : "");
							break;
						case IFSC:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(null != account ? account.getIfscCode() : "");
							break;
/*						case VEHICLE_NUMBER:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(vehicle.getVehicleNo());
							break;
						case COMMENTS:
							cell = row.createCell(colIndex, CellType.STRING);
							cell.setCellValue(vehicle.isOldOwner() ? "Old Owner" : "");
							break;*/
						default:
							break;
						}
						colIndex++;
					}
				//}	//END loop for vehicle list
			}
			
			workbook.write(out);
			return XlsUtil.addToCache(new ByteArrayInputStream(out.toByteArray()));
			
		}
	}

}
