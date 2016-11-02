package main;
import ordercontents.OrderService;
import facilitycontents.FacilityService;
//import itemcontents.ItemService;
//import networkcontents.NetworkService;
public class Main {

	public static void main(String[] args) {
		System.out.println(FacilityService.getInstance().toString());
		System.out.println(OrderService.getInstance().processOrders());
		System.out.println(FacilityService.getInstance().toString());
	}
}
