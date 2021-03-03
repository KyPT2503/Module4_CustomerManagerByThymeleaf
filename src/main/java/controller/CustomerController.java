package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ICustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/all")
    public ModelAndView showAll() {
        return new ModelAndView("index", "customers", customerService.findAll());
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        Customer customer = new Customer();
        customer.setId(customerService.findAll().size() + 1);
        return new ModelAndView("create", "customer", customer);
    }

    @PostMapping("/save")
    public ModelAndView save(Customer customer) {
        customer.setId(customerService.findAll().size() + 1);
        customerService.save(customer);
        return new ModelAndView("redirect:/customers/all");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable int id) {
        return new ModelAndView("create", "customer", customerService.findById(id));
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeletePage(@PathVariable int id) {
        return new ModelAndView("delete", "customer", customerService.findById(id));
    }

    @PostMapping("/delete")
    public ModelAndView deleteCustomer(Customer customer) {
        customerService.remove(customer.getId());
        return new ModelAndView("redirect:/customers/all");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable int id) {
        return new ModelAndView("detail", "customer", customerService.findById(id));
    }
}
