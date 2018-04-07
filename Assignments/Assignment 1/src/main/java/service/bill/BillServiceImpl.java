package service.bill;

import model.Bill;
import repository.bill.BillRepository;

public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Boolean addBill(Bill bill) {
        return billRepository.addBill(bill);
    }
}
