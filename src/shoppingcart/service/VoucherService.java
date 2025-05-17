package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.common.Storage;
import shoppingcart.dto.Voucher;

public class VoucherService {
DatabaseService dbService = new DatabaseService();
	
	public ArrayList<Voucher> getVouchers(){
		return dbService.loadVouchers();
	}
	
	public Voucher applyVoucher1(String code) {
		Voucher foundVoucher = null;
		
		if(Storage.appliedVoucher != null) {
			ArrayList<Voucher> vouchers = getVouchers();
			for(Voucher voucher: vouchers) {
				if(checkVoucher(voucher, code)) {
					useVoucher(voucher);
					foundVoucher = voucher;
					break;
				}
			}
			
			if(foundVoucher != null) {
				
			}
		}
		
		return foundVoucher;
	}
	
	public Voucher findVoucher(String code) {
		ArrayList<Voucher> vouchers = getVouchers();
		Voucher foundVoucher = null;
		for(Voucher voucher: vouchers) {
			if(checkVoucher(voucher, code)) {
				useVoucher(voucher);
				foundVoucher = voucher;
				break;
			}
		}
		if(foundVoucher != null) {
			dbService.uploadVoucher(vouchers);
		}
		return foundVoucher;
	}
	
	public void useVoucher(Voucher voucher) {
		int currentCustomerUsage = voucher.getCustomerUsage();
		int currentCustomerLimit = voucher.getCustomerLimit();
		voucher.setCustomerUsage(currentCustomerUsage + 1);
		if(currentCustomerUsage + 1 == currentCustomerLimit) {
			voucher.setActive(false);
		}
	}
	
	public boolean checkVoucher(Voucher voucher, String code) {
		return voucher.getCode().equalsIgnoreCase(code) && voucher.isActive() && (voucher.getCustomerUsage() < voucher.getCustomerLimit());
	}
}
