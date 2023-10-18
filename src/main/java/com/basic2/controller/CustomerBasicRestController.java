package com.basic2.controller;

import com.basic2.entity.Customer;
import com.basic2.exception.BusinessException;
import com.basic2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerBasicRestController {

        @Autowired
        private CustomerRepository customerRepository; //먼저 레파지토리를 인젝션받는다.

        @PostMapping
        public Customer create(@RequestBody Customer customer) {
            return customerRepository.save(customer);
        }

        @GetMapping
        public List<Customer> getCustomers() {
                System.out.println("Test");
                return customerRepository.findAll(); // 부트에서 구현해준findAll() 메소드사용
        }

        @GetMapping("/{id}")
        public Customer getCustomer(@PathVariable Long id) { //매개변수앞에써주는어노테이션@PathVariable
            Optional<Customer> optionalCustomer = customerRepository.findById(id);
                //id도 email과 같은 unique 값이라 Optional로 중복,널값이 들어왔을때 처리를 하기위해 써주어야함.
            Customer customer = optionalCustomer.orElseThrow(() ->new BusinessException("User Not Found",
                    HttpStatus.NOT_FOUND));
                    return customer;
        }

        @GetMapping("/email/{email}")
        public Customer getCustomerByEmail(@PathVariable String email) {
                return customerRepository.findByEmail(email)
                        .orElseThrow(() -> new BusinessException("요청하신 email에 해당하는 유저가 없습니다",
                                HttpStatus.NOT_FOUND));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
                Customer customer = customerRepository.findById(id)
                        .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));

                customerRepository.delete(customer);

                return ResponseEntity.ok(id + " Customer가 삭제 되었습니다.");

        }

}
