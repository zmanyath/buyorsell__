package com.wethinkcode.buyorsell.user;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.wethinkcode.buyorsell.cart_item.CartItem;
import com.wethinkcode.buyorsell.cart_item.CartItemService;
import com.wethinkcode.buyorsell.dto.loginDto;
import com.wethinkcode.buyorsell.location.Location;
import com.wethinkcode.buyorsell.location.LocationService;
import com.wethinkcode.buyorsell.order_details.OrderDetails;
import com.wethinkcode.buyorsell.order_details.OrderDetailsService;
import com.wethinkcode.buyorsell.payment_details.PaymentDetails;
import com.wethinkcode.buyorsell.payment_details.PaymentDetailsService;
import com.wethinkcode.buyorsell.user_payment.UserPayment;
import com.wethinkcode.buyorsell.user_payment.UserPaymentService;
import com.wethinkcode.buyorsell.exceptions.UserException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    LocationService addressService;

    @Autowired
    UserPaymentService userPaymentService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    PaymentDetailsService paymentDetailsService;

    

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value ="/", method = RequestMethod.GET, produces = "application/json")
	public String home() {
		return "This is the user Controller";
	}

    // User Mappings
    @GetMapping(value ={"/all-users"}, produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    } 

    @GetMapping(value = {"/{id}"}, produces = "application/json")
    public Optional<User> getById(@PathVariable("id") final int id) {
        return userService.getById(id);
    }

    @CrossOrigin
	@PostMapping(value = "/register")
    @ResponseBody
	public ResponseEntity<User> createUser(@RequestBody User u) throws UserException {
        userService.registerUser(u);		
		return new ResponseEntity<User>(userService.registerUser(u), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<loginDto> authenticateUser(@RequestBody loginDto lo) throws UserException{
        userService.loginUser(lo);
		
		return new ResponseEntity<loginDto>(userService.loginUser(lo), HttpStatus.CREATED);
    }
    
    //address mappings
    @PostMapping(value = {"/{id}/save-address/", "/save-address"})
    public Location saveAddress(@PathVariable("id") final int id, @RequestParam String address_line1, @RequestParam String address_line2, @RequestParam String city, @RequestParam long postal_code, @RequestParam String country) {
        Location address = new Location(address_line1, address_line2, city, postal_code, country);
        addressService.saveAddress(address); 
        return address;
    }

    @GetMapping(value ="/{id}/saved-addresses", produces = "application/json")
    public ResponseEntity<List<Location>> getLocations(@PathVariable("id") final int id) {
        return new ResponseEntity<>(addressService.getLocations(), HttpStatus.OK);
    } 

    //userpayment mappings
    @PostMapping(value = {"/save-payment"})
    public UserPayment saveUserPayment(@PathVariable("id") final int id, String payment_type, @RequestParam String provider, @RequestParam long account_no, @RequestParam long expiry, @RequestParam long cvc) {
        UserPayment payment = new UserPayment(payment_type, provider,account_no, expiry, cvc);
        userPaymentService.saveUserPayment(payment);
        return payment;
    }

    @GetMapping(value = "/user-payments", produces = "application/json")
    public ResponseEntity<List<UserPayment>> getAllPayments() {
        return new ResponseEntity<>(userPaymentService.getAllPayments(), HttpStatus.OK);
    }

    //order details mappings
    @GetMapping(value = "/orders/make-order")
    public OrderDetails createOrder(@RequestParam float total, @RequestParam Date created_at, @RequestParam Date modified_at) {
        OrderDetails order = new OrderDetails(total, created_at, modified_at);
        orderDetailsService.createOrder(order);
        return order;
    }

    @GetMapping(value = {"/orders/order/{id}"})
    public ResponseEntity<OrderDetails> getOrder(@RequestParam int id) {
        return new ResponseEntity<>(orderDetailsService.getOrder(id), HttpStatus.OK);
    }


    @GetMapping(value ="/orders", produces = "application/json")
    public ResponseEntity<List<OrderDetails>> listOrders() {
        return new ResponseEntity<List<OrderDetails>>(orderDetailsService.listOrders(), HttpStatus.OK);

    }

    @PostMapping(value = "/orders/order/payment-details/add-payment-details")
    public PaymentDetails addDetails(@RequestParam float amount,@RequestParam String provider, @RequestParam String status, @RequestParam Date created_at, @RequestParam Date modified_at) {
        PaymentDetails details = new PaymentDetails(amount, provider, status, created_at, modified_at);
        paymentDetailsService.addDetails(details);
        return details;
    }

    @GetMapping(value = "/orders/order/payment-details/{id}", produces = "application/json")
    public ResponseEntity<PaymentDetails> getDetails(@RequestParam int id) {
        return new ResponseEntity<>(paymentDetailsService.getDetails(id), HttpStatus.OK);
    }

    @GetMapping(value = "/orders/order/payment-details/")
    public ResponseEntity<List<PaymentDetails>> listPaymentDetails() {
        return new ResponseEntity<List<PaymentDetails>>(paymentDetailsService.listPaymentDetails(), HttpStatus.OK);

    }

    //cart mappings
    @PostMapping(value = "/orders/order/cart/addItems")
    public CartItem addToCart(@RequestParam int quantity, @RequestParam Date created_at, @RequestParam Date modified_at){
        CartItem item = new CartItem(quantity, created_at, modified_at);
        cartItemService.addToCart(item);
        return item;
    }

    @GetMapping(value = "/orders/order/cart/{id}")
    public ResponseEntity<CartItem> getCartItems(@RequestParam int id) {
        return new ResponseEntity<>(cartItemService.getCartItems(id), HttpStatus.OK);
    }

    @GetMapping(value = "/orders/order/cartItems")
    public ResponseEntity<List<CartItem>> lisItems() {
        return new ResponseEntity<List<CartItem>>(cartItemService.listItems(), HttpStatus.OK);

    }

    //delete mappings
    @DeleteMapping(value = "/delete-user/{id}") 
    public void deleteUser(@PathVariable int id) { 
        userService.deleteUser(id);
    }

    @DeleteMapping(value = "/addresses/address/delete-address/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
    }

    @DeleteMapping(value = "/user-payments/user-payment/delete-payment/{id}") 
    public void deleteUserPayment(@PathVariable int id) { 
        userPaymentService.deleteUserPayment(id);
    }

    @DeleteMapping(value = "/orders/order/delete-order/{id}")
    public void removeOrder(@PathVariable int id) {
        orderDetailsService.removeOrder(id);
    }

    @DeleteMapping(value = "/orders/order/cart/delete-item/{id}")
    public void removeItem(@PathVariable int id) {
        cartItemService.removeItem(id);
    }

}
